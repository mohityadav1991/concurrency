package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.AccountStatus;

import java.util.Comparator;

public class Account implements Comparator<Account> {
  private long id;
  private String userName;
  private String password;
  private AccountStatus status;
  private String name;
  private String email;
  private String phone;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public void setStatus(AccountStatus status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Account(long id, String userName, String password, AccountStatus status, String name, String email, String phone) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.status = status;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  @Override
  public int compare(Account o1, Account o2) {
    return o1.getId() == o2.getId() ? 1 : 0;
  }
}
