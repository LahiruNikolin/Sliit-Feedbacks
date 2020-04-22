package com.example.sliitfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button teacherBTN;

    private ImageButton init;

    private TextView initTv;

    public TextView feedbacks,teachers,students;
    public ProgressBar spinner;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final String TAG ="MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.progressBar);

        GetDashboadData dataRetriver=new GetDashboadData(this);


        feedbacks=findViewById(R.id.feedback_tv);
        teachers=findViewById(R.id.teachers_tv);
        students=findViewById(R.id.students_tv);
        dataRetriver.getDashboardData();


        teacherBTN = findViewById(R.id.teacher_list_btn);
        teacherBTN.setOnClickListener(this);
        init=findViewById(R.id.init_btn);


        initTv=findViewById(R.id.init_text);

        init.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                return false;
            }


        });

        init.setOnClickListener(this);

        db.collection("flags")
                .document("svDqsKxgDCFqn3lJWB29")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                //  Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                if(document.getData().get("feedback").equals(true)){

                                    initTv.setText("INITIATED!");
                                    initTv.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.initColor, null));

                                }
                                else{



                                }


                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

/*

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        Map<String, Object> user1 = new HashMap<>();
        user1.put("first", "Alan");
        user1.put("middle", "Mathison");
        user1.put("last", "Turing");
        user1.put("born", 1912);

        db.collection("users")
                .add(user1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        Log.d(TAG,db.collection("users").getPath());

    }



 */


    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.teacher_list_btn:
                Log.d(TAG,"CLICKED");
                startActivity(new Intent(this,TeachersList.class));
                break;

            case R.id.init_btn:

                notifyStudents();
               // Log.d(TAG,"CLICKED");

                break;
        }

    }

    private void notifyStudents() {



/*
        db.collection("flags")
                .document("svDqsKxgDCFqn3lJWB29")
                .set(mp);

 */

        db.collection("flags")
                .document("svDqsKxgDCFqn3lJWB29")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                              //  Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                if(document.getData().get("feedback").equals(true)){

                                    Log.d(TAG, "Current data: " +"it is true");
                                    Map<String,Boolean> mp=new HashMap<>();
                                    mp.put("feedback",false);
                                    db.collection("flags")
                                            .document("svDqsKxgDCFqn3lJWB29")
                                            .set(mp);

                                    initTv.setText("INITIATE");
                                    initTv.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.white, null));
                                }
                                else{

                                    Map<String,Boolean> mp=new HashMap<>();
                                    mp.put("feedback",true);
                                    db.collection("flags")
                                            .document("svDqsKxgDCFqn3lJWB29")
                                            .set(mp);

                                    initTv.setText("INITIATED!");
                                    initTv.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.initColor, null));

                                }


                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

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


                if(android.os.Build.VERSION.SDK_INT>=21)
                    finishAndRemoveTask();
                else
                    this.finishAffinity();


                return true;

            case R.id.logout_btn:

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void populateFeedbackTv(int count){

    }

    public void populateTeachersTv(int count){

    }

    public void populateStudentTv(int count){

    }

}
