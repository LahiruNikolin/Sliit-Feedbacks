package com.example.sliitfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class TeacherDetails extends AppCompatActivity {
    TextView tname,temail,Subjects,Rating,TotalFB;
     ImageView imageView;

    Button ViewFB;
   // FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String teacherID;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG="TD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);


        tname = findViewById(R.id.tName);
        temail = findViewById(R.id.tEmail);
        Subjects = findViewById(R.id.tSubjects);
        Rating = findViewById(R.id.tRating);
        TotalFB = findViewById(R.id.tFeedback);
        imageView=findViewById(R.id.tImage);



        teacherID  = getIntent().getStringExtra("TID");;

        loadTeacherProfile(teacherID);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.menu_exit:


                finishAffinity();

                System.exit(0);


                return true;

            case R.id.logout_btn:
                startActivity(new Intent(this,admin_log_r.class));
                finish();

            case R.id.main_act:
                startActivity(new Intent(this,MainActivity.class));
                finish();


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }



    public void loadTeacherProfile(String id){


        db.collection("teachers")
                .document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                //  Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                tname.setText(document.getData().get("fname")+" "+document.getData().get("lname"));
                                temail.setText(document.getData().get("email").toString());

                                Picasso.get()
                                        .load( document.getData().get("image").toString())
                                        .placeholder(R.drawable.teacher)
                                        .into(imageView);




                            } else {
                                Log.d(TAG, "  document doesn't exist");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });



        getSubs();
    }

    public void getSubs(){

        db.collection("subjects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                   ArrayList<String> subids=new  ArrayList<String>();
                   String subs="";
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if(document.getData().get("tid").toString().equals(teacherID)) {
                                    subs += document.getData().get("name").toString() + " ";
                                   // Log.d(TAG, document.getData().get("name").toString());
                                   // Log.d(TAG, "Match");
                                }

                            }
                            Subjects.setText(subs);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }



                    }
                });




    }
}
