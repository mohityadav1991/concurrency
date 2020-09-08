package com.mohit.concurrency.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
