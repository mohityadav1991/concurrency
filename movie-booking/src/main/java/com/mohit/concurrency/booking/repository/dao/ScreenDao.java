package com.mohit.concurrency.booking.repository.dao;

import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.repository.data.ScreenDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class ScreenDao implements BaseDao<Screen, ScreenDatabase> {

    @Autowired
    private ScreenDatabase screenDatabase;

    @Override
    public ScreenDatabase getDatabase() {
        return screenDatabase;
    }
}
