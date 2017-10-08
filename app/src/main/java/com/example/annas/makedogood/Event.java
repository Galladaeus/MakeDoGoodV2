package com.example.annas.makedogood;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by annas on 10/7/2017.
 */
@IgnoreExtraProperties
public class Event {
    String name;
    String description;
    String address;
    String date;
    String time;
    String type;

    public Event() {
    }

    public Event(String name, String description, String address, String date, String time, String type) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    @Exclude
    public Map<String, Object> eventDataToMap() {

        HashMap<String, Object> eventData = new HashMap<>();

        eventData.put("name", name);
        eventData.put("description", description);
        eventData.put("address", address);
        eventData.put("date", date);
        eventData.put("time", time);
        eventData.put("type", type);

        return eventData;
    }
}
