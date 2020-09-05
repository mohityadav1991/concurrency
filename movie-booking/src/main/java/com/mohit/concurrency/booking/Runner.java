package com.mohit.concurrency.booking;

import com.mohit.concurrency.booking.api.ExternalApi;
import com.mohit.concurrency.booking.api.InternalApi;
import com.mohit.concurrency.booking.model.entity.Movie;
import com.mohit.concurrency.booking.model.entity.Screen;
import com.mohit.concurrency.booking.repository.data.MovieDatabase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mohit@interviewbit.com on 04/09/20
 **/
public class Runner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        ExternalApi externalApi = applicationContext.getBean(ExternalApi.class);
        InternalApi internalApi = applicationContext.getBean(InternalApi.class);

        boolean[][] layout1 = {
                {true, true, true, true, true, true, true},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
        };

        boolean[][] layout2 = {
                {true, true, true, true, true, true, true},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
                {false, true, true, false, true, true, false},
        };
        Screen s1 = new Screen(1l, layout1);
        Screen s2 = new Screen(2l, layout2);

        List<Screen> screens = new ArrayList<>();
        screens.add(s1);
        screens.add(s2);

        Movie m1 = new Movie(1l, "T1", "G1", "L1");
        m1.setId(1l);
        Movie m2 = new Movie(2l, "T1", "G1", "L1");
        m2.setId(2l);
        Movie m3 = new Movie(1l, "T1", "G1", "L1");
        m3.setId(3l);
        Movie m4 = new Movie(1l, "T1", "G1", "L1");
        m4.setId(4l);
        Movie m5 = new Movie(2l, "T1", "G1", "L1");
        m5.setId(5l);

        
        internalApi.addOrUpdateScreen(screens);
        internalApi.addOrUpdateMovie()

    }
}
