package com.openclassrooms.firebaseoc.models;


public class Salon {
    private String nom;

    public Salon() { }

    public Salon(String name) {
        this.nom= name;
    }

    // --- GETTERS ---
    public String getNom() { return nom; }


    // --- SETTERS ---
    public void setNom(String name) { this.nom = name; }
}
