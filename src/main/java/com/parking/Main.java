package com.parking;

// Main.java
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.parking.classes.Camion;
import com.parking.classes.Moto;
import com.parking.classes.ParcVehicules;
import com.parking.classes.Vehicule;
import com.parking.classes.Voiture;
import com.parking.data.FichierGestion;
import com.parking.exceptions.VehiculeException;
import com.parking.exceptions.VehiculeNotFoundException;

public class Main {
    private static ParcVehicules parc = new ParcVehicules();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        chargerDonnees();

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterVehicule(scanner);
                    break;
                case 2:
                    supprimerVehicule(scanner);
                    break;
                case 3:
                    modifierVehicule(scanner);
                    break;
                case 4:
                    rechercherVehiculeParNom(scanner);
                    break;
                case 5:
                    listerVehiculesParLettre(scanner);
                    break;
                case 6:
                    afficherNombreDeVehicules();
                    break;
                case 7:
                    sauvegarderDonnees();
                    break;
                case 8:
                    chargerDonnees();
                    break;
                case 9:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix non valide.");
            }
        }

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\nSystème de Gestion de Parc de Véhicules");
        System.out.println("1. Ajouter un véhicule");
        System.out.println("2. Supprimer un véhicule");
        System.out.println("3. Modifier un véhicule");
        System.out.println("4. Rechercher un véhicule par nom");
        System.out.println("5. Lister les véhicules par lettre");
        System.out.println("6. Afficher le nombre de véhicules en stock");
        System.out.println("7. Sauvegarder les données");
        System.out.println("8. Charger les données");
        System.out.println("9. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private static void ajouterVehicule(Scanner scanner) {
        System.out.print("Entrez le type de véhicule (Voiture, Camion, Moto): ");
        String type = scanner.nextLine();
        System.out.print("Entrez l'ID: ");
        String id = scanner.nextLine();
        System.out.print("Entrez le nom: ");
        String nom = scanner.nextLine();
        System.out.print("Entrez la marque: ");
        String marque = scanner.nextLine();
        System.out.print("Entrez l'année: ");
        int annee = scanner.nextInt();
        scanner.nextLine(); // Consomme la nouvelle ligne

        try {
            switch (type.toLowerCase()) {
                case "voiture":
                    System.out.print("Entrez le nombre de portes: ");
                    int nombreDePortes = scanner.nextInt();
                    scanner.nextLine(); // Consomme la nouvelle ligne
                    parc.ajouterVehicule(new Voiture(id, nom, marque, annee, nombreDePortes));
                    break;
                case "camion":
                    System.out.print("Entrez la capacité de charge: ");
                    double capaciteDeCharge = scanner.nextDouble();
                    scanner.nextLine(); // Consomme la nouvelle ligne
                    parc.ajouterVehicule(new Camion(id, nom, marque, annee, capaciteDeCharge));
                    break;
                case "moto":
                    System.out.print("A-t-il un sidecar (true/false): ");
                    boolean sidecar = scanner.nextBoolean();
                    scanner.nextLine(); // Consomme la nouvelle ligne
                    parc.ajouterVehicule(new Moto(id, nom, marque, annee, sidecar));
                    break;
                default:
                    System.out.println("Type de véhicule non valide.");
                    return;
            }
            System.out.println("Véhicule ajouté avec succès.");
        } catch (VehiculeException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void supprimerVehicule(Scanner scanner) {
        System.out.print("Entrez l'ID du véhicule à supprimer: ");
        String id = scanner.nextLine();
        try {
            parc.supprimerVehicule(id);
            System.out.println("Véhicule supprimé avec succès.");
        } catch (VehiculeNotFoundException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void modifierVehicule(Scanner scanner) {
        System.out.print("Entrez l'ID du véhicule à modifier: ");
        String id = scanner.nextLine();
        try {
            Vehicule vehicule = parc.rechercherVehiculeParId(id);
            if (vehicule == null) {
                throw new VehiculeNotFoundException("Véhicule avec l'ID " + id + " non trouvé.");
            }
            System.out.print("Entrez le nouveau nom: ");
            String nom = scanner.nextLine();
            System.out.print("Entrez la nouvelle marque: ");
            String marque = scanner.nextLine();
            System.out.print("Entrez la nouvelle année: ");
            int annee = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            if (vehicule instanceof Voiture) {
                System.out.print("Entrez le nouveau nombre de portes: ");
                int nombreDePortes = scanner.nextInt();
                scanner.nextLine(); // Consomme la nouvelle ligne
                parc.modifierVehicule(id, new Voiture(id, nom, marque, annee, nombreDePortes));
            } else if (vehicule instanceof Camion) {
                System.out.print("Entrez la nouvelle capacité de charge: ");
                double capaciteDeCharge = scanner.nextDouble();
                scanner.nextLine(); // Consomme la nouvelle ligne
                parc.modifierVehicule(id, new Camion(id, nom, marque, annee, capaciteDeCharge));
            } else if (vehicule instanceof Moto) {
                System.out.print("A-t-il un nouveau sidecar (true/false): ");
                boolean sidecar = scanner.nextBoolean();
                scanner.nextLine(); // Consomme la nouvelle ligne
                parc.modifierVehicule(id, new Moto(id, nom, marque, annee, sidecar));
            }

            System.out.println("Véhicule modifié avec succès.");
        } catch (VehiculeNotFoundException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void rechercherVehiculeParNom(Scanner scanner) {
        System.out.print("Entrez le nom du véhicule à rechercher: ");
        String nom = scanner.nextLine();
        try {
            Vehicule vehicule = parc.rechercherVehiculeParNom(nom);
            System.out.println("Véhicule trouvé: " + vehicule);
        } catch (VehiculeNotFoundException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private static void listerVehiculesParLettre(Scanner scanner) {
        System.out.print("Entrez la lettre alphabétique: ");
        char lettre = scanner.nextLine().charAt(0);
        Map<String, Vehicule> vehicules = parc.listerVehiculesParLettre(lettre);
        for (Vehicule vehicule : vehicules.values()) {
            System.out.println(vehicule);
        }
    }

    private static void afficherNombreDeVehicules() {
        System.out.println("Nombre de véhicules en stock: " + parc.nombreDeVehicules());
    }

    private static void sauvegarderDonnees() {
        try {
            FichierGestion.sauvegarder(parc.getVehicules());
            System.out.println("Données sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données: " + e.getMessage());
        }
    }

    private static void chargerDonnees() {
        try {
            Map<String, Vehicule> vehiculesCharges = FichierGestion.charger();
            for (Vehicule vehicule : vehiculesCharges.values()) {
                parc.ajouterVehicule(vehicule);
            }
            System.out.println("Données chargées avec succès.");
        } catch (IOException | VehiculeException e) {
            System.out.println("Erreur lors du chargement des données: " + e.getMessage());
        }
    }
}
