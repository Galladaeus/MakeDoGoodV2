package com.example.annas.makedogood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CurrentEvents extends AppCompatActivity {

    static Event one = new Event("Trash Cleanup", "Narnia", "10/10/2010", "12:00am", "Clean trash in the river");
    static Event foodDrive = new Event("Cooking Occasion", "Hogwarts Dr, Switzerland", "10/10/2010", "12:00am", "Cool for the homeless");

    public static ArrayList<Event> arr = new ArrayList<Event>();
    public static ArrayList<String> mobileArray = new ArrayList<String>();

    static {
        arr.add(one);
        arr.add(foodDrive);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mobileArray.add(arr.get(0).name);
        mobileArray.add(arr.get(1).name);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_events);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Intent intent = new Intent(CurrentEvents.this, SpecificEvent.class);

                intent.putExtra("position", position);
                startActivity(intent);
            }

        });
    }

    public static void add(Event tempo) {
        arr.add(tempo);
        mobileArray.add(tempo.name);
    }
}
