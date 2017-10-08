package com.example.annas.makedogood;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateEvent extends AppCompatActivity {

    private DatabaseReference mDatabase;
    boolean isFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        final Button button = (Button) findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText name = (EditText) findViewById(R.id.name);
                final String eventName = name.getText().toString();

                final EditText address = (EditText) findViewById(R.id.addr);
                final String eventAddress = address.getText().toString();

                final EditText date = (EditText) findViewById(R.id.date);
                final String eventDate = date.getText().toString();

                final EditText time = (EditText) findViewById(R.id.time);
                final String eventTime = time.getText().toString();

                final EditText description = (EditText) findViewById(R.id.editText13);
                final String eventDescription = description.getText().toString();

                Event tempo = new Event(eventName, eventAddress, eventDate, eventTime, eventDescription);

                CurrentEvents.add(tempo);

                final Button submitButton = (Button) findViewById(R.id.b1);
                // If event is submitted, write to database
                submitButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mDatabase = FirebaseDatabase.getInstance().getReference();

                        Map<String, Object> eventData = new HashMap<>();
                        eventData.put("name", eventName);
                        eventData.put("description", eventDescription);

                        String key = mDatabase.child("events").push().getKey();
                        Map<String, Object> newEvent = new HashMap<>();
                        newEvent.put(key, eventData);

                        mDatabase.child("events").updateChildren(newEvent);
                        // TODO Double click to return to main page????
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }

}
