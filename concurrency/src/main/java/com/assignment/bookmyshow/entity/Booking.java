package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.BookingStatus;

import java.util.Date;
import java.util.List;

public class Booking {
  private long bookingID;
  private int numberOfSeats;
  private Date createdOn;
  private BookingStatus status;

  private MovieShow show;
  private List<ShowSeat> seats;
  private Payment payment;

  public long getBookingID() {
    return bookingID;
  }

  public void setBookingID(long bookingID) {
    this.bookingID = bookingID;
  }

  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }

  public MovieShow getShow() {
    return show;
  }

  public void setShow(MovieShow show) {
    this.show = show;
  }

  public List<ShowSeat> getSeats() {
    return seats;
  }

  public void setSeats(List<ShowSeat> seats) {
    this.seats = seats;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }
}
