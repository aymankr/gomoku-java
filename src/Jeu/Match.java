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
    
    private Joueur j1;
    private Joueur j2;
    private int nbToursJ1;
    private int nbToursJ2;
    
    Match(int tJa, int tJb, Joueur ja, Joueur jb) {
        this.nbToursJ1 = tJa;
        this.nbToursJ2 = tJb;
        this.j1 = ja;
        this.j2 = jb;
    }
    
    public void run() {
        
    }
}
