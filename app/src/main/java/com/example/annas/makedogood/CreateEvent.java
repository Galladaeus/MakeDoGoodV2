package com.example.annas.makedogood;

import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        final Button button = (Button) findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final EditText eventName = (EditText) findViewById(R.id.name);
                String name = eventName.getText().toString();

                final EditText addr = (EditText) findViewById(R.id.addr);
                String address = addr.getText().toString();

                final EditText date = (EditText) findViewById(R.id.date);
                String dat = date.getText().toString();

                final EditText time = (EditText) findViewById(R.id.time);
                String tim = time.getText().toString();


                final EditText eventd = (EditText) findViewById(R.id.editText13);
                String description = eventd.getText().toString();

                Event tempo = new Event(name, address, dat, tim, description);
            }
        });
    }

}
