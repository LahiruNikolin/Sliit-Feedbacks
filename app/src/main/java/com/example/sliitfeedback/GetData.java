package com.example.sliitfeedback;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class GetData {

    TeachersList listener;
    private static final String TAG = "GetData";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public GetData(TeachersList listener) {
        this.listener = listener;

    }

    public void retrieveData(){




    }

    public void searchData(String qry){


    }
}
