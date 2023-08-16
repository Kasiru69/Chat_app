package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptar_chat extends RecyclerView.Adapter<Adaptar_chat.ViewHolder> {

    Context context;
    ArrayList<Pair> arr;
    public Adaptar_chat(Context context, ArrayList<Pair> arr)
    {
        this.context=context;
        this.arr=arr;
    }
    @NonNull
    @Override
    public Adaptar_chat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_chat,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptar_chat.ViewHolder holder, int position) {

        holder.txtmessage.setText(arr.get(position).mssg);
        holder.txtname.setText(arr.get(position).name);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtmessage,txtname;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.textView7);
            txtmessage=itemView.findViewById(R.id.textView2);
        }
    }
}
