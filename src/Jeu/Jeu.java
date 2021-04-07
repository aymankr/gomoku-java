/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Jeu {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    /**
     * Le main
     */
    public static void main(String[] args) {
        out.println("* Bienvenue sur Gomoku.\n");

        menuPrincipal();
    }

    /**
     * Affiche le menu principal au(x) joueur(s) et leur permet de choisir un mode
     * de src
     * 
     * 
     */
    private static void menuPrincipal() {
        boolean boucler = true;
        while (boucler) {
            out.println("* Menu");
            out.println("a) Jouer contre un bot");
            out.println("b) Jouer contre un humain");
            out.println("q) Quitter le jeu \n(lors d'une partie, quittez à tout moment avec 'q')");
            out.print("? ");
            String commande = lireLigne();

            switch (commande) {
            case "q":
                out.println("-> Bye.");
                boucler = false;
                break;
            case "a":
                menuPartie(true);
                break;
            case "b":
                menuPartie(false);
                break;
            case "test":
                Joueur j1 = new JoueurHumain("mathieu", true);
                Joueur j2 = new JoueurIA("IA", false);
                Partie p = new Partie(j1, j2, 5, 5, 10);
                p.gererPartie();
            default:
                out.println("-> commande inconnue '" + commande + "'");
                break;
            }
        }

    }

    /**
     * Pose une question et attend un entier positif comme réponse
     * 
     * @param s la question posée
     * @return la réponse
     */
    private static int questionInt(String s, boolean demandeNbTours) {
        out.println(s);
        out.print("--> ");
        String l = lireLigne();
        int n;
        try {

            n = Integer.parseInt(l);

            if (demandeNbTours && (n < 10 || n % 2 == 1)) {
                out.println("Le nombre de tours doit être pair et au moins de 10, réessayez : ");
            } else if (!demandeNbTours && (n < 5 || n > 26)) {
                out.println("Erreur de saisie : entier entre 5 et 26 attendu");
            }

        } catch (Exception e) {
            out.println("Erreur de saisie : entier attendu");
            n = -1;
        }

        if (demandeNbTours && (n < 10 || n % 2 == 1)) {
            n = questionInt(s, demandeNbTours);
        }

        else if (!demandeNbTours && (n < 5 || n > 26)) {
            n = questionInt(s, demandeNbTours);
        }

        return n;
    }

    /**
     * Pose une question et retourne la réponse de l'utilisateur (pour son nom)
     * 
     * @param s la question
     * @return retourner la réponse
     */
    private static String questionString(String s) {
        out.println(s);
        out.print("--> ");
        String l = lireLigne();
        if (!nomValide(l) || l.isEmpty()) {
            out.println("Erreur de saisie : le nom ne peut être constitué que de lettres");
            l = questionString(s);
        }

        return l;
    }

    /**
     * Vérifier si un nom est valide
     * 
     * @param s le nom
     * @return retourner vrai si valide
     */
    private static boolean nomValide(String s) {
        boolean v = true;
        int i = 0;
        while (i < s.length() && v) {
            char c = s.charAt(i);
            if (!lettreValide(c)) {
                v = false;
            }
            i++;
        }
        return v;
    }

    /**
     * Vérifier si un caractère est une lettre
     * 
     * @param c le caractère
     * @return retourner vrai si valide
     */
    private static boolean lettreValide(char c) {
        return (c > 96 && c < 123) || (c > 64 && c < 91);
    }

    /**
     * Lancement du menu avant la partie, vérifie s'il y a un seul joueur humain ou
     * deux pour créer l'IA ou non
     * 
     * @param estSeul vrai si le joueur humain est seul
     */
    private static void menuPartie(boolean estSeul) {

        int nbTours = questionInt("Combien de tours voulez vous jouer ? (nombre pair >= 10)" + "\n" + "\n", true);
        int nbLig = questionInt("Combien de lignes ? (entre 5 et 26)" + "\n" + "\n", false);
        int nbCol = questionInt("Combien de colonnes ? (entre 5 et 26)" + "\n" + "\n", false);

        if (!estSeul) {
            System.out.print("Joueur 1, ");
        }

        Joueur j1 = new JoueurHumain(questionString("Quel est votre nom ?" + "\n" + "\n"), true);
        Joueur j2;
        if (!estSeul) {
            j2 = new JoueurHumain(questionString("Joueur 2, quel est votre nom ?" + "\n" + "\n"), false);
        } else {
            j2 = new JoueurIA("IA", false);
        }

        Partie p = new Partie(j1, j2, nbLig, nbCol, nbTours);

        p.gererPartie();

    }

    /**
     * Fonction permettant de lire l'entrée de l'utilisateur
     *
     * @return retourner l'entrée de l'utilisateur
     */
    private static String lireLigne() {
        return in.nextLine().trim();
    }
}
