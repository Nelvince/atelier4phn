package com.parking.data;

// FichierGestion.java
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.parking.classes.Camion;
import com.parking.classes.Moto;
import com.parking.classes.Vehicule;
import com.parking.classes.Voiture;

public class FichierGestion {
    private static final String FICHIER = "vehicules.txt";
    
    public static void sauvegarder(Map<String, Vehicule> vehicules) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER))) {
            for (Vehicule vehicule : vehicules.values()) {
                writer.write(vehicule.toString());
                writer.newLine();
            }
        }
    }
    
    public static Map<String, Vehicule> charger() throws IOException {
        Map<String, Vehicule> vehicules = new HashMap<>();
        File file = new File(FICHIER);
        if (!file.exists()) {
            file.createNewFile();
            return vehicules;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(",");
                String type = parts[0];
                String id = parts[1];
                String nom = parts[2];
                String marque = parts[3];
                int annee = Integer.parseInt(parts[4]);
                
                switch (type) {
                    case "Voiture":
                        int nombreDePortes = Integer.parseInt(parts[5]);
                        vehicules.put(id, new Voiture(id, nom, marque, annee, nombreDePortes));
                        break;
                    case "Camion":
                        double capaciteDeCharge = Double.parseDouble(parts[5]);
                        vehicules.put(id, new Camion(id, nom, marque, annee, capaciteDeCharge));
                        break;
                    case "Moto":
                        boolean sidecar = Boolean.parseBoolean(parts[5]);
                        vehicules.put(id, new Moto(id, nom, marque, annee, sidecar));
                        break;
                }
            }
        }
        return vehicules;
    }
}
