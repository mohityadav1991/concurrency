package com.mohit.concurrency.booking.api.serialiser;

import lombok.Builder;
import lombok.Data;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@Builder
public class MovieSearchFilter {
    private String title;
    private String genre;
    private String language;
}
