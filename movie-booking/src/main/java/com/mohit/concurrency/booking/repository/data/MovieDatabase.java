package com.mohit.concurrency.booking.repository.data;

import com.mohit.concurrency.booking.model.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class MovieDatabase implements BaseDatabase<Movie>{
    private List<Movie> movies;
    private Map<Long, Movie> movieIdIndex;
    private Map<String, Set<Movie>> titleIndex;
    private Map<String, Set<Movie>> genreIndex;
    private Map<String, Set<Movie>> languageIndex;

    public MovieDatabase() {
        movies = new ArrayList<>();
        movieIdIndex = new HashMap<>();
        titleIndex = new HashMap<>();
        genreIndex = new HashMap<>();
        languageIndex = new HashMap<>();
    }

    @Override
    public boolean save(Movie m) {
        movies.add(m);
        movieIdIndex.put(m.getId(), m);
        String title = m.getTitle().toLowerCase();

        // Create title index
        Set<Movie> moviesTitle;
        if (titleIndex.containsKey(title)) {
            moviesTitle = titleIndex.get(title);
        } else {
            moviesTitle = new HashSet<>();
        }
        moviesTitle.add(m);
        titleIndex.put(title, moviesTitle);

        // Create genre index
        Set<Movie> moviesGenre;
        if (genreIndex.containsKey(title)) {
            moviesGenre = genreIndex.get(title);
        } else {
            moviesGenre = new HashSet<>();
        }
        moviesGenre.add(m);
        genreIndex.put(title, moviesGenre);

        // Create language index
        Set<Movie> moviesLang;
        if (languageIndex.containsKey(title)) {
            moviesLang = languageIndex.get(title);
        } else {
            moviesLang = new HashSet<>();
        }
        moviesGenre.add(m);
        languageIndex.put(title, moviesLang);
        return true;
    }

    @Override
    public Movie findById(Long id) {
        return movieIdIndex.get(id);
    }

    public Map<String, Set<Movie>> getTitleIndex() {
        return titleIndex;
    }

    public Map<String, Set<Movie>> getGenreIndex() {
        return genreIndex;
    }

    public Map<String, Set<Movie>> getLanguageIndex() {
        return languageIndex;
    }
}
