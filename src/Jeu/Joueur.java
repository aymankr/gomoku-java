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
    private boolean sonTour;
    private int nbTours;

    Joueur(String n, boolean estNoir) {
        this.estNoir = estNoir;
        this.nom = n;
        this.nbTours = 0;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getNbTours() {
        return nbTours;
    }

    public boolean etatTour() {
        return sonTour;
    }

    public void setTour(boolean estSonTour) {
        if (estSonTour) {
            sonTour = true;
        }
        else {
            sonTour = false;
        }
    }
}
