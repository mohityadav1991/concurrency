package com.assignment.bookmyshow.repository;

import com.assignment.bookmyshow.entity.Event;
import com.assignment.bookmyshow.entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventRepository {
  public static ConcurrentHashMap<Long, Movie> movies = new ConcurrentHashMap<>();

  public static List<Event> getAllMovie() {

    return movies.values().stream().collect(Collectors.toList());
  }

  public static Movie getMovie(Long id) {
    return movies.get(id);
  }

  public static void saveMovie(Movie movie) {
    movies.putIfAbsent(movie.getMid(), movie);
  }

  public void deleteMovie(Movie movie) {
    movies.remove(movie);
  }

  public Boolean addOrUpdateEvents(List<Event> events) {
    // TODO: can be done in better way
    events.stream().forEach(e -> saveMovie((Movie) e));
    return true;
  }
}
