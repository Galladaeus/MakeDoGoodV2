package com.example.annas.makedogood;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEvent extends AppCompatActivity {

    // Reference to database on Firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // [Initialize database reference so you can write data into firebase noSQL database]
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final Button button = (Button) findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText eventName = (EditText) findViewById(R.id.name);
                String name = eventName.getText().toString();

                final EditText eventAddress = (EditText) findViewById(R.id.addr);
                String address = eventAddress.getText().toString();

                final EditText eventDate = (EditText) findViewById(R.id.date);
                String date = eventDate.getText().toString();

                final EditText eventTime = (EditText) findViewById(R.id.time);
                String time = eventTime.getText().toString();

                final EditText eventDescription = (EditText) findViewById(R.id.editText13);
                String description = eventDescription.getText().toString();

                Event tempo = new Event(name, address, date, time, description);

                //mDatabase.child("test").setValue(tempo);
            }
        });
    }

}
