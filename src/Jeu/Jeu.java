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
public class Jeu  {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CoupChoisiException{
        out.println("* Bienvenue sur Gomoku.\n");
        Plateau p = new Plateau(17, 5);
        
        menuPrincipal();
    }

    static void menuPrincipal() throws CoupChoisiException{
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
                case "a":
                    out.println("a");
                    break;
                case "b":
                    menuPartie(false);
                    
                    break;
                default:
                    out.println("-> commande inconnue '" + commande + "'");
                    break;
            }
        }
        
    }
    
        private static int questionInt(String s){
            System.out.println(s);
            System.out.print("--> ");
            String l = lireLigne();
            int n = Integer.parseInt(l);
            return n;
        }
        
        private static String questionString(String s){
            System.out.println(s);
            System.out.print("--> ");
            String l = lireLigne();
          
            return l;
        }
        
        private static void menuPartie(boolean estSeul) throws CoupChoisiException{
            
            int nbT = questionInt("Combien de tour voulez vous jouer ?" + "\n" + "\n");
            int nbL = questionInt("Combien de lignes ?" + "\n" + "\n");
            int nbC = questionInt("Combien de colonnes ?" + "\n" + "\n");

            
            
            if (!estSeul){
                System.out.print("Joueur 1, ");
            }
            
            
            
            Joueur j1 = new JoueurHumain(questionString("quel est votre nom ?" + "\n" + "\n")
            ,true);
            Joueur j2;
            if (!estSeul){
                j2 = new JoueurHumain(questionString("Joueur 2, quel est votre nom ?" + "\n" + "\n")
                ,false);
            }
            else{
                j2 = new JoueurIA("IA", false);
            }
            
            Partie p = new Partie(j1,j2,nbL,nbC,nbT);
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
