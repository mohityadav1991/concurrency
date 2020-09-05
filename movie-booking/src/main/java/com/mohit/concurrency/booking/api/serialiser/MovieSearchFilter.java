package com.mohit.concurrency.booking.api.serialiser;

import lombok.Data;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
public class MovieSearchFilter {
    private String title;
    private String genre;
    private String language;
}
