package com.mohit.concurrency.booking.api;

import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.repository.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class InternalApi {

    @Autowired
    private MovieDao movieDao;

    public Boolean addOrUpdateMovie(List<Movie> eventList) {
        movieDao.saveOrUpdateAll(eventList);
        return true;
    }

}
