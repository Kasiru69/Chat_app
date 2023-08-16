package com.example.chat;

public class Pair {
    String name,mssg;
    Pair(String name,String mssg)
    {
        this.name=name;
        this.mssg=mssg;
    }

    Pair() { }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }
}
