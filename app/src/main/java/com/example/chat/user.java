package com.example.chat;

import java.util.ArrayList;

public class user {
    String mail,password,name,id;
    ArrayList<String> arr;
    user(String email,String password,String name,String id,ArrayList<String> arr)
    {
        this.mail=email;
        this.password=password;
        this.name=name;
        this.id=id;
        this.arr=arr;
    }

    user() { }
    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getArr() {
        return arr;
    }

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }
}
