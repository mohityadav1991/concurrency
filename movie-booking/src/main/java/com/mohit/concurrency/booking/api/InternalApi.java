package com.mohit.concurrency.booking.api;

import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.repository.dao.MovieDao;
import com.mohit.concurrency.booking.repository.dao.ScreenDao;
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

    @Autowired
    private ScreenDao screenDao;

    public Boolean addOrUpdateMovie(List<Movie> eventList) {
        movieDao.saveOrUpdateAll(eventList);
        return true;
    }

    public Boolean addOrUpdateScreen(List<Screen> screens) {
        screenDao.saveOrUpdateAll(screens);
        return true;
    }
}
