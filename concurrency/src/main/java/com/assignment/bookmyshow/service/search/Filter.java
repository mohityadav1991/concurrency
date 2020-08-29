package com.assignment.bookmyshow.service.search;

public class Filter {
  public Filter(String title) {
    this.title = title;
  }

  private String title;
  private String genre;
  private String language;
  private Integer price;
  private Integer time;

  public Filter(String title, String genre, String language, Integer price, Integer time) {
    this.title = title;
    this.genre = genre;
    this.language = language;
    this.price = price;
    this.time = time;
  }

  public Filter() {}

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
}
