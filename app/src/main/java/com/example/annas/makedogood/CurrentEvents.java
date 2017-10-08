package com.example.annas.makedogood;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentEvents extends AppCompatActivity {

    private DatabaseReference mDatabase;

    static Event one = new Event("Trash Cleanup", "Clean trash in the river", "Narnia", "10/10/2010", "12:00am", "Clean-up");
    static Event foodDrive = new Event("Cooking Occasion", "Volunteer at the soup kitchen for homeless wizards", "Hogwarts Dr, Switzerland", "10/10/2010", "12:00am", "Food Service");

    public static ArrayList<Event> arr = new ArrayList<Event>();
    public static ArrayList<String> mobileArray = new ArrayList<String>();

    static {
        arr.add(one);
        arr.add(foodDrive);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance().getReference("/events/");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot event: dataSnapshot.getChildren()) {
                    // Adds events to arr
                    arr.add(event.getValue(Event.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


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
