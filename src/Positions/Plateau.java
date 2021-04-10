package Positions;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Classe permettant de rassembler les cases utilisant des coordonnées
 * 
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class Plateau {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    private final int nbColonnes, nbLignes;
    private final Case[][] plat;

    /**
     * Constructeur d'un plateau
     * 
     * @param nbLignes nombre de lignes
     * @param nbColonnes nombre de colonnes
     */
    public Plateau(int nbLignes, int nbColonnes) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.plat = new Case[nbLignes][nbColonnes];
        initPlateau();
    }

    /**
     * Initialiser un plateau composé de cases
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
     * Vérifier s'il y a victoire lors d'un alignement de cinq cases de même
     * Color
     *
     * @param coup le coup
     * @return retourner vrai s'il y a victoire
     */
    public boolean victoire(String coup) {
        Coordonnees c = Coordonnees.convertCoord(coup);

        plat[c.getLigne()][c.getCol()].setGagnante(this, plat);

        return plat[c.getLigne()][c.getCol()].estGagnante();
    }

    /**
     * Actualiser le plateau après le coup joué
     *
     * @param coup le coup joué
     */
    public void actualiserPlateau(String coup) {
        Coordonnees c = Coordonnees.convertCoord(coup);

        plat[c.getLigne()][c.getCol()].actualiseCaseJouable(this, plat, coup);
    }

    /**
     * Afficher l'ensemble du plateau
     *
     */
    public void affiche() {

        char col = 65;
        out.print("    ");
        for (int c = 0; c < nbColonnes; c++) {

            out.print(col);
            out.print(" ");
            col++;
        }
        out.println();
        afficheBar();

        for (int r = 0; r < nbLignes; r++) {
            out.print(r);
            if (r >= 10) {
                out.print("|");
            } else {
                out.print(" |");
            }
            for (int c = 0; c < nbColonnes; c++) {
                out.print(" ");
                out.print(plat[r][c].afficheCase());

            }
            out.println(" |");
        }
        afficheBar();
    }

    /**
     * Afficher les délimitations haut et bas du plateau
     *
     */
    private void afficheBar() {
        out.print("  +");
        for (int c = 0; c < nbColonnes; c++) {
            out.print(" -");
        }
        out.println(" +");
    }

    /**
     * Modifier le plateau lors d'un coup joué
     *
     * @param estNoir vrai si le joueur est noir
     * @param coup le coup
     */
    public void modifPlat(boolean estNoir, String coup) {
        Coordonnees c = Coordonnees.convertCoord(coup);

        plat[c.getLigne()][c.getCol()].setCouleur(estNoir);
    }

    /**
     * Récolter le nombre de lignes du plateau
     *
     * @return retourner ce nombre
     */
    public int getNbLignes() {
        return nbLignes - 1;
    }

    /**
     * Récolter le nombre de colonnes
     *
     * @return retourner ce nombre
     */
    public int getNbColonnes() {
        return nbColonnes - 1;
    }

    /**
     * Récolter une case du plateau
     *
     * @param i ligne
     * @param j colonne
     * @return retourner la case
     */
    public Case getCase(int i, int j) {
        return plat[i][j];
    }

}
