package com.assignment.bookmyshow.entity;

import java.util.List;

public class Cinema {
  private long cinemaId;
  private String name;
  private int totalCinemaHalls;
  private Address location;
  private List<CinemaHall> halls;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTotalCinemaHalls() {
    return totalCinemaHalls;
  }

  public void setTotalCinemaHalls(int totalCinemaHalls) {
    this.totalCinemaHalls = totalCinemaHalls;
  }

  public Address getLocation() {
    return location;
  }

  public void setLocation(Address location) {
    this.location = location;
  }

  public List<CinemaHall> getHalls() {
    return halls;
  }

  public void setHalls(List<CinemaHall> halls) {
    this.halls = halls;
  }

  public long getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(long cinemaId) {
    this.cinemaId = cinemaId;
  }
}
