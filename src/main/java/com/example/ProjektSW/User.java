package com.example.ProjektSW;

public class User {

    private String name;
    private String rfid;
    private boolean logged;

    public User(String name, String rfid, boolean logged) {
        this.name = name;
        this.rfid = rfid;
        this.logged = logged;
    }

    public String toJsonString() {
        return "{ \"name\":\"" + name + "\", \"rfid\":\"" + rfid + "\", \"logged\":" +logged + "}";
    }
}
