/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.List;

/**
 *
 * @author A
 */
abstract public class Joueur {

    protected boolean estIA;
    private boolean estNoir;
    private String nom;

    /**
     * Constructeur du joueur
     * 
     * @param n       le nom
     * @param estNoir sa couleur
     */
    public Joueur(String n, boolean estNoir) {
        this.estNoir = estNoir;
        this.nom = n;
    }

    /**
     * Renvoyer le nom du joueur en question
     * 
     * @return retourner le nom
     */
    public String getNom() {
        return nom;
    }

    public boolean estUneIA() {
        return estIA;
    }

    abstract void jouer(String coup, List<String> listCoups, Plateau plat, Partie p, boolean estNoir);
}
