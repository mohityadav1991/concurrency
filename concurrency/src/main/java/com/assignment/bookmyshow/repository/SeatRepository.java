package com.assignment.bookmyshow.repository;

import com.assignment.bookmyshow.entity.ShowSeat;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatRepository {
  public static ConcurrentHashMap<Long, ShowSeat> seats = new ConcurrentHashMap<>();

  public static List<ShowSeat> getAllSeat() {
    return seats.values().stream().collect(Collectors.toList());
  }

  public static ShowSeat getSeat(long seatId) {
    return seats.get(seatId);
  }

  public static void saveShow(ShowSeat seat) {
    seats.putIfAbsent(seat.getShowSeatId(), seat);
  }
}
