package com.app.quotes.quotes;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.util.Calendar;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;

import static android.R.attr.data;

public class PublishPost extends AppCompatActivity {

    // adding the script from  this :
    private static final int GALLERY_REQUEST = 1;
    private ImageButton imageselector;

    private EditText title;
    private  EditText description;
    private EditText source;
    private EditText imageurl;
    private  TextView postbutton;
    private Uri imageUri;
    private TextView field_pop_up;



    //this for declare  the current date from  the system get Instance to work with it :
      Calendar currentTime = Calendar.getInstance();


    //this for get the Time in varvable currentTime :
    //the fnuction trim() is make copy from the Object String
    String currenttime =(String) currentTime.getTime().toString().trim();




    //declare PrograsseDialog :
    private ProgressDialog mProgress;

    //declare StorageRefenrce Firebase Storage :
    private StorageReference mStorage;


    //Declare DatabaseRefenrce Firebase Database:
    private DatabaseReference mDatabase ;



    //this for declare varavble fo type Firebase Analytics catching the analytics of this activity :
    protected FirebaseAnalytics mFirebaseAnalytics;


    public  String btnName = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_post);




        //this adding for the analycis in firebase
        // catching the LogEvent :
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        //set the prograssBar into the Layout:
        mProgress=new ProgressDialog(this);



        //Set mStorage refrnce into Firebase Storage :
        mStorage= FirebaseStorage.getInstance().getReference();



        //Set mDatabase refernce into Firebase Database:
        mDatabase = FirebaseDatabase.getInstance().getReference("blog");






        // declare the conpoment
        title =(EditText)findViewById(R.id.title);
        description =(EditText) findViewById(R.id.description);
        source =(EditText) findViewById(R.id.source);
        imageurl=(EditText) findViewById(R.id.imageurl);





        // this script for get image gallery  from  the device and set it into the Imagebutton
        imageselector =(ImageButton)findViewById(R.id.imageselector);
        imageselector.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,GALLERY_REQUEST);


            }

        });


        //this for the publish the post :
        postbutton =(TextView)findViewById(R.id.postbutton);
        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // catching the LogEvent (First Open Bundle):
                Bundle params = new Bundle();

                //catching the Onclick Button with LogEvent in Firebase Adding this 2 lines :
                //RQ: v is the name varable of the view in this case is the Textview (id==postbutton):
                params.putInt("ButtonsID",v.getId());
                btnName = "PostCliked";
                // to send the LoggEvenet  (Logging) to the Fireabase Analytics :
                mFirebaseAnalytics.logEvent(btnName,params);


                //this methode for relite the conpment (EditText..) with the Firebase and Posting  :
                startPosting ();



            }
        });



    }









    //define the methode startPosting :
    private void startPosting() {


      //put message into PrograssDialog:
        mProgress.setMessage("Posting into the Blog ....");



        //define local varable and set from the conpment layout :
        final String title_val = title.getText().toString().trim();
        final String description_val =description.getText().toString().trim();
        String source_val =source.getText().toString().trim();

        //simple test is it empty or not :
        if (!TextUtils.isEmpty(title_val)&& !TextUtils.isEmpty(description_val) && !TextUtils.isEmpty(source_val) && imageUri!=null )
        {

            //Louding of the PrograssDialog :
            mProgress.show();

            //this for save the image into folder (blog_image) after that the Image (in name image in look like the time update):
           // StorageReference filepath = mStorage.child("blog_image").child("systeme time t/d/a ");
            StorageReference filepath =mStorage.child("blog_image").child("image"+currenttime);
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();




                     // create new Random Id to the new post :
                    DatabaseReference newPost =mDatabase.push();
                    //create new att nommate title and set it the value of title_val :
                    newPost.child("title").setValue(title_val);
                    //create new att nommate description and set it the value of description_val :
                    newPost.child("description").setValue(description_val);
                    //create new att nommate imageUrl  and set it the value of imageURL (path,link) from Firebase Storage :
                    newPost.child("imageUrl").setValue(downloadUrl.toString());



                    //finshing the louding of the PrograssDialog completing Posting the Post :
                    mProgress.dismiss();


                    // if the posting is commplete than will open new Intent :
                    Intent List_Published_Post =new Intent(getApplicationContext(),List_Published_Post.class);
                    startActivity(List_Published_Post);


                }

                //(End here )

            });

        }

        else
        {
            //this if the posting is no not correct the toast it will show :
            //Toast.makeText(getApplicationContext(), "Error something is Messing  ", Toast.LENGTH_SHORT).show();

            //OR
            mProgress.dismiss();
            starttoast();





        }


    }




    //this methode for the costomize tosat :
    private void starttoast() {
        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View costomTosatroot =inflater.inflate(R.layout.activity_costomizetosat,null);
        Toast costomTosat =new Toast(context);
        costomTosat.setView(costomTosatroot);
        costomTosat.setDuration(Toast.LENGTH_LONG);
        costomTosat.show();

    }




    // this script for get image gallery  from  the device and set it into the Imagebutton
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==GALLERY_REQUEST && resultCode==RESULT_OK)
        {
            imageUri = data.getData();
            imageselector.setImageURI(imageUri);

        }


    }
}
