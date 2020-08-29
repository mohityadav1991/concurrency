package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.service.search.Filter;

import java.util.Arrays;
import java.util.List;

public class CinemaHall {
  private long cinemaHallId;
  private String name;
  private int totalSeats;

  private CinemaHallSeat[][] seats;
  private List<MovieShow> shows;

  public CinemaHall(long cinemaHallId, String name, int totalSeats, CinemaHallSeat[][] seats) {
    this.cinemaHallId = cinemaHallId;
    this.name = name;
    this.totalSeats = totalSeats;
    this.seats = seats;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public void setTotalSeats(int totalSeats) {
    this.totalSeats = totalSeats;
  }

  public CinemaHallSeat[][] getSeats() {
    return seats;
  }

  public void setSeats(CinemaHallSeat[][] seats) {
    this.seats = seats;
  }

  public List<MovieShow> getShows() {
    return shows;
  }

  public void setShows(List<MovieShow> shows) {
    this.shows = shows;
  }

  public long getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(long cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  @Override
  public String toString() {
    return "{"
        + "cinemaHallId="
        + cinemaHallId
        + ", name='"
        + name
        + '\''
        + ", totalSeats="
        + totalSeats
        + '}';
  }
}
