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
public class Partie {
    
    private Joueur j1;
    private Joueur j2;
    private boolean finie;
    
    Partie(Joueur ja, Joueur jb) {
        this.j1 = jb;
        this.j2 = jb;
    }
    
    public void partieTermine() {
        
    }
    
    public void set(Coordonnees coord, Case.Color couleur) {
        
    }
    
    public Case.Color get(Coordonnees coord) {
        return null;
    }
}
