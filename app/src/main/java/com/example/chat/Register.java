package com.example.chat;

import static com.example.chat.R.id.button4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase data;
    FirebaseStorage store;
    EditText Email,Password,Name;
    Button bt,bt2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        bt=findViewById(R.id.button);
        bt2=findViewById(R.id.button4);
        Email=findViewById(R.id.editTextTextEmailAddress);
        Password=findViewById(R.id.editTextTextPassword);
        Name=findViewById(R.id.editTextText);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Register.this,Login_page.class);
                startActivity(it);
                finish();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=Email.getText().toString();
                String password=Password.getText().toString();
                String name=Name.getText().toString();
                auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String id=task.getResult().getUser().getUid();
                            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Data").child(id);
                            ArrayList<String> arr=new ArrayList<>();
                            user users= new user(mail,password,name,id,arr);
                            ref.setValue(users);
                            Intent it=new Intent(Register.this,Login_page.class);
                            startActivity(it);
                            finish();
                        }
                        else
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}