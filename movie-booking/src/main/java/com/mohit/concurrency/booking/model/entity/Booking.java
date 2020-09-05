package com.mohit.concurrency.booking.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@Builder
public class Booking extends BaseEntity{
    private Long userId;
    private Long movieId;
    private List<Long> seatNumbers;
}
