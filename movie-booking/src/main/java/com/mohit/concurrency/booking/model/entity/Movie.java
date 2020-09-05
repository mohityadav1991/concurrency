package com.mohit.concurrency.booking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends BaseEntity{
    private Long screenId;
    private String title;
    private String genre;
    private String language;
}
