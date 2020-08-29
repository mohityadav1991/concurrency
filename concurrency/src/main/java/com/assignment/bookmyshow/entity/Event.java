package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.Genre;
import com.assignment.bookmyshow.entity.constants.Language;

import java.util.Date;
import java.util.List;

public class Event {
  private String title;
  private String description;
  private Language language;
  private Date releaseDate;
  private Genre genre;

  public Event(String title, String description, Language language, Date releaseDate, Genre genre) {
    this.title = title;
    this.description = description;
    this.language = language;
    this.releaseDate = releaseDate;
    this.genre = genre;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Language getLanguage() {
    return language;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public List<MovieShow> getShows() {
    return null;
  }

  @Override
  public String toString() {
    return "title='" + title + '\'' + ", language='" + language + '\'' + ", genre='" + genre;
  }
}
