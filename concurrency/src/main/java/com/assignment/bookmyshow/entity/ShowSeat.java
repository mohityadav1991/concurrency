package com.assignment.bookmyshow.entity;

public class ShowSeat {
  private long showSeatId;
  private boolean isReserved;
  private int seatNo;

  public long getShowSeatId() {
    return showSeatId;
  }

  public void setShowSeatId(long showSeatId) {
    this.showSeatId = showSeatId;
  }

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  public int getSeatNo() {
    return seatNo;
  }

  public void setSeatNo(int seatNo) {
    this.seatNo = seatNo;
  }
}
