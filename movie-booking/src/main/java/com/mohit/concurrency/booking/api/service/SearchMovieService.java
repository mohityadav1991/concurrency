package com.mohit.concurrency.booking.api.service;

import com.mohit.concurrency.booking.api.serialiser.MovieSearchFilter;
import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.model.entity.Seat;
import com.mohit.concurrency.booking.model.entity.SeatStatus;
import com.mohit.concurrency.booking.model.exception.ErrorCodes;
import com.mohit.concurrency.booking.model.exception.InvalidSearchRequestException;
import com.mohit.concurrency.booking.model.exception.NotFoundException;
import com.mohit.concurrency.booking.repository.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class SearchMovieService {

    @Autowired
    private MovieDao movieDao;

    public Set<Movie> handleSearchRequest(MovieSearchFilter filter) throws InvalidSearchRequestException {
        validateInput(filter);
        Set<Movie> movies = new HashSet<>();
        if (!StringUtils.isEmpty(filter.getTitle())) {
            movies = movieDao.filterByTitle(filter.getTitle(), movies);
        }
        if (!StringUtils.isEmpty(filter.getGenre())) {
            movies = movieDao.filterByGenre(filter.getGenre(), movies);
        }
        if (!StringUtils.isEmpty(filter.getLanguage())) {
            movies = movieDao.filterByLanguage(filter.getLanguage(), movies);
        }
        return movies;
    }

    private void validateInput(MovieSearchFilter filter) throws InvalidSearchRequestException {
        if (StringUtils.isEmpty(filter.getGenre()) &&
                StringUtils.isEmpty(filter.getLanguage()) &&
                StringUtils.isEmpty(filter.getTitle()))
            throw new InvalidSearchRequestException(ErrorCodes.E100);
    }

    public boolean[][] handleFetchLayoutRequest(long eventId) throws NotFoundException {
        Movie movie = movieDao.findOne(eventId);
        if (movie == null)
            throw new NotFoundException(ErrorCodes.E101);
        Screen screen = movie.getScreen();
        Seat[][] screenLayout = screen.getLayout();
        int rowCount = screenLayout.length;
        int colCount = screenLayout[0].length;
        boolean[][] layout = new boolean[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                layout[i][j] = screenLayout[i][j].getStatus().equals(SeatStatus.AVAILABLE);
            }
        }
        return layout;
    }
}
