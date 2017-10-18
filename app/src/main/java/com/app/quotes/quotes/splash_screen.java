package com.app.quotes.quotes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class splash_screen extends AppCompatActivity {

    private final  int Splash_display_length=6000;


    // declare vraaible analycis in firebase
    protected FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //this adding for the analycis in firebase
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainIntent =new Intent (splash_screen.this,Home.class);

                // the MainActivity is the Main page of the appliaction

                splash_screen.this.startActivity(MainIntent);
                splash_screen.this.finish();
            }
        },Splash_display_length);








    }


}
