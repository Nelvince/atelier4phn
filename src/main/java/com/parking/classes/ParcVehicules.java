package com.parking.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.parking.exceptions.VehiculeException;
import com.parking.exceptions.VehiculeNotFoundException;

// ParcVehicules.java
public class ParcVehicules {
    private Map<String, Vehicule> vehicules = new HashMap<>();
    
    // Ajouter un véhicule
    public void ajouterVehicule(Vehicule vehicule) throws VehiculeException {
        if (vehicule == null) {
            throw new VehiculeException("Le véhicule ne peut pas être null.");
        }
        vehicules.put(vehicule.getId(), vehicule);
    }
    
    // Supprimer un véhicule
    public void supprimerVehicule(String id) throws VehiculeNotFoundException {
        if (!vehicules.containsKey(id)) {
            throw new VehiculeNotFoundException("Véhicule avec l'ID " + id + " non trouvé.");
        }
        vehicules.remove(id);
    }
    
    // Modifier un véhicule
    public void modifierVehicule(String id, Vehicule vehicule) throws VehiculeNotFoundException {
        if (!vehicules.containsKey(id)) {
            throw new VehiculeNotFoundException("Véhicule avec l'ID " + id + " non trouvé.");
        }
        vehicules.put(id, vehicule);
    }
    
    // Rechercher un véhicule par nom
    public Vehicule rechercherVehiculeParNom(String nom) throws VehiculeNotFoundException {
        Vehicule vehicule = vehicules.values().stream()
                .filter(v -> v.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
        if (vehicule == null) {
            throw new VehiculeNotFoundException("Véhicule avec le nom " + nom + " non trouvé.");
        }
        return vehicule;
    }
    
    // Lister les véhicules en saisissant une lettre alphabétique
    public Map<String, Vehicule> listerVehiculesParLettre(char lettre) {
        return vehicules.values().stream()
                .filter(v -> v.getNom().toLowerCase().startsWith(String.valueOf(lettre).toLowerCase()))
                .collect(Collectors.toMap(Vehicule::getId, v -> v));
    }
    
    // Afficher le nombre de véhicules en stock
    public int nombreDeVehicules() {
        return vehicules.size();
    }
    
    // Fonctionnalités supplémentaires
    // Lister les véhicules par type
    public Map<String, Vehicule> listerVehiculesParType(Class<? extends Vehicule> type) {
        return vehicules.values().stream()
                .filter(type::isInstance)
                .collect(Collectors.toMap(Vehicule::getId, v -> v));
    }

    // Rechercher un véhicule par identifiant
    public Vehicule rechercherVehiculeParId(String id) {
        return vehicules.get(id);
    }


    public Map<String,Vehicule> getVehicules() {
        return this.vehicules;
    }

    public void setVehicules(Map<String,Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

}
