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

                final EditText eventName = (EditText) findViewById(R.id.name);
                final String name = eventName.getText().toString();

                final EditText addr = (EditText) findViewById(R.id.addr);
                final String address = addr.getText().toString();

                final EditText date = (EditText) findViewById(R.id.date);
                final String dat = date.getText().toString();

                final EditText time = (EditText) findViewById(R.id.time);
                final String tim = time.getText().toString();

                final EditText eventd = (EditText) findViewById(R.id.editText13);
                final String description = eventd.getText().toString();

                final String eventKey = name + address;

                Event tempo = new Event(name, address, dat, tim, description);

                CurrentEvents.add(tempo);

                final Button submitButton = (Button) findViewById(R.id.b1);
                // If event is submitted, write to database
                submitButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        isFound = false;
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.orderByChild("events").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                // Event key was found, either event was already created or database error
                                if(dataSnapshot.getValue() == eventKey) {
                                    isFound = true;
                                    //TODO send error message and tell user to change name/address
                                }
                                System.out.println(dataSnapshot.getKey());
                            }
                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            }
                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                            }
                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

                        String key = mDatabase.child("events").push().getKey();
                        Map<String, Object> newEvent = new HashMap<>();
                        newEvent.put(key, name);

                        mDatabase.child("events").updateChildren(newEvent);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }

}
