package com.example.sliitfeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class AdminProfileR extends AppCompatActivity {
    TextView Email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button CreateAdm,teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_r);

        Email = findViewById(R.id.email);
        teacher = findViewById(R.id.teacher_list_btn);
        CreateAdm = findViewById(R.id.CreateAdminBtn);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("admin").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Email.setText(documentSnapshot.getString("Email"));
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TeachersList.class));
            }
        });

        CreateAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateAdmin.class));
               // Log.d("bug","ada");
            }
        });



    }
}
