package com.aashviit.DSEAssistant.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aashviit.DSEAssistant.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {

    private InterstitialAd mInterstitialAd;
    Context context;
    ArrayList<model> userArrayList;


    public myadapter(Context context, ArrayList<model> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
        mInterstitialAd=null;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        setAds();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singelrowdesign,parent,false);
        return  new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        model data= userArrayList.get(position);
        holder.Intitute_code.setText("Institute Code: " + (data.getInstitute_Code()));
        holder.coll_name.setText("Collage Name: " + data.getCollageName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), viewpdf.class);
                intent.putExtra("filename", data.getInstitute_Code());
                intent.putExtra("FileUrl", data.getFileurl());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                if(mInterstitialAd!=null){
                    mInterstitialAd.show((Activity) context);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            holder.itemView.getContext().startActivity(intent);
                            //startActivity(new Intent((Activity) view.getContext(),Merit.class));
                            mInterstitialAd=null;
                            setAds();
                        }
                    });
                }else{
                    Log.d("HI","failed");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }



    public void filterlist(ArrayList<model> filterlist) {
        this.userArrayList=filterlist;
        notifyDataSetChanged();
    }


    class myviewholder extends RecyclerView.ViewHolder{
        TextView Intitute_code,coll_name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            Intitute_code=itemView.findViewById(R.id.int_code);
            coll_name=itemView.findViewById(R.id.coll_name);

        }
    }
    public  void setAds(){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context,"ca-app-pub-5343519906233311/4170707275", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        //mInterstitialAd.show((Activity) view.getContext());
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });

    }
}
