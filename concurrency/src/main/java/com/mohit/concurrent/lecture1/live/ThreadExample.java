package com.mohit.concurrent.lecture1.live;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mohit@interviewbit.com on 19/08/20
 **/
public class ThreadExample {

    class Temp{
        int lecNo;
        String date;
        int attendance;
    }

    public static void main(String[] args) throws InterruptedException {
        BufferedReader reader;
        Map<String, Integer> map = new HashMap<>();
        try {
            reader = new BufferedReader(new FileReader(
                    "/Users/mohityadav/Desktop/test.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                String key = split[1] + ":" + split[2];
                int max = Math.max(map.getOrDefault(key, 0), Integer.valueOf(split[0]));
                map.put(key, max);
                line = reader.readLine();
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String[] split = entry.getKey().split(":");
                System.out.println(String.format("%s,%s,%d", split[0], split[1], entry.getValue()));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
