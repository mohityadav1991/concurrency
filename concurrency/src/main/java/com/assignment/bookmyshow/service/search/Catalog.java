package com.assignment.bookmyshow.service.search;

import com.assignment.bookmyshow.entity.MovieShow;
import com.assignment.bookmyshow.repository.EventShowRepository;

import java.util.List;
import java.util.stream.Collectors;

// It is a good usecase of Trie.
public class Catalog implements ICatalog {
  @Override
  public List<MovieShow> searchEvents(Filter filter) {
    return EventShowRepository.getAllShow().stream()
        .filter(
            c ->
                (filter.getGenre() != null
                        ? filter.getGenre().equalsIgnoreCase(c.getMovie().getGenre().toString())
                        : true)
                    && (filter.getLanguage() != null
                        ? filter
                            .getLanguage()
                            .equalsIgnoreCase(c.getMovie().getLanguage().toString())
                        : true)
                    && (filter.getTitle() != null
                        ? filter.getTitle().equalsIgnoreCase(c.getMovie().getTitle())
                        : true)
                    && (filter.getPrice() != null ? filter.getPrice() == c.getPrice() : true))
        .collect(Collectors.toList());
  }

  @Override
  public List<MovieShow> searchEventsByTitle(String title) {
    return EventShowRepository.getAllShow().stream()
        .filter(c -> title.equalsIgnoreCase(c.getMovie().getTitle()))
        .collect(Collectors.toList());
  }

  @Override
  public List<MovieShow> searchEventsByGenre(String genre) {
    return EventShowRepository.getAllShow().stream()
        .filter(c -> genre.equalsIgnoreCase(c.getMovie().getGenre().toString()))
        .collect(Collectors.toList());
  }
}
