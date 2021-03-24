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
public class Coordonnees {

    private int ligne, colonne;
    private static final int NB_LIGNES = 5;
    private static final int NB_COLONNES = 8;

    static final char CAR_PREMIERE_LIGNE = 'A';
    static final int PREMIERE_COLONNE = 1;

    Coordonnees(int numLigne, int numColonne) {
        ligne = numLigne;
        colonne = numColonne;
    }

    static Coordonnees depuisCars(char carLigne, int col) {
        return new Coordonnees(
                carLigneVersNum(carLigne), col);
    }

    static int carLigneVersNum(char nomLigne) {
        final char carMin = CAR_PREMIERE_LIGNE;
        final char carMax = CAR_PREMIERE_LIGNE + NB_LIGNES - 1;
        if ((nomLigne < carMin) || (nomLigne > carMax)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à carVersNum, avec car = " + nomLigne
                    + ". Les valeurs autorisées sont les caractères entre "
                    + carMin + " et " + carMax + ".");
        }
        return nomLigne - CAR_PREMIERE_LIGNE;
    }
}
