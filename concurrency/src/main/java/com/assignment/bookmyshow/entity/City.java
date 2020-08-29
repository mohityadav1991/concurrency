package com.assignment.bookmyshow.entity;

public class City {
  private String name;
  private String state;
  private String pinCode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pinCode) {
    if (pinCode.length() != 6) throw new IllegalArgumentException("Incorrect Pin Code");
    this.pinCode = pinCode;
  }
}
