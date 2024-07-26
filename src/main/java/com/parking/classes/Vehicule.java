package com.parking.classes;

public abstract class Vehicule {
    private String id;
    private String nom;
    private String marque;
    private int annee;
    
    public Vehicule(String id, String nom, String marque, int annee) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.annee = annee;
    }
    
    // Getters et Setters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getMarque() { return marque; }
    public int getAnnee() { return annee; }
    
    public void setId(String id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setMarque(String marque) { this.marque = marque; }
    public void setAnnee(int annee) { this.annee = annee; }
    
    @Override
    public String toString() {
        return "Vehicule{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", annee=" + annee +
                '}';
    }
}
