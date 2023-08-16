package com.example.chat;


import java.util.ArrayList;

public class chat {
    ArrayList<Pair> arr;
    chat(ArrayList<Pair> arr)
    {
        this.arr=arr;
    }
    chat() {

    }
    public ArrayList<Pair> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Pair> arr) {
        this.arr = arr;
    }
}
