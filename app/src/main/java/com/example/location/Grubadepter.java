package com.example.location;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Grubadepter extends RecyclerView.Adapter<Grubadepter.ViewHolder> {

    Context context;
    List<String>list;



    Activity activity;
    String username ;


    public Grubadepter(Context context, List<String> list, Activity activity, String username) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.username = username;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(list.get(position).toString());
        holder.userAnalayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent ıntent=new Intent(activity,a.class);
                //ıntent.putExtra("Kadi",username);
                //ıntent.putExtra("othername",list.get(position).toString());
                //activity.startActivity(ıntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout userAnalayout;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.username);
            userAnalayout=itemView.findViewById(R.id.userAnalayout);
        }
    }
}
