package com.example.sliitfeedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class TeachersList extends AppCompatActivity {

    private static final String TAG = "TeacherList";
    private RecyclerView recyclerView;

    private RecyclerAdapter recyclerAdapter;
    private ProgressBar spinner;

    private List<TeacherData> tds;
    private TextView resultTv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_list);

        recyclerView=findViewById(R.id.rec_teacher_list);
        spinner=findViewById(R.id.progressBar2);
        resultTv=findViewById(R.id.teacher_list_tv);
        GetData downloadDatas=new GetData(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String qry = extras.getString("search_qry");
            qry = qry.substring(0, 1).toUpperCase() + qry.substring(1);

            downloadDatas.searchData(qry);

            Snackbar snackbar = Snackbar.make(recyclerView.getRootView(),"Showing results for "+qry, BaseTransientBottomBar.LENGTH_LONG);


            snackbar.show();

        }
        else{


            // tds.add(new TeacherData("Himaya Akarsha","po"));



            downloadDatas.retrieveData();

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.teacher_menu_item:

                startActivity(new Intent(this,SearchActivity.class));
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dataReady(List<TeacherData> tds){

       // Log.d(TAG,"got data");
        if(tds.isEmpty())
            resultTv.setText("No Matching Lecturers");


        this.tds=tds;

        recyclerAdapter=new RecyclerAdapter(this,tds);

        recyclerView.setAdapter(recyclerAdapter);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        spinner.setVisibility(View.GONE);
    }



}
