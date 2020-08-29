package com.assignment.bookmyshow.service.event;

import com.assignment.bookmyshow.entity.CinemaHallSeat;
import com.assignment.bookmyshow.entity.Event;
import com.assignment.bookmyshow.entity.MovieShow;
import com.assignment.bookmyshow.repository.EventShowRepository;
import com.assignment.bookmyshow.service.search.Catalog;
import com.assignment.bookmyshow.service.search.Filter;

import java.util.List;

public class EventShowService {
  EventShowRepository eventShowRepository;

  public EventShowService(EventShowRepository eventShowRepository) {
    this.eventShowRepository = eventShowRepository;
  }

  public boolean addEventShow(MovieShow movieShow) {
    eventShowRepository.saveShow(movieShow);
    return true;
  }

  public boolean addEventShow(List<MovieShow> movieShow) {
    movieShow.stream().forEach(e -> eventShowRepository.saveShow(e));
    return true;
  }

  public boolean deleteEventShow(MovieShow movieShow) {
    eventShowRepository.deleteShow(movieShow);
    return true;
  }

  public List<MovieShow> fetchEvents(Filter filter) {
    Catalog catalog = new Catalog();
    return catalog.searchEvents(filter);
  }

  public Boolean[][] fetchEventSeatLayout(long eventId) {
    MovieShow show = eventShowRepository.getShow(eventId);
    CinemaHallSeat[][] seatLayout = show.getPlayedAtHall().getSeats();
    Boolean[][] finalLayout = new Boolean[seatLayout.length][];
    for (int i = 0; i < finalLayout.length; ++i) {
      finalLayout[i] = new Boolean[seatLayout[i].length];
      for (int j = 0; j < finalLayout[i].length; ++j) {
        finalLayout[i][j] = seatLayout[i][j].isReserved();
      }
    }
    return finalLayout;
  }
}
