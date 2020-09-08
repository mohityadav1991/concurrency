package com.mohit.concurrency.booking.api.service;

import com.mohit.concurrency.booking.model.entity.*;
import com.mohit.concurrency.booking.model.exception.InvalidStateException;
import com.mohit.concurrency.booking.model.exception.NotFoundException;
import com.mohit.concurrency.booking.repository.data.BookingDatabase;
import com.mohit.concurrency.booking.repository.data.MovieDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class BookingService {

    @Autowired
    private BookingDatabase bookingDatabase;

    @Autowired
    private MovieDatabase movieDatabase;

    public boolean lockSeats(Set<GridLocation> seatIds, Long movieId, Long userId) {
        Movie movie = movieDatabase.findById(movieId);
        Screen screen = movie.getScreen();
        Seat[][] layout = screen.getLayout();
        if (!areSeatsAvailable(seatIds, layout))
            return false;
        // Lock Seats
        setSeatStatus(seatIds, userId, layout, SeatStatus.LOCKED);

        // Save state
        screen.setLayout(layout);
        movie.setScreen(screen);
        movieDatabase.save(movie);
        return true;
    }

    public Long bookSeats(Set<GridLocation> seatIds, Long movieId, Long userId) throws NotFoundException, InvalidStateException {
        Movie movie = movieDatabase.findById(movieId);
        Screen screen = movie.getScreen();
        Seat[][] layout = screen.getLayout();

        if (!areSeatsLockedByUser(seatIds, layout, userId))
            return -1l;

        // book seats
        setSeatStatus(seatIds, userId, layout, SeatStatus.BOOKED);

        // Save state
        screen.setLayout(layout);
        movie.setScreen(screen);
        movieDatabase.save(movie);

        // Create and persist a new booking
        Booking newBooking = Booking.builder()
                .userId(userId)
                .seatNumbers(new ArrayList<>(seatIds))
                .movieId(movieId)
                .build();
        return bookingDatabase.save(newBooking);
    }

    private boolean areSeatsAvailable(Set<GridLocation> seatIds, Seat[][] layout) {
        for (GridLocation seatId : seatIds) {
            SeatStatus seatState = layout[seatId.getX()][seatId.getY()].getStatus();
            if (!seatState.equals(SeatStatus.AVAILABLE))
                return false;
        }
        return true;
    }

    private void setSeatStatus(Set<GridLocation> seatIds, Long userId, Seat[][] layout, SeatStatus status) {
        for (GridLocation seatId : seatIds) {
            layout[seatId.getX()][seatId.getY()].setStatus(status);
            layout[seatId.getX()][seatId.getY()].setUserId(userId);
        }
    }

    private boolean areSeatsLockedByUser(Set<GridLocation> seatIds, Seat[][] layout, Long userId) {
        for (GridLocation seatId : seatIds) {
            Seat seatState = layout[seatId.getX()][seatId.getY()];
            if (!seatState.getStatus().equals(SeatStatus.LOCKED) || !seatState.getUserId().equals(userId))
                return false;
        }
        return true;
    }


    public Booking findById(long bookingId) {
        return bookingDatabase.findById(bookingId);
    }
}
