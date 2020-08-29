package com.assignment.bookmyshow.repository;

import com.assignment.bookmyshow.entity.Movie;
import com.assignment.bookmyshow.entity.MovieShow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventShowRepository {

  public static ConcurrentHashMap<Long, MovieShow> show = new ConcurrentHashMap<>();

  public static List<MovieShow> getAllShow() {
    return show.values().stream().collect(Collectors.toList());
  }

  public static MovieShow getShow(long showId) {
    return show.get(showId);
  }

  public static void saveShow(MovieShow movie) {
    show.put(movie.getShowId(), movie);
  }

  public void deleteShow(MovieShow movieShow) {
    show.remove(movieShow);
  }
}
