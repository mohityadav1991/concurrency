package com.assignment.bookmyshow.client;

import com.assignment.bookmyshow.entity.*;
import com.assignment.bookmyshow.entity.constants.AccountStatus;
import com.assignment.bookmyshow.entity.constants.Genre;
import com.assignment.bookmyshow.entity.constants.Language;
import com.assignment.bookmyshow.entity.constants.SeatType;
import com.assignment.bookmyshow.repository.AccountRepository;
import com.assignment.bookmyshow.repository.BookingRepository;
import com.assignment.bookmyshow.repository.EventRepository;
import com.assignment.bookmyshow.repository.EventShowRepository;
import com.assignment.bookmyshow.service.booking.BookingService;
import com.assignment.bookmyshow.service.event.EventService;
import com.assignment.bookmyshow.service.event.EventShowService;
import com.assignment.bookmyshow.service.user.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataInitializer {

  protected static void init() {

    EventService eventService = new EventService(new EventRepository());
    EventShowService eventShowService = new EventShowService(new EventShowRepository());
    UserService userService = new UserService(new AccountRepository());
    Customer customer =
        new Customer(
            46456, "singhpiyush", "1234567", AccountStatus.ACTIVE, "Piyush", "a.b@c", "9599320311");

    // creating seats inside a cinemahall
    CinemaHallSeat s1 = new CinemaHallSeat(SeatType.REGULAR, 1, false);
    CinemaHallSeat s2 = new CinemaHallSeat(SeatType.REGULAR, 2, false);
    CinemaHallSeat s3 = new CinemaHallSeat(SeatType.PREMIUM, 3, false);
    CinemaHallSeat s4 = new CinemaHallSeat(SeatType.PREMIUM, 4, false);
    CinemaHallSeat s5 = new CinemaHallSeat(SeatType.PREMIUM, 5, false);

    CinemaHall cinemaHall =
        new CinemaHall(123, "PVR Logix", 5, new CinemaHallSeat[][] {{s3, s4, s5}, {s1, s2, s3}});

    Movie movie1 =
        new Movie(
            123,
            "Transporter",
            "Action Movie by Jason",
            120,
            Language.ENGLISH,
            new Date(),
            Genre.ACTION);
    List<MovieShow> shows = new ArrayList<>();
    MovieShow movieShow1 = new MovieShow(1, 10, 200, cinemaHall, movie1);
    MovieShow movieShow2 = new MovieShow(2, 12, 250, cinemaHall, movie1);
    MovieShow movieShow3 = new MovieShow(3, 14, 300, cinemaHall, movie1);
    MovieShow movieShow4 = new MovieShow(4, 16, 400, cinemaHall, movie1);
    shows.add(movieShow1);
    shows.add(movieShow2);
    shows.add(movieShow3);
    shows.add(movieShow4);
    cinemaHall.setShows(shows);
    movie1.setShows(shows);

    eventService.addEvent(movie1);
    eventShowService.addEventShow(shows);
    userService.addUser(customer);
  }
}
