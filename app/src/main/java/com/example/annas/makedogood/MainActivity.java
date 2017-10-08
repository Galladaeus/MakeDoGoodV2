package com.example.annas.makedogood;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {


    private FusedLocationProviderClient mFusedLocationClient;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        final Button button = (Button) findViewById(R.id.button2);
        final Button view = (Button) findViewById(R.id.button4);
        final Button em = (Button) findViewById(R.id.button5);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CurrentEvents.class);
                startActivity(intent);
            }
        });
        em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mFusedLocationClient.getLastLocation();

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getBaseContext())
                                .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                                .setContentTitle("Your location is now shared")
                                .setContentText("Hello World!");

                NotificationManager mNotificationManager =

                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                mNotificationManager.notify(001, mBuilder.build());

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
                startActivity(intent);
            }
        });
    }
}
