/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

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

    /**
     * Indique si le joueur actuel est une IA
     * 
     * @return retourne vrai ssi le joueur est une IA
     */
    public boolean estUneIA() {
        return estIA;
    }

    /**
     * Méthode abstraite pour jouer un coup
     * 
     * @param coup le coup
     * @param listCoups la liste des coups
     * @param plat le plateau
     * @param p la partie
     * @param estNoir vrai ssi le joueur est noir
     */    
    abstract void jouer(String coup, ArrayList<String> listCoups, Plateau plat, Partie p, boolean estNoir);
}
