package com.mohit.concurrency.booking.repository.dao;

import com.mohit.concurrency.booking.api.serialiser.MovieSearchFilter;
import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.repository.data.MovieDatabase;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class MovieDao implements BaseDao<Movie, MovieDatabase> {

    @Autowired
    private MovieDatabase movieDatabase;

    @Override
    public MovieDatabase getDatabase() {
        return movieDatabase;
    }

    public Set<Movie> findMovies(MovieSearchFilter filter) {
        Collection<Movie> movies = movieDatabase.findAll();
        if (!StringUtils.isEmpty(filter.getGenre())) {
            movies = CollectionUtils.intersection(movieDatabase.getGenreIndex().get(filter.getGenre()), movies);
        }
        if (!StringUtils.isEmpty(filter.getTitle())) {
            movies = CollectionUtils.intersection(movieDatabase.getGenreIndex().get(filter.getTitle()), movies);
        }
        if (!StringUtils.isEmpty(filter.getLanguage())) {
            movies = CollectionUtils.intersection(movieDatabase.getGenreIndex().get(filter.getLanguage()), movies);
        }
        return new HashSet<>(movies);
    }
}
