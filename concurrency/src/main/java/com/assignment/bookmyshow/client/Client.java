package com.assignment.bookmyshow.client;

import com.assignment.bookmyshow.entity.CinemaHallSeat;
import com.assignment.bookmyshow.entity.MovieShow;
import com.assignment.bookmyshow.repository.EventShowRepository;
import com.assignment.bookmyshow.service.event.EventShowService;
import com.assignment.bookmyshow.service.search.Filter;

import java.util.Arrays;
import java.util.List;

public class Client {
  public static void main(String[] args) {
    DataInitializer.init();
    EventShowService eventShowService = new EventShowService(new EventShowRepository());
    Filter filter = new Filter("Transporter");
    List<MovieShow> shows = eventShowService.fetchEvents(filter);
    System.out.println(shows);
    Boolean[][] layout = eventShowService.fetchEventSeatLayout(1);
    System.out.println(Arrays.deepToString(layout));
  }
}
