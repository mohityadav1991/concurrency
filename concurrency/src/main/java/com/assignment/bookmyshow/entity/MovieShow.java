package com.assignment.bookmyshow.entity;

import java.util.Map;

public class MovieShow {
  private long showId;
  private Integer time;
  private Integer price;
  private CinemaHall playedAtHall;
  private Movie movie;


  public MovieShow(long showId, Integer time, Integer price, CinemaHall playedAtHall, Movie movie) {
    this.showId = showId;
    this.time = time;
    this.price = price;
    this.playedAtHall = playedAtHall;
    this.movie = movie;
  }

  public long getShowId() {
    return showId;
  }

  public void setShowId(long showId) {
    this.showId = showId;
  }

  public CinemaHall getPlayedAtHall() {
    return playedAtHall;
  }

  public void setPlayedAtHall(CinemaHall playedAtHall) {
    this.playedAtHall = playedAtHall;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "MovieShow{"
        + "showId="
        + showId
        + ", time="
        + time
        + ", price="
        + price
        + ", playedAtHall="
        + playedAtHall
        + ", movie="
        + movie
        + '}'
        + "\n";
  }
}
