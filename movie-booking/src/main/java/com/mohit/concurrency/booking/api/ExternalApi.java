package com.mohit.concurrency.booking.api;

import com.mohit.concurrency.booking.api.serialiser.MovieSearchFilter;
import com.mohit.concurrency.booking.api.service.BookingService;
import com.mohit.concurrency.booking.api.service.SearchMovieService;
import com.mohit.concurrency.booking.model.entity.Booking;
import com.mohit.concurrency.booking.model.entity.GridLocation;
import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.model.exception.InvalidSearchRequestException;
import com.mohit.concurrency.booking.model.exception.InvalidStateException;
import com.mohit.concurrency.booking.model.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/

@Component
public class ExternalApi {

    @Autowired
    private SearchMovieService searchMovieService;

    @Autowired
    private BookingService bookingService;

    public Set<Movie> fetchEvents(MovieSearchFilter filter) throws InvalidSearchRequestException {
        return searchMovieService.handleSearchRequest(filter);
    }

    public boolean[][] fetchEventSeatLayout(long eventId) throws NotFoundException {
        return searchMovieService.handleFetchLayoutRequest(eventId);
    }

    public boolean lockSeats(Set<GridLocation> seatIds, Long eventId, Long userId) {
        return bookingService.lockSeats(seatIds, eventId, userId);
    }

    public Long bookSeats(Set<GridLocation> seatIds, Long eventId, Long userId) throws NotFoundException, InvalidStateException {
        return bookingService.bookSeats(seatIds, eventId, userId);
    }

    public Booking fetchBooking(long bookingId) {
        return bookingService.findById(bookingId);
    }
}
