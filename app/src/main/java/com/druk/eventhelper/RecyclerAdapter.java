package com.druk.eventhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.druk.eventhelper.data.Event;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Event> arrayList = new ArrayList<>();
    Context context;

    public RecyclerAdapter(ArrayList new_arrayList) {
        arrayList = new_arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.card_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Event event = arrayList.get(position);
        holder.Name.setText(event.getName());
        holder.Description.setText(event.getDescription());
        holder.Time.setText(event.getTime());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilClass.deleteEvent(context,event.getName());
                arrayList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name, Description, Time;
        ImageButton imageButton;

        public ViewHolder(View view) {
            super(view);
            Name = view.findViewById(R.id.tv_name);
            Description = view.findViewById(R.id.tv_description);
            Time = view.findViewById(R.id.tv_time);
            imageButton = view.findViewById(R.id.ib_delete);
        }
    }


}
