package com.example.gabe.politicianspulse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepRvAdapter extends RecyclerView.Adapter<RepRvAdapter.MyViewHolder> {

    private Context context;
    private List<Reps> repsList;

    public RepRvAdapter() {

    }

    public RepRvAdapter(List<Reps> repList, Context Activity) {
        this.repsList = repList;
        this.context = Activity;

    }


    @NonNull
    @Override
    public RepRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card, viewGroup, false);         //Inflate view to card.xml
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.viewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (context, SingleRepresentative.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name", repsList.get(viewHolder.getAdapterPosition()).getOfficialName());
                i.putExtra("party", repsList.get(viewHolder.getAdapterPosition()).getParty());
                i.putExtra("office", repsList.get(viewHolder.getAdapterPosition()).getOfficeName());
                i.putExtra("rep", repsList.get(viewHolder.getAdapterPosition()).getPhotoUrl());
                i.putExtra("line1", repsList.get(viewHolder.getAdapterPosition()).getLine1());
                i.putExtra("city", repsList.get(viewHolder.getAdapterPosition()).getCity());
                i.putExtra("state", repsList.get(viewHolder.getAdapterPosition()).getState());
                i.putExtra("zip", repsList.get(viewHolder.getAdapterPosition()).getZip());
                i.putExtra("phones", repsList.get(viewHolder.getAdapterPosition()).getPhones());

                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepRvAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.officeName.setText(repsList.get(i).getOfficeName());               //Display these three elements in the card view
        myViewHolder.officialName.setText(repsList.get(i).getOfficialName());
        myViewHolder.party.setText(repsList.get(i).getParty());
        //load image into card view
        Picasso.get()
                .load(repsList.get(i).getPhotoUrl())
                .centerCrop()
                .transform(new CircleTransform(50, 0))
                .fit()
                .into(myViewHolder.photoUrl);
    }

    @Override
    public int getItemCount() {
        return repsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Variables
        TextView officeName, officialName, party;
        ImageView photoUrl;
        LinearLayout viewContainer;
        public MyViewHolder(View itemView){
            super(itemView);
            officeName = itemView.findViewById(R.id.office_name);
            officialName = itemView.findViewById(R.id.official_name);
            party = itemView.findViewById(R.id.party);
            photoUrl = itemView.findViewById(R.id.photo_url);
            viewContainer = itemView.findViewById(R.id.container);
        }
    }
}
