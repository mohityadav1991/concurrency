package com.mohit.concurrency.booking.test;

import com.mohit.concurrency.booking.BeanConfig;
import com.mohit.concurrency.booking.api.ExternalApi;
import com.mohit.concurrency.booking.api.InternalApi;
import com.mohit.concurrency.booking.model.entity.*;
import com.mohit.concurrency.booking.model.exception.InvalidStateException;
import com.mohit.concurrency.booking.model.exception.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/

public class Runner {

    public static void main(String[] args) throws NotFoundException, InvalidStateException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        ExternalApi externalApi = applicationContext.getBean(ExternalApi.class);
        InternalApi internalApi = applicationContext.getBean(InternalApi.class);
        testConcurrentLockSeatsScenario(externalApi, internalApi);

    }

    @SneakyThrows
    private static void testConcurrentLockSeatsScenario(ExternalApi externalApi, InternalApi internalApi) throws NotFoundException, InvalidStateException {
        setup(internalApi);
//        MovieSearchFilter t1MovieSearchFilter = MovieSearchFilter.builder().title("T1").build();
//        System.out.println("T1: " + externalApi.fetchEvents(t1MovieSearchFilter));
//
//        MovieSearchFilter g2MovieSearchFilter = MovieSearchFilter.builder().genre("G1").build();
//        System.out.println("G1: " + externalApi.fetchEvents(g2MovieSearchFilter));
//
//        MovieSearchFilter l1MovieSearchFilter = MovieSearchFilter.builder().language("L1").build();
//        System.out.println("L1: " + externalApi.fetchEvents(l1MovieSearchFilter));
//
//        MovieSearchFilter l2g2MovieSearchFilter = MovieSearchFilter.builder().genre("G2").language("l2").build();
//        System.out.println("G2,L2: " + externalApi.fetchEvents(l2g2MovieSearchFilter));


        // Fetch Layout API
//        boolean[][] seatLayout1 = externalApi.fetchEventSeatLayout(1);
//        printLayout(seatLayout1);
//        boolean[][] seatLayout2 = externalApi.fetchEventSeatLayout(2);
//        printLayout(seatLayout2);

        //
        Set<GridLocation> bookSeats1 = Stream.of(new GridLocation(0, 0),
                new GridLocation(0, 1),
                new GridLocation(0, 2),
                new GridLocation(0, 3),
                new GridLocation(0, 4))
                .collect(Collectors.toSet());

        CompletableFuture.runAsync(() -> bookSeat(externalApi, bookSeats1, 1l, 1000l));
        CompletableFuture.runAsync(() -> bookSeat(externalApi, bookSeats1, 1l, 2000l));
    }

    private static void bookSeat(ExternalApi externalApi, Set<GridLocation> bookSeats1, long eventId, long userId) {

        try {
            boolean lockSeatStatus = externalApi.lockSeats(bookSeats1, eventId, userId);
            System.out.println(String.format("userId: %d, eventId: %d, response: %s", userId, eventId, lockSeatStatus ));

            if (lockSeatStatus) {
                Long bookingId = externalApi.bookSeats(bookSeats1, eventId, userId);
                if (bookingId > 0) {
                    Booking booking = externalApi.fetchBooking(bookingId);
                    System.out.println(booking);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void setup(InternalApi internalApi) {
        Seat[][] layout1 = {
                {new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat()},
                {new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT)},
                {new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT)},
                {new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT)},
                {new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT)},
        };
        Seat[][] layout2 = {
                {new Seat(), new Seat(), new Seat(), new Seat(), new Seat()},
                {new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat()},
                {new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat()},
                {new Seat(), new Seat(), new Seat(SeatStatus.NOT_PRESENT), new Seat(), new Seat()},
        };
        Screen s1 = new Screen(1l, layout1);
        Screen s2 = new Screen(2l, layout2);

        List<Screen> screens = new ArrayList<>();
        screens.add(s1);
        screens.add(s2);

        Movie m1 = new Movie(s1, "T1", "G1", "L2");
        m1.setId(1l);
        Movie m2 = new Movie(s2, "T1", "G2", "L1");
        m2.setId(2l);
        Movie m3 = new Movie(s1, "T2", "G2", "L2");
        m3.setId(3l);
        Movie m4 = new Movie(s1, "T2", "G1", "L2");
        m4.setId(4l);
        Movie m5 = new Movie(s2, "T1", "G2", "L1");
        m5.setId(5l);

        List<Movie> movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);
        movies.add(m5);

        internalApi.addOrUpdateMovie(movies);
    }

    private static void printLayout(boolean[][] seatLayout) {
        for (boolean[] row : seatLayout) {
            for (boolean cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
