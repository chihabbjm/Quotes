package com.app.quotes.quotes;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.Tag;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;


public class Home extends AppCompatActivity {

    // declare variables  analycis in firebase
    protected FirebaseAnalytics mFirebaseAnalytics;

    public  String btnName = null;


    //this for define Relative Layout :




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //labou , traspo , payement ,  besion real

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //this adding for the analycis in firebase
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // I am adding this to the script :
        Button Settings= (Button)findViewById(R.id.Settings);
        Button Favourite  = (Button)findViewById(R.id.Favourite);
        Button Random  = (Button) findViewById(R.id.Random);
        Button  Category = (Button) findViewById(R.id.Category);
        TextView addpost =(TextView) findViewById(R.id.addpost);
        TextView exit =(TextView) findViewById(R.id.exit);








    }











    public   Bundle params = new Bundle();

    public void logcat(View view) {
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",view.getId());
        btnName = "CategoryCliked";
        //desplay Tosat :
        Toast.makeText(getApplicationContext(), "Category", Toast.LENGTH_SHORT).show();
        // to send Logging to  the Fireabase Analytics :
        mFirebaseAnalytics.logEvent(btnName,params);
        // startActivity(new Intent( this,Category.class));
        //thes List_Published_Post is the Home page List of Cradview (blog_row)
        startActivity(new Intent(this,List_Published_Post.class));

    }
    public void ran(View view) {
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",view.getId());
        btnName = "RandomCliked";
        //desplay Tosat :
        Toast.makeText(getApplicationContext(), "Random", Toast.LENGTH_SHORT).show();
        // to send Logging the Fireabase Analytics :
        mFirebaseAnalytics.logEvent(btnName,params);
        // startActivity(new Intent( this,Random.class));

    }
    public void logset(View view) {
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",view.getId());
        btnName = "SettingsCliked";
        //desplay Tosat :
        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
        // to send Logging the Fireabase Analytics :
        mFirebaseAnalytics.logEvent(btnName,params);
        // startActivity(new Intent( this,Setting.class));

    }

    public void logfav(View view) {
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",view.getId());
        btnName = "FavouriteCliked";
        //desplay Tosat :
        Toast.makeText(getApplicationContext(), "Favourite", Toast.LENGTH_SHORT).show();
        // to send Logging the Fireabase Analytics :
        mFirebaseAnalytics.logEvent(btnName,params);
        // startActivity(new Intent( this,Favourite.class));

    }


    public void AddPost (View view) {

        startActivity(new Intent(this,LoginAdmin.class));
        Toast.makeText(getApplicationContext(), "Admin Panel", Toast.LENGTH_SHORT).show();


    }

    public void Exit(View view) {
        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT).show();
        this.finish();

    }
}
