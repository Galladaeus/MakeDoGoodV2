package com.example.annas.makedogood;

/**
 * Created by annas on 10/7/2017.
 */

public class Event {
    String name;
    String location;
    String date;
    String type;
    String description;

    Event() {
    }

    Event(String n, String l, String de, String t, String d) {
        name = n;
        location = l;
        date = de;
        type = t;
        description = d;
    }
}
