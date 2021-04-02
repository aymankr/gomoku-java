package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author A
 */
public class Plateau {

    private final int nbColonnes, nbLignes;
    private final Case[][] plat;

    public Plateau(int nbLignes, int nbColonnes) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.plat = new Case[nbLignes][nbColonnes];
        initPlateau();
    }

    /**
     * Initialiser un plateau composé de cases et coordonnées
     * 
     */
    private void initPlateau() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Coordonnees c = new Coordonnees(i, j);
                plat[i][j] = new Case(c);
            }
        }
    }

    /**
     * Afficher l'ensemble du plateau
     * 
     */
    public void display() {
        char col = 65;
        System.out.print("    ");
        for (int c = 0; c < nbColonnes; c++) {

            System.out.print(col);
            System.out.print(" ");
            col++;
        }

        System.out.println();

        displayBar();

        for (int r = 0; r < nbLignes; r++) {
            System.out.print(r);

            if (r >= 10) {
                System.out.print("|");
            } else {
                System.out.print(" |");
            }

            for (int c = 0; c < nbColonnes; c++) {

                System.out.print(plat[r][c].getAffichable());
                System.out.print(" ");
            }

            System.out.println(" |");

        }
        displayBar();
    }

    /**
     * Afficher les délimitations haut et bas du plateau
     * 
     */
    private void displayBar() {
        System.out.print("  +");
        for (int c = 0; c < nbColonnes; c++) {
            System.out.print(" -");
        }
        System.out.println(" +");
    }

    /**
     * Récolter le nombre de lignes du plateau
     * 
     * @return retourner ce nombre
     */
    public int getNbLignes() {
        return nbLignes;
    }

    /**
     * Récolter le nombre de colonnes
     * 
     * @return retourner ce nombre
     */
    public int getNbColonnes() {
        return nbColonnes;
    }
}
