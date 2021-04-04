/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.Arrays;

/**
 *
 * @author A
 */
public class Coordonnees {

    private int ligne, colonne;
    private static final char CAR_PREMIERE_COLONNE = 'A';

    /**
     * Constructeur d'un Coordonnees
     * 
     * @param numLigne numéro de ligne
     * @param numColonne numéro de colonne
     */    
    public Coordonnees(int numLigne, int numColonne) {
        ligne = numLigne;
        colonne = numColonne;
    }

    /**
     * Convertir un caractère de colonne en numéro de colonne
     * 
     * @param nomCol le caractère
     * @param nbC    nombre de colonne maximal
     * @return retourner le numéro de colonne
     */
    public static int carColVersNum(char nomCol, int nbC) {

        return nomCol - CAR_PREMIERE_COLONNE;
    }

    /**
     * Indique si la coordonnée est dans le plateau
     *
     * @return retourne vrai ssi les coordonnées sont dans le plateau
     */
    public boolean estDansPlateau(Plateau p) {
        return this.ligne >= 0 && this.colonne >= 0 && this.ligne <= p.getNbLignes()
                && this.colonne <= p.getNbColonnes();
    }

    /**
     * Récolter un tableau de coordonnées alignés à la coordonnée actuelle suivant
     * une direction choisie
     * 
     * @param d la direction
     * @param p le plateau
     * @return retourner ce tableau
     */
    private Coordonnees[] suivantes(Direction d, Plateau p) {
        Coordonnees[] suiv = new Coordonnees[4];
        int facteur_Decalage = 0;

        for (int i = 0; i < 4; i++) {
            Coordonnees c = new Coordonnees(this.ligne + d.mvtVertic() + facteur_Decalage * d.mvtVertic(),
                    this.colonne + d.mvtHoriz() + facteur_Decalage * d.mvtHoriz());
            if (c.estDansPlateau(p)) {
                suiv[i] = new Coordonnees(c.ligne, c.colonne);
            }
            facteur_Decalage++;
        }
        return suiv;
    }

    /**
     * Récolter un tableau de coordonnées voisines sur les directions
     * complémentaires
     * 
     * @param p       le plateau
     * @param tabComp tableau de directions complémentaires
     * @return retourner le tableau de coordonnées
     */
    public Coordonnees[] voisComplementaires(Plateau p, Direction[] tabComp) {
        Coordonnees[] tabVoisins = new Coordonnees[8];

        int nbVoisines = 0;

        for (Direction d : tabComp) {
            Coordonnees[] suiv = suivantes(d, p);

            for (int i = 0; i < 4; i++) {
                if (suiv[i] != null && suiv[i].estDansPlateau(p)) {
                    tabVoisins[nbVoisines] = suiv[i];
                    nbVoisines++;
                }
            }
        }
        return Arrays.copyOf(tabVoisins, nbVoisines);
    }

    /**
     * Récolter la ligne de la coordonnée actuelle
     * 
     * @return retourner le numéro de la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Récolter la colonne
     * 
     * @return retourner la colonne
     */
    public int getCol() {
        return colonne;
    }
}
