package com.example.annas.makedogood;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        final Button button = (Button) findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText name = (EditText) findViewById(R.id.event_name);
                final String eventName = name.getText().toString();

                final EditText address = (EditText) findViewById(R.id.event_address);
                final String eventAddress = address.getText().toString();

                final EditText date = (EditText) findViewById(R.id.event_date);
                final String eventDate = date.getText().toString();

                final EditText time = (EditText) findViewById(R.id.event_time);
                final String eventTime = time.getText().toString();

                final EditText description = (EditText) findViewById(R.id.event_description);
                final String eventDescription = description.getText().toString();

                final EditText type = (EditText) findViewById(R.id.event_type);
                final String eventType = type.getText().toString();

                Event tempo = new Event(eventName, eventDescription, eventAddress, eventDate, eventTime, eventType);

                CurrentEvents.add(tempo);

                final Button backButton = (Button) findViewById(R.id.backButton);
                final Button submitButton = (Button) findViewById(R.id.submit_button);
                // If event is submitted, write to database
                submitButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        // Add new event to database if user submitted
                        mDatabase = FirebaseDatabase.getInstance().getReference();

                        Event event = new Event(eventName,eventDescription,eventAddress,eventDate,eventTime,eventType);
                        event.eventDataToMap();

                        String key = mDatabase.child("events").push().getKey();
                        Map<String, Object> newEvent = new HashMap<>();
                        newEvent.put(key, event);

                        mDatabase.child("events").updateChildren(newEvent);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

                backButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
