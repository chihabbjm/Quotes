package com.app.quotes.quotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class costomizetosat extends AppCompatActivity {

     private String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costomizetosat);
    }

    public TextView field_pop_up =(TextView)findViewById(R.id.field_pop_up);

    public void setSt(String s) {
        field_pop_up.setText(s.toString());

    }


}
