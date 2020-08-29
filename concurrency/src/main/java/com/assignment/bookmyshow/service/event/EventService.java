package com.assignment.bookmyshow.service.event;

import com.assignment.bookmyshow.entity.Event;
import com.assignment.bookmyshow.entity.Movie;
import com.assignment.bookmyshow.repository.EventRepository;

import java.util.List;

public class EventService {
  EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public boolean addEvent(Movie movie) {
    eventRepository.saveMovie(movie);
    return true;
  }

  public boolean deleteEvent(Movie movie) {
    eventRepository.deleteMovie(movie);
    return true;
  }

  public Event getEvent(long id) {
    return eventRepository.getMovie(id);
  }

  public List<Event> getAllEvent() {
    return eventRepository.getAllMovie();
  }
}
