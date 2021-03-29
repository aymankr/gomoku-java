/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

/**
 *
 * @author A
 */
public class Match {
    
    private int nbTours;
    private Joueur j1;
    private Joueur j2;
    
    Match(int nb, Joueur ja, Joueur jb) {
        this.j1 = ja;
        this.j2 = jb;
        this.nbTours = nb;
    }
    
    public Joueur getJoueur(boolean estNoir) {
        if (estNoir) {
            return j1;
        }
        else {
            return j2;
        }
    }

    public int getNbTours() {
        return nbTours;
    }
    
    public void run() {
        
    }
}
