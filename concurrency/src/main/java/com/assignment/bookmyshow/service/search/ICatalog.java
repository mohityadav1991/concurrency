package com.assignment.bookmyshow.service.search;

import com.assignment.bookmyshow.entity.MovieShow;

import java.util.List;

public interface ICatalog {
  List<MovieShow> searchEvents(Filter filter);

  List<MovieShow> searchEventsByTitle(String title);

  List<MovieShow> searchEventsByGenre(String genre);

  // add some more methods for searching on price,time etc
}
