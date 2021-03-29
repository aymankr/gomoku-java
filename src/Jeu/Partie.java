/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.ArrayList;

/**
 *
 * @author A
 */
public class Partie {
    
    private Match m;
    private ArrayList<String> coupJouesJoueur1, coupJouesJoueur2;
    private Joueur j1, j2;
    private boolean finie;
    
    Partie(Joueur ja, Joueur jb, Match m) {
        this.j1 = ja;
        this.j2 = jb;
        this.m = m;
    }
    
    public boolean partieTermine() {
        return j1.getNbTours() == m.getNbTours() 
                && j2.getNbTours() == m.getNbTours();
    }
    
}
