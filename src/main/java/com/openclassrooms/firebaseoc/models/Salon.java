package com.openclassrooms.firebaseoc.models;


public class Salon {
    private String nom;
    private String scrum;
    public Salon() { }

    public Salon(String name, String scrum) {

        this.nom= name;
        this.scrum = scrum;
    }

    // --- GETTERS ---
    public String getNom() {
        return nom; }

    public String getScrum(){
        return scrum;
    }

    // --- SETTERS ---
    public void setNom(String name) { this.nom = name; }
    public void setScrum(String scrum){
        this.scrum = scrum;
    }
}
