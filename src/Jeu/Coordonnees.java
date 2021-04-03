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

    public Coordonnees(int numLigne, int numColonne) {
        ligne = numLigne;
        colonne = numColonne;
    }

    public static int carColVersNum(char nomCol, int nbC) {
        final char carMin = CAR_PREMIERE_COLONNE;
        final char carMax = (char) (CAR_PREMIERE_COLONNE + nbC);

        if ((nomCol < carMin) || (nomCol > carMax)) {
            throw new IllegalArgumentException("Appel incorrect à carVersNum, avec car = " + nomCol
                    + ". Les valeurs autorisées sont les caractères entre " + carMin + " et " + carMax + ".");
        }
        return nomCol - CAR_PREMIERE_COLONNE;
    }

    /**
     * Indique si la coordonnée est dans le plateau
     *
     * @return retourne vrai ssi les coordonnées sont dans le plateau
     */
    public boolean estDansPlateau(Plateau p) {
        return this.ligne >= 0 && this.colonne >= 0 && this.ligne < p.getNbLignes() && this.colonne < p.getNbColonnes();
    }

    public Coordonnees[] suivantes(Direction d, Plateau p) {
        Coordonnees[] suiv = new Coordonnees[4];
        int decal = 0;

        for (int i = 0; i < 4; i++) {
            Coordonnees c = new Coordonnees(this.ligne + d.mvtVertic() + decal * d.mvtVertic(),
                    this.colonne + d.mvtHoriz() + decal * d.mvtHoriz());
            if (c.estDansPlateau(p)) {
                suiv[i] = new Coordonnees(c.ligne, c.colonne);
            }
            decal++;
        }
        return suiv;
    }

    public Coordonnees[] coordCasesVois(Plateau p) {
        Coordonnees[] tabVoisins = new Coordonnees[32];

        int nbVoisines = 0;

        for (Direction d : Direction.toutesDirections()) {
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

    public int getLigne() {
        return ligne;
    }

    public int getCol() {
        return colonne;
    }
}
