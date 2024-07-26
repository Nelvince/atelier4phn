package com.parking.classes;

public class Voiture extends Vehicule {
    private int nombreDePortes;
    
    public Voiture(String id, String nom, String marque, int annee, int nombreDePortes) {
        super(id, nom, marque, annee);
        this.nombreDePortes = nombreDePortes;
    }
    
    // Getters et Setters
    public int getNombreDePortes() { return nombreDePortes; }
    public void setNombreDePortes(int nombreDePortes) { this.nombreDePortes = nombreDePortes; }
    
    @Override
    public String toString() {
        return "Voiture{" +
                super.toString() +
                ", nombreDePortes=" + nombreDePortes +
                '}';
    }
}