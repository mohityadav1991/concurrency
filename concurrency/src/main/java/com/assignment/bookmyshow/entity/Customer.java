package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.AccountStatus;

public class Customer extends Account {

  public Customer(
      long id,
      String userName,
      String password,
      AccountStatus status,
      String name,
      String email,
      String phone) {
    super(id, userName, password, status, name, email, phone);
  }
}
