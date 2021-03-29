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
abstract public class Joueur {

    private boolean estNoir;
    private String nom;
    private boolean gagne;

    Joueur(String n, boolean estNoir) {
        this.estNoir = estNoir;
        this.nom = n;
    }
    
    public void jouerPosition() {

    }
}
