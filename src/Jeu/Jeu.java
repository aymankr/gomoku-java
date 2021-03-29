/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.io.PrintStream;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Jeu {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        out.println("* Bienvenue sur Gomoku.\n");
        Plateau p = new Plateau(17, 5);
        
        p.display();
    }

    static void menuPrincipal() {
        boolean boucler = true;
        while (boucler) {
            out.println("* Menu");
            out.println("a) Jouer contre un bot");
            out.println("b) Jouer contre un humain");
            out.println("q) quitter");
            out.print("? ");
            String commande = lireLigne();
            
            switch (commande) {
                case "q":
                    out.println("-> Bye.");
                    boucler = false;
                    break;
                case "aa":
                    out.println("a");
                    break;
                default:
                    out.println("-> commande inconnue '" + commande + "'");
                    break;
            }
        }
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
