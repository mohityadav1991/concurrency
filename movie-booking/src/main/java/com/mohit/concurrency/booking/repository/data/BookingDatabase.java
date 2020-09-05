package com.mohit.concurrency.booking.repository.data;

import com.mohit.concurrency.booking.model.entity.Booking;
import com.mohit.concurrency.booking.model.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class BookingDatabase implements BaseDatabase<Booking> {

    private List<Booking> bookings;
    private Map<Long, Booking> bookingIdIndex;
    private Map<Long, Seat[][]> eventBookingMap;

    public BookingDatabase() {
        bookings = new ArrayList<>();
        bookingIdIndex = new HashMap<>();
        eventBookingMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean save(Booking b) {
        bookings.add(b);
        bookingIdIndex.put(b.getId(), b);
        return true;
    }

    public boolean saveLayout(Long movieId, Seat[][] layout) {
        eventBookingMap.put(movieId, layout);
        return true;
    }

    public long getSize() {
        return bookings.size();
    }

    @Override
    public Booking findById(Long id) {
        return bookingIdIndex.get(id);
    }

    public Seat[][] fetchLayout(Long movieId) {
        return eventBookingMap.get(movieId);
    }
}
