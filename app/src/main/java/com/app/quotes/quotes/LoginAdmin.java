package com.app.quotes.quotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static junit.framework.Assert.assertEquals;

public class LoginAdmin extends AppCompatActivity {

    // adding script:
    private EditText password;
    private EditText username;
    private int counter = 5;
    TextView  Info;
    Button login_button;
   public String message="";


    // declare variables  analycis in firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);





        //adding this to the script :
       password = (EditText) findViewById(R.id.password);
       username = (EditText) findViewById(R.id.username);

        //final String email = username.getText().toString();
        //final String pass = password.getText().toString();







        Button login_button =(Button)findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());

            }
        });


    }





    private void validate(String email_v, String pass_v) {

        if ((email_v.equals("chihabbjm")) && (pass_v.equals("22873620"))) {
            Intent intent = new Intent(LoginAdmin.this, PublishPost.class);
            startActivity(intent);
        } else {

          this.finish();

        }

    }

    //the script end here




}





