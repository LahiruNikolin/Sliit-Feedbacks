package com.example.sliitfeedback;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private Context context;

    private List<TeacherData> teacherDatas;

    public RecyclerAdapter(Context context, List<TeacherData> teacherDatas) {
        this.context = context;
        this.teacherDatas = teacherDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TeacherData td=teacherDatas.get(position);

        holder.name.setText(td.getName());
        String imgUrl=td.getImg();

        Picasso.get()
                .load( imgUrl)
                .placeholder(R.drawable.teacher)
                .into(holder.iv);






    }



    @Override
    public int getItemCount() {
        return teacherDatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;

        ImageView iv;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.teacher_name);
            iv=itemView.findViewById((R.id.teacherIv));
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position=getAdapterPosition();
            TeacherData teacher=teacherDatas.get(position);

            Log.d("clicker",teacher.getDocId());

        }
    }
}
