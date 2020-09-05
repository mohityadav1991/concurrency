package com.mohit.concurrency.booking.repository.dao;

import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.repository.data.MovieDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class MovieDao implements BaseDao<Movie, MovieDatabase> {

    @Autowired
    private MovieDatabase movieDatabase;

    public Set<Movie> filterByTitle(String title, Set<Movie> movieIds) {
        Set<Movie> movies = movieDatabase.getTitleIndex().get(title.toLowerCase());
        return getFilteredList(movieIds, movies);
    }

    public Set<Movie> filterByGenre(String genre, Set<Movie> movieIds) {
        Set<Movie> movies = movieDatabase.getGenreIndex().get(genre.toLowerCase());
        return getFilteredList(movieIds, movies);
    }

    public Set<Movie> filterByLanguage(String language, Set<Movie> movieIds) {
        Set<Movie> movies = movieDatabase.getLanguageIndex().get(language.toLowerCase());
        return getFilteredList(movieIds, movies);
    }

    private Set<Movie> getFilteredList(Set<Movie> movieIds, Set<Movie> movies) {
        if(movieIds == null || movieIds.isEmpty())
            return movies;
        return movies
                .stream()
                .filter(movie -> movieIds.contains(movie))
                .collect(Collectors.toSet());
    }

    @Override
    public MovieDatabase getDatabase() {
        return movieDatabase;
    }
}
