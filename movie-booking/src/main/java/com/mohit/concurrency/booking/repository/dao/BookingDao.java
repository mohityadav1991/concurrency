package com.mohit.concurrency.booking.repository.dao;

import com.mohit.concurrency.booking.model.entity.Booking;
import com.mohit.concurrency.booking.repository.data.BookingDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class BookingDao implements BaseDao<Booking, BookingDatabase> {

    @Autowired
    private BookingDatabase database;

    @Override
    public BookingDatabase getDatabase() {
        return database;
    }
}
