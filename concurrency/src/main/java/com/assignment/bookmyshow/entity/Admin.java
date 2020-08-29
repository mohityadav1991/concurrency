package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.AccountStatus;

public class Admin extends Account {

  public Admin(long id, String userName, String password, AccountStatus status, String name, String email, String phone) {
    super(id, userName, password, status, name, email, phone);
  }

  boolean addMovie(Movie movie) {
    return false;
  }

  boolean addShow(MovieShow show) {
    return false;
  }
}
