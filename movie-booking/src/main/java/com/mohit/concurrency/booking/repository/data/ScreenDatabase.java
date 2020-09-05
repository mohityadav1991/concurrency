package com.mohit.concurrency.booking.repository.data;

import com.mohit.concurrency.booking.model.entity.Screen;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
@Component
public class ScreenDatabase implements BaseDatabase<Screen> {
    private List<Screen> screens;
    private Map<Long, Screen> screenIdIndex;

    public ScreenDatabase() {
        screenIdIndex = new HashMap<>();
        screens = new ArrayList<>();
    }

    @Override
    public boolean save(Screen s) {
        screens.add(s);
        screenIdIndex.put(s.getId(), s);
        return true;
    }

    @Override
    public Screen findById(Long id) {
        return screenIdIndex.get(id);
    }
}
