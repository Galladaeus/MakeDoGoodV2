package com.example.annas.makedogood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpecificEvent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_event);
        Bundle bundle = getIntent().getExtras();
        int i = bundle.getInt("position");

        TextView one = (TextView) findViewById(R.id.textView);
        TextView two = (TextView) findViewById(R.id.textView2);
        TextView three = (TextView) findViewById(R.id.textView3);
        TextView four = (TextView) findViewById(R.id.textView4);
        TextView five = (TextView) findViewById(R.id.textView5);
        TextView six = (TextView) findViewById(R.id.textView6);

        one.setText(CurrentEvents.arr.get(i).name);
        two.setText(CurrentEvents.arr.get(i).address);
        three.setText(CurrentEvents.arr.get(i).time);
        four.setText(CurrentEvents.arr.get(i).type);
        five.setText(CurrentEvents.arr.get(i).description);
        six.setText(CurrentEvents.arr.get(i).date);

    }

}
