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

    static final char CAR_PREMIERE_COLONNE = 'A';
    static final int PREMIERE_LIGNE = 0;

    public Coordonnees(int numLigne, int numColonne) {
        ligne = numLigne;
        colonne = numColonne;
    }
    

    public static int carColVersNum(char nomCol, Plateau plat) {
        int nbCols = plat.getColonne()-1;
        final char carMin = CAR_PREMIERE_COLONNE;
        final char carMax = (char)(CAR_PREMIERE_COLONNE + nbCols);
        
        if ((nomCol < carMin) || (nomCol > carMax)) {
            throw new IllegalArgumentException(
                    "Appel incorrect à carVersNum, avec car = " + nomCol
                    + ". Les valeurs autorisées sont les caractères entre "
                    + carMin + " et " + carMax + ".");
        }
        return nomCol - CAR_PREMIERE_COLONNE;
    }
    
}
