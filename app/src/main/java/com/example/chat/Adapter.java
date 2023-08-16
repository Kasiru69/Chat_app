package com.example.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final RecyclerViewInterface recycler;
    Context context;
    ArrayList<user> arr;
    public Adapter(Context context,ArrayList<user> arr,RecyclerViewInterface recycler)
    {
        this.context=context;
        this.arr=arr;
        this.recycler=recycler;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        ViewHolder viewHolder= new ViewHolder(view,recycler);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.txtname.setText(arr.get(position).name);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtnumber;
        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recycler) {
            super(itemView);
            txtname=itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycler !=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION)
                            recycler.onitem(pos);
                    }
                }
            });
        }
    }
}

