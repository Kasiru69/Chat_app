package com.example.chat;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class add_friend extends AppCompatActivity {

    HashMap data;
    ArrayList<String> tmp;
    ArrayList<String> temp;
    HashMap<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Intent it=getIntent();
        String userid=it.getStringExtra("user id");
        String Name=it.getStringExtra("username");
        Button btn=findViewById(R.id.buttonsend);
        EditText txt=findViewById(R.id.editTextText2);
        tmp=new ArrayList<>();
        map=new HashMap<>();
        //Toast.makeText(this,txt.getText().toString(), Toast.LENGTH_SHORT).show();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Data").child(userid);
        DatabaseReference ref2=ref.child("arr");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot it:snapshot.getChildren())
                    tmp.add(it.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //array.add("ahahaha");
        StringBuilder sb=new StringBuilder();
        DatabaseReference ref5=FirebaseDatabase.getInstance().getReference().child("Data");
        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot it:snapshot.getChildren()) {
                        user val=it.getValue(user.class);
                        //Toast.makeText(add_friend.this, val.name, Toast.LENGTH_SHORT).show();
                        map.put(val.name,val.id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //StringBuilder sb=new StringBuilder();
        for(String str:tmp) sb.append(str+",");
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tmp.contains(txt.getText().toString()))
                    Toast.makeText(add_friend.this, txt.getText().toString()+" is already your friend", Toast.LENGTH_SHORT).show();
                else if(!map.containsKey(txt.getText().toString()))
                    Toast.makeText(add_friend.this, "Person don't exist", Toast.LENGTH_SHORT).show();
                else
                tmp.add(txt.getText().toString());
                temp=new ArrayList<>();
                String frndid=map.get(txt.getText().toString());
                DatabaseReference ref6= FirebaseDatabase.getInstance().getReference().child("Data").child(frndid);
                DatabaseReference ref7=ref6.child("arr");
                ref7.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot it:snapshot.getChildren())
                            temp.add(it.getValue(String.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                temp.add(Name);
                ref7.setValue(temp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                ref2.setValue(tmp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                Intent it=new Intent(add_friend.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}