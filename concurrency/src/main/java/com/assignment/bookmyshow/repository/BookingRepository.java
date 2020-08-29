package com.assignment.bookmyshow.repository;

import com.assignment.bookmyshow.entity.Account;
import com.assignment.bookmyshow.entity.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepository {

  // this acts as In-Memory DB
  public static ConcurrentHashMap<Long, Booking> bookings = new ConcurrentHashMap<>();

  public static List<Booking> getAllBookings() {
    return bookings.values().stream().collect(Collectors.toList());
  }

  public static Booking getBooking(long bookingID) {
    return bookings.get(bookingID);
  }

  public static void saveBooking(Booking u) {
    bookings.putIfAbsent(u.getBookingID(), u);
  }
}
