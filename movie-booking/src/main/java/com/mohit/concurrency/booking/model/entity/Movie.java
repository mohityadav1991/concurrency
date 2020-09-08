package com.mohit.concurrency.booking.model.entity;

import lombok.*;

import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Movie extends BaseEntity{
    private Screen screen;
    private String title;
    private String genre;
    private String language;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + this.getId() +
                ", \"screen\": \"" + screen.getId() + '\"' +
                ", \"title\": \"" + title + '\"' +
                ", \"genre\": \"" + genre + '\"' +
                ", \"language\": \"" + language + '\"' +
                '}';
    }
}
