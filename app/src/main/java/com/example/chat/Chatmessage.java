package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Chatmessage extends AppCompatActivity {

    ArrayList<Pair> arr;
    Adaptar_chat adapter,adapter2;
    RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmessage);
        rec=(RecyclerView) findViewById(R.id.recyclerview2);
        LinearLayoutManager linear=new LinearLayoutManager(this);
        linear.setStackFromEnd(true);
        arr=new ArrayList<>();
        rec.setLayoutManager(linear);
        Intent it=getIntent();
        String senderid=it.getStringExtra("senderid");
        String Name=it.getStringExtra("sendername");
        String receiverid=it.getStringExtra("receiverid");
        ArrayList<String> nonsense=new ArrayList<>();
        nonsense.add(senderid); nonsense.add(receiverid);
        Collections.sort(nonsense);
        TextView fina=findViewById(R.id.textView8);
        fina.setText("I am "+Name);
        DatabaseReference ref3= FirebaseDatabase.getInstance().getReference("Chat").child(nonsense.get(0)+nonsense.get(1));
       ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arr.clear();
                for(DataSnapshot it:snapshot.getChildren())
                    arr.add(it.getValue(Pair.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
             EditText txt = findViewById(R.id.editTextTextmessage);
            ImageButton bt = findViewById(R.id.imageButtonmessage);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arr.add(new Pair(Name, txt.getText().toString()));
                    adapter.notifyDataSetChanged();
                    chat Chat = new chat(arr);
                    ref3.push().setValue(new Pair(Name, txt.getText().toString()));
                    txt.setText("");
                }
            });
        adapter = new Adaptar_chat(this,arr);
        rec.setAdapter(adapter);
    }
}