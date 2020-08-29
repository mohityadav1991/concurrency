package com.assignment.bookmyshow.service.booking;

import com.assignment.bookmyshow.repository.BookingRepository;

public class BookingService {
  BookingRepository bookingRepository;

  public BookingService(BookingRepository bookingRepository) {
    this.bookingRepository = bookingRepository;
  }

  public Long bookEvent(Long seatId, Long eventId, Long threatreId, Long user_id) {
    return null;
  }
}
