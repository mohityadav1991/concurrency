package com.mohit.concurrency.booking.repository.data;

import com.mohit.concurrency.booking.model.entity.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class BookingDatabase implements BaseDatabase<Booking> {

    private List<Booking> bookings;
    private Map<Long, Booking> bookingIdIndex;

    public BookingDatabase() {
        bookings = new ArrayList<>();
        bookingIdIndex = new HashMap<>();
    }

    @Override
    public long save(Booking b) {
        long id = generateId();
        b.setId(id);
        bookings.add(b);
        bookingIdIndex.put(b.getId(), b);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    private long generateId() {
        return bookings.size() + 1;
    }

    @Override
    public Booking findById(Long id) {
        return bookingIdIndex.get(id);
    }

}
