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



        db.collection("teachers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    String name="";
                    String imgUrl="";
                    String docId="";
                    List<TeacherData> tds=new ArrayList<>();
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                //  Log.d(TAG, document.getId() + " => " +   document.getData().get("fname"));

                                name=document.getData().get("fname")+" "+document.getData().get("lname");
                                imgUrl=document.getData().get("image").toString();
                                docId=document.getId();
                                // Log.d(TAG,  name);

                                tds.add(new TeacherData(name ,imgUrl,docId));

                               //Log.d(TAG,"here callled");


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        Log.d(TAG,"out callled");
                        listener.dataReady(tds);

                    }
                });

        //Log.d(TAG,  tds.get(1).getName());
        /*

      */


    }

    public void searchData(String qry){

        db.collection("teachers").orderBy("fname")
                .startAt(qry)
                .endAt(qry+"\uf8ff")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    String name="";
                    String imgUrl="";
                    String docId="";
                    List<TeacherData> tds=new ArrayList<>();
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        Log.d(TAG,"yah?");
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {

                            name=doc.getData().get("fname")+" "+doc.getData().get("lname");
                            imgUrl=doc.getData().get("image").toString();
                            docId=doc.getId();
                          //  Log.d(TAG,  name);
                            tds.add(new TeacherData(name ,imgUrl,docId));
                        }
                        listener.dataReady(tds);
                    }
                });
    }


}
