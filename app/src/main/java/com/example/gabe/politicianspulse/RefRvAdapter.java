package com.example.gabe.politicianspulse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RefRvAdapter extends RecyclerView.Adapter<RefRvAdapter.MyViewHolder>{
    private Context context;
    private List<Refs> refList;

    public RefRvAdapter(Context applicationContext, List<Refs> refList){
        this.context = applicationContext;
        this.refList = refList;
    }

    public RefRvAdapter(List<Refs> refList, Context applicationContext){
        this.refList = refList;
        this.context = applicationContext;
    }
    @NonNull
    @Override
    public RefRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.ref_card, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RefRvAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.refTitle.setText(refList.get(i).getRefTitle());
        myViewHolder.refSub.setText(refList.get(i).getRefSub());

    }

    @Override
    public int getItemCount() {
        return refList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView refTitle, refSub;
        public MyViewHolder(View itemView){
            super(itemView);
            refTitle = itemView.findViewById(R.id.ref_title);
            refSub = itemView.findViewById(R.id.ref_sub);
        }
    }
}
