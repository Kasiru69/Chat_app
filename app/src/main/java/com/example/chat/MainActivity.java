package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private FirebaseAuth auth;
    public static String userid;
    DatabaseReference ref;
    ArrayList<user> arr;
    HashSet<user> set;
    ArrayList<String> tmp;
    Adapter adapter;
    String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        FirebaseUser use=auth.getCurrentUser();
        arr=new ArrayList<>();
        tmp=new ArrayList<>();
        if(use==null)
        {
            Intent it=new Intent(MainActivity.this,Login_page.class);
            startActivity(it);
            finish();
        }
        Button btso=findViewById(R.id.button3);
        btso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent it=new Intent(MainActivity.this,Login_page.class);
                startActivity(it);
                finish();
            }
        });
        RecyclerView rec=(RecyclerView) findViewById(R.id.recyclerview);
        rec.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Data");
        userid=use.getUid();
        DatabaseReference ref2=ref.child(userid);
        DatabaseReference ref3=ref2.child("arr");
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot it:snapshot.getChildren())
                    tmp.add(it.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Name=snapshot.child("name").getValue(String.class);
                TextView torbaba=findViewById(R.id.textView4);
                torbaba.setText("My name is "+Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot it:snapshot.getChildren())
                {
                    user use=it.getValue(user.class);
                    if(use.name!=Name && tmp.contains(use.name)) {
                        arr.add(use);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter=new Adapter(this,arr,this);
        rec.setAdapter(adapter);
        ImageButton imgbtn=findViewById(R.id.imageButton);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,add_friend.class);
                it.putExtra("user id",userid);
                it.putExtra("username",Name);
                startActivity(it);
                finish();
            }
        });
    }

    @Override
    public void onitem(int pos) {
        Intent it=new Intent(MainActivity.this,Chatmessage.class);
        it.putExtra("senderid",userid);
        it.putExtra("sendername",Name);
        it.putExtra("receiverid",arr.get(pos).id);
        startActivity(it);
        //finish();
    }
}