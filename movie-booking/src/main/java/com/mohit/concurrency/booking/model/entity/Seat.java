package com.mohit.concurrency.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int value;
    private Long userId;
}
