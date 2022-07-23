package com.aashviit.DSEAssistant.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aashviit.DSEAssistant.R;

import java.util.ArrayList;

public class myadapter_other extends RecyclerView.Adapter<myadapter_other.myviewholder> {



    Context context;
    ArrayList<model> userArrayList;

    public myadapter_other(Context context, ArrayList<model> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singelrowdesign2,parent,false);
        return  new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        model data= userArrayList.get(position);
        holder.coll_name.setText(data.getCollageName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), viewpdf.class);
                intent.putExtra("filename", data.getInstitute_Code());
                intent.putExtra("FileUrl", data.getFileurl());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void notifyItemChanged() {

    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView Intitute_code,coll_name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Intitute_code=itemView.findViewById(R.id.int_code);
            coll_name=itemView.findViewById(R.id.coll_name);

        }
    }
}
