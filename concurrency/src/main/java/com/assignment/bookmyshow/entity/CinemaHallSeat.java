package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.SeatType;

public class CinemaHallSeat {
  private SeatType type;
  private long seatId;
  private boolean isReserved;

  public CinemaHallSeat(SeatType type, long seatId, boolean isReserved) {
    this.type = type;
    this.seatId = seatId;
    this.isReserved = isReserved;
  }

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  public long getSeatId() {
    return seatId;
  }

  public void setSeatId(long seatId) {
    this.seatId = seatId;
  }

  public SeatType getType() {
    return type;
  }

  public void setType(SeatType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "CinemaHallSeat{"
        + "type="
        + type
        + ", seatId="
        + seatId
        + ", isReserved="
        + isReserved
        + '}';
  }
}
