package com.mohit.concurrency.booking.api.service;

import com.mohit.concurrency.booking.model.entity.Booking;
import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.model.entity.Seat;
import com.mohit.concurrency.booking.model.exception.ErrorCodes;
import com.mohit.concurrency.booking.model.exception.InvalidStateException;
import com.mohit.concurrency.booking.model.exception.NotFoundException;
import com.mohit.concurrency.booking.repository.data.BookingDatabase;
import com.mohit.concurrency.booking.repository.data.ScreenDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class BookingService {

    @Autowired
    private BookingDatabase bookingDatabase;

    @Autowired
    private ScreenDatabase screenDatabase;

    public boolean lockSeats(Set<Long> seatIds, Long movieId, Long userId) {
        Seat[][] seatLayout = bookingDatabase.fetchLayout(movieId);
        if (seatLayout == null)
            seatLayout = initMovieLayout(movieId);
        int m = seatLayout.length;
        int n = seatLayout[0].length;
        int count = 0;
        List<Integer[]> seatIndexes = new ArrayList<>();
        if (checkSeatsAndFetchIndexes(seatIds, seatLayout, m, n, count, seatIndexes, null))
            return false;

        for (Integer[] seatIndex : seatIndexes) {
            seatLayout[seatIndex[0]][seatIndex[1]].setUserId(userId);
            seatLayout[seatIndex[0]][seatIndex[1]].setValue(1);
        }
        bookingDatabase.saveLayout(movieId, seatLayout);
        return true;
    }

    public long bookSeats(Set<Long> seatIds, Long movieId, Long userId) throws NotFoundException, InvalidStateException {
        Seat[][] seatLayout = bookingDatabase.fetchLayout(movieId);
        if (seatLayout == null)
            throw new NotFoundException(ErrorCodes.E102);
        int m = seatLayout.length;
        int n = seatLayout[0].length;
        int count = 0;
        List<Integer[]> seatIndexes = new ArrayList<>();
        if (checkSeatsAndFetchIndexes(seatIds, seatLayout, m, n, count, seatIndexes, userId))
            return -1;
        for (Integer[] seatIndex : seatIndexes) {
            seatLayout[seatIndex[0]][seatIndex[1]].setValue(2);
        }
        bookingDatabase.saveLayout(movieId, seatLayout);
        Booking b = Booking.builder()
                .movieId(movieId)
                .seatNumbers(new ArrayList<>(seatIds))
                .userId(userId)
                .build();
        long id = bookingDatabase.getSize() + 1;
        b.setId(id);
        bookingDatabase.save(b);
        return id;
    }

    private boolean checkSeatsAndFetchIndexes(Set<Long> seatIds, Seat[][] seatLayout, int m, int n, int count, List<Integer[]> seatIndexes, Long userId) {
        // Check if the seats are vacant
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count++;
                if (seatIds.contains(count)) {
                    if ((userId == null && seatLayout[i][j].getValue() != 0) ||
                            (userId != null && seatLayout[i][j].getValue() != 1) && userId.equals(seatLayout[i][j])) {
                        return true;
                    } else {
                        Integer[] indexes = {i, j};
                        seatIndexes.add(indexes);
                    }
                }
            }
        }
        // Invalid seat numbers are passed.
        if (seatIds.size() != seatIndexes.size()) {
            return true;
        }
        return false;
    }

    private Seat[][] initMovieLayout(Long movieId) {
        Screen screen = screenDatabase.findById(movieId);
        boolean[][] layout = screen.getLayout();
        int m = layout.length;
        int n = layout[0].length;
        Seat[][] seatLayout = new Seat[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                seatLayout[i][j] = layout[i][j]
                        ? new Seat(0, null)
                        : new Seat(-1, null);
        bookingDatabase.saveLayout(movieId, seatLayout);
        return seatLayout;
    }

    public Booking findById(long bookingId) {
        return bookingDatabase.findById(bookingId);
    }
}
