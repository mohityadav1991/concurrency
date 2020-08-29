package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.Genre;
import com.assignment.bookmyshow.entity.constants.Language;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Movie extends Event implements Comparator<Movie> {
  private long mid;
  private int durationInMins;
  private List<MovieShow> shows;

  public Movie(
      long mid,
      String title,
      String description,
      int durationInMins,
      Language language,
      Date releaseDate,
      Genre genre) {
    super(title, description, language, releaseDate, genre);
    this.durationInMins = durationInMins;
    this.mid = mid;
  }

  public int getDurationInMins() {
    return durationInMins;
  }

  public void setDurationInMins(int durationInMins) {
    this.durationInMins = durationInMins;
  }

  public void setShows(List<MovieShow> shows) {
    this.shows = shows;
  }

  public List<MovieShow> getShows() {
    return null;
  }

  public long getMid() {
    return mid;
  }

  public void setMid(long mid) {
    this.mid = mid;
  }



  @Override
  public int compare(Movie o1, Movie o2) {
    return o1.getMid() == o2.getMid() ? 1 : 0;
  }
}
