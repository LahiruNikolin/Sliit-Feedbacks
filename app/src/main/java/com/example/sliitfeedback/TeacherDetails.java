package com.example.sliitfeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class TeacherDetails extends AppCompatActivity {
    TextView tname,temail,Subjects,Rating,TotalFB;

    Button ViewFB;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String teacherID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);


        tname = findViewById(R.id.tName);
        temail = findViewById(R.id.tEmail);
        Subjects = findViewById(R.id.tSubjects);
        Rating = findViewById(R.id.tRating);
        TotalFB = findViewById(R.id.tFeedback);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        teacherID = "01zUdjxGo43Eszlbdmxj";

        DocumentReference documentReference = fstore.collection("teachers").document(teacherID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                tname.setText(documentSnapshot.getString("fname"));
                temail.setText(documentSnapshot.getString("email"));

            }
        });

    }
}
