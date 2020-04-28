package com.example.sliitfeedback;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GetDashboadData {

    MainActivity listener;

    private static final String TAG = "GetDashboadData";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public GetDashboadData(MainActivity listener) {
        this.listener = listener;

    }

    public void getDashboardData(){
        db.collection("teachers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    int teacherCount=0;

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                teacherCount++;

                               // Log.d(TAG,document.getId());


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                        listener.teachers.setText(teacherCount+" Teachers");

                    }
                });

        getStudentCount();

    }

    public  void getStudentCount(){



        db.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    int stCount=0;

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {



                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {


                                stCount++;


                            }


                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                        listener.students.setText(stCount+" Students");

                    }


                });

        getFeedbackCount();

    }
    public  void getFeedbackCount(){



        db.collection("feedbacks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {


                    int Count=0;

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {



                        if (task.isSuccessful()) {


                            for (QueryDocumentSnapshot document : task.getResult()) {


                                Count++;


                            }


                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                        listener.feedbacks.setText(Count+" Feedbacks");
                        listener.spinner.setVisibility(View.GONE);

                    }


                });



    }

}
