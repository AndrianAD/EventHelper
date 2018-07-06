package com.druk.eventhelper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    ArrayList<Event> arrayList=new ArrayList<>();

    public RecyclerAdapter(ArrayList new_arrayList) {
        arrayList = new_arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
Event event=arrayList.get(position);
holder.Name.setText(event.getName());
holder.Description.setText(event.getDescription());
holder.Time.setText(event.getTime());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
       private TextView Name,Description,Time;
        public ViewHolder(View view) {
            super(view);
       Name=view.findViewById(R.id.tv_name);
       Description=view.findViewById(R.id.tv_description);
       Time=view.findViewById(R.id.tv_time);

        }
    }
}
