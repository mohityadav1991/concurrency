package com.mohit.concurrency.booking.model.entity;

import lombok.Data;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
public class Screen extends BaseEntity{
    private Seat[][] layout;
    public Screen() {
    }

    public Screen(Long id, Seat[][] layout) {
        super(id);
        this.layout = layout;
    }
}
