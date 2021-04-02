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
     * Le main
     */
    public static void main(String[] args) throws CoupChoisiException{
        out.println("* Bienvenue sur Gomoku.\n");
        Plateau p = new Plateau(17, 5);
        
        menuPrincipal();
    }

    /**
     * Affiche le menu principal au(x) joueur(s) et leur permet de choisir un mode de jeu
     * @throws CoupChoisiException 
     */
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
    
        /**
         * Pose une question et attend un entier comme réponse
         * @param s la question posée
         * @return la réponse
         */
        private static int questionInt(String s){
            System.out.println(s);
            System.out.print("--> ");
            String l = lireLigne();
            int n;
            try{
                
                n = Integer.parseInt(l);
                if (n<=0){
                    System.out.println("Erreur de saisie : entier strictement positif attendu");
                }
                
            }
            catch(Exception e){
                System.out.println("Erreur de saise : entier attendu");
                n = -1;
            }
            if (n<=0){
                n= questionInt(s);
            }
            return n;
        }
        
        /**
         * 
         * @param s
         * @return 
         */
        private static String questionString(String s){
            System.out.println(s);
            System.out.print("--> ");
            String l = lireLigne();
            if (!nomValide(l)){
                System.out.println("Erreur de saisie : le nom ne peut être constitué que de lettres");
                l = questionString(s);
            }
            
            return l;
        }
        
        private static boolean nomValide(String s){
            boolean v = true;
            int i =0;
            while(i < s.length() && v){
                char c = s.charAt(i);
                if (!lettreValide(c)){
                    v = false;
                }
                i++;
            }
            return v;
        }
        
        private static boolean lettreValide(char c){
            return (c > 96 && c < 123) || (c > 64 && c < 91);
        }
        private static void menuPartie(boolean estSeul) throws CoupChoisiException{
            
            int nbT = questionInt("Combien de tour voulez vous jouer ?");
            System.out.println("");
            System.out.println("");
            int nbL = questionInt("Combien de lignes ?");
            System.out.println("");
            System.out.println("");
            int nbC = questionInt("Combien de colonnes ?");
            System.out.println("");
            System.out.println("");
            
            
            
            if (!estSeul){
                System.out.print("Joueur 1, ");
            }
            
            
            
            
            
            Joueur j1 = new JoueurHumain(questionString("quel est votre nom ?"),true);
            System.out.println("");
            System.out.println("");
            Joueur j2;
            if (!estSeul){
                j2 = new JoueurHumain(questionString("Joueur 2, quel est votre nom ?"),false);
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
