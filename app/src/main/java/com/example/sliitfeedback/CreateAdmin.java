package com.example.sliitfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAdmin extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static final String  tag = TAG;
    EditText Email,Password,ConPassword;
    Button CreateAdmin,teacher,admin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.Password);
        ConPassword = findViewById(R.id.ConPassword);
        CreateAdmin = findViewById(R.id.Register);
        progressBar = findViewById(R.id.progressBar);
        teacher = findViewById(R.id.teacher_list_btn);
        admin = findViewById(R.id.Admin);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class ));
        }

        CreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String conPW = ConPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    Password.setError("Password must be above 6 characters");
                    return;
                }
                if (password == conPW) {
                    ConPassword.setError("Password doesn't match");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAdmin.this, "User Created...", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("admin").document(userID);
                            Map<String,Object> admin = new HashMap<>();
                            admin.put("Email",email);
                            documentReference.set(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User profile is created");
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),AdminProfileR.class ));


                        }else{
                            Toast.makeText(CreateAdmin.this, "Eror !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

            });

            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),teach_details_r.class));
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminProfileR.class));
            }
        });




    }
}


