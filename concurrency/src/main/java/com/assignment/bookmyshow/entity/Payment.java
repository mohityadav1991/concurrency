package com.assignment.bookmyshow.entity;

import com.assignment.bookmyshow.entity.constants.PaymentStatus;

import java.util.Date;

public class Payment {
  private double amount;
  private Date createdOn;
  private long transactionId;
  private PaymentStatus status;

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }
}
