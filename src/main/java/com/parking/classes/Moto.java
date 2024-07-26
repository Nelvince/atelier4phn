package com.parking.classes;

public class Moto extends Vehicule {
    private boolean sidecar;
    
    public Moto(String id, String nom, String marque, int annee, boolean sidecar) {
        super(id, nom, marque, annee);
        this.sidecar = sidecar;
    }
    
    // Getters et Setters
    public boolean hasSidecar() { return sidecar; }
    public void setSidecar(boolean sidecar) { this.sidecar = sidecar; }
    
    @Override
    public String toString() {
        return "Moto{" +
                super.toString() +
                ", sidecar=" + sidecar +
                '}';
    }
}