package com.parking.classes;

public class Camion extends Vehicule {
    private double capaciteDeCharge;
    
    public Camion(String id, String nom, String marque, int annee, double capaciteDeCharge) {
        super(id, nom, marque, annee);
        this.capaciteDeCharge = capaciteDeCharge;
    }
    
    // Getters et Setters
    public double getCapaciteDeCharge() { return capaciteDeCharge; }
    public void setCapaciteDeCharge(double capaciteDeCharge) { this.capaciteDeCharge = capaciteDeCharge; }
    
    @Override
    public String toString() {
        return "Camion{" +
                super.toString() +
                ", capaciteDeCharge=" + capaciteDeCharge +
                '}';
    }
}