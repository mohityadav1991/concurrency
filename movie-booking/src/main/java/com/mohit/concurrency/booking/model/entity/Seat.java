package com.mohit.concurrency.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@AllArgsConstructor
public class Seat {
    private SeatStatus status;
    private Long userId;

    public Seat(SeatStatus status) {
        this.status = status;
    }

    public Seat() {
        this.status = SeatStatus.AVAILABLE;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
