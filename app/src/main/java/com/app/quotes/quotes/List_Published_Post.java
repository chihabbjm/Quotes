package com.app.quotes.quotes;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class List_Published_Post extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    public  String btnName = null;



    //this for define the variable FirebaseAnalytics (like logevent) :
    protected FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__published__post);

        //this adding for the analycis in firebase
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        //this You can enable disk persistence with just one line of code.(offiline capabilities)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        //this for using the like button
        Button like=(Button)findViewById(R.id.like);





        mBlogList =(RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));



        //make acces to the blog child no into the root acces :
        mDatabase= FirebaseDatabase.getInstance().getReference().child("/blog");

        //this of synchronize the data in enable internet acces :
        mDatabase.keepSynced(true);







    }

    //then creating onstart
@Override
    protected void onStart (){
        super.onStart();
    //then we create new java lasse nammate Blog



        //create FirebaseRecyculer
        //FirebaseRecyclerAdapter <Blog,BlogViewHolder> firebaseRecyclerAdapter
FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <Blog, BlogViewHolder>(

     Blog.class,
        R.layout.blog_row,
        BlogViewHolder.class,
        mDatabase


) {

    @Override
    protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

        //minute 12:53


        viewHolder.setTitle(model.getTitle());
        viewHolder.setText(model.getDescription());



        viewHolder.setImage(getApplicationContext(),model.getImageUrl());




    }
};


mBlogList.setAdapter(firebaseRecyclerAdapter);

    }






    //after that we create new class nammate  BlogViewHolder to conncate and set the attribut from the Firebase Database into the componet layout
   public static  class BlogViewHolder  extends RecyclerView.ViewHolder {

           View mView ;


        TextView post_title;
        TextView post_text;
        ImageView  post_image;



        //constracteur
        public BlogViewHolder(View itemView) {
            super(itemView);

            mView = itemView;








        }

        // create new setter to all the layout commponet :



        //this setter for the Title the name of the varable title from  the Blog class :
        public void setTitle (String title )
        {

            //post_title is Textview from layout blog_row
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            //blog_row is sengle cardeview to create is the recycler view in firebase to create new articles and add theme Automatiqulie
            post_title.setText(title);


        }








        //this setter for the description
        public void setText (String description)
        {
            //post_title is Textview from layout blog_row
            TextView post_text =(TextView) mView.findViewById(R.id.post_text);
            //blog_row is sengle cardeview to create is the recycler view in firebase to create new articles and add theme Automatiqulie
            post_text.setText(description);

        }



        public void setImage(Context ctx ,String image)
        {

            //post_image is Imageview from layout blog_row
            ImageView  post_image =(ImageView)  mView.findViewById(R.id.post_image) ;
            //this for set  the imageURL from firebase  into imageview :
            Picasso.with(ctx).load(image).into(post_image);


        }






    }

   //create new bundle for push into it the logevnet like button:
    public   Bundle params = new Bundle();


    //set the image icon in defoualt icon in back in layout XML:

    //this is onclick methode for change the image icon
    public void like_button(View v)
    {
        //this for change the icon image from  the defoulat image into read image icon in on click evnet:
        v.setBackgroundResource(R.mipmap.like_pers);
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",v.getId());
        btnName = "LikeCliked";
        //this for send the logevnet :
        mFirebaseAnalytics.logEvent(btnName,params);

    }



    //this is onclick methode for change the image icon

    public void save(View v)
    {
        //this for change the icon image from  the defoulat image into read image icon in on click evnet:
        v.setBackgroundResource(R.mipmap.downlaod_pers);
        //  logEvent(btnName,params);
        params.putInt("ButtonsID",v.getId());
        btnName = "SaveImageCliked";
        //this for send the logevnet :
        mFirebaseAnalytics.logEvent(btnName,params);


    }

    //the end of the Class List_published post
}




