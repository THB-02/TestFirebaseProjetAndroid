package com.openclassrooms.firebaseoc.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;

public class US {

    private String id;
    private String enonce;
    private Date dateCreated;
    private HashMap<String, User> notes = new HashMap<String, User>();

    public US(){

    }

    public US(String enonce){
        this.enonce = enonce;
    }

    public String getId() {
        return id;
    }
    @ServerTimestamp
    public Date getDateCreated() { return dateCreated; }
    public void setId(String id) {
        this.id = id;
    }
    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
    public HashMap<String, User> getNotes() {
        return notes;
    }
    public void setNotes(HashMap<String, User> notes) {
        this.notes = notes;
    }
}
