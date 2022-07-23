package com.aashviit.DSEAssistant.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aashviit.DSEAssistant.R;

import java.util.ArrayList;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.viewholder> {


    Context context;
    ArrayList<modeclass> userArrayList;

    public NewAdapter(Context context, ArrayList<modeclass> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singelrowdesign2,parent,false);
        return  new NewAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        modeclass data= userArrayList.get(position);
        holder.coll_name.setText("Collage Name: " + data.getWebsitelink());

    }
    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView coll_name;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            coll_name=itemView.findViewById(R.id.coll_name);
        }
    }
}
