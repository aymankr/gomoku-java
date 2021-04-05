package Jeu;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Plateau {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    private final int nbColonnes, nbLignes;
    private final Case[][] plat;

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
     * Vérifier s'il y a victoire lors d'un alignement de cinq cases de même Color
     * 
     * @return retourner vrai s'il y a victoire
     */
    public boolean victoire() {
        boolean vict = false;
        int i = 0, j = 0;
        boolean fini = false;

        while (i < nbLignes && j < nbColonnes && !fini) {
            Case c = plat[i][j];
            vict = c.victoireAlignement(this, plat);

            if (vict) {
                fini = true;
            }
            c.actualiseCaseJouable(this, plat);
            i++;
            j++;
        }

        return vict;
    }

    /**
     * Afficher l'ensemble du plateau
     * 
     */
    public void display() {

        char col = 65;
        out.print("    ");
        for (int c = 0; c < nbColonnes; c++) {

            out.print(col);
            out.print(" ");
            col++;
        }

        out.println();

        displayBar();

        for (int r = 0; r < nbLignes; r++) {
            out.print(r);

            if (r >= 10) {
                out.print("|");
            } else {
                out.print(" |");
            }

            for (int c = 0; c < nbColonnes; c++) {
                out.print(" ");
                out.print(plat[r][c].getAffichable());

            }

            out.println(" |");

        }
        displayBar();
    }

    /**
     * Modifier le plateau lors d'un coup joué
     * 
     * @param partie  la partie
     * @param estNoir vrai si le joueur est noir
     * @param coup    le coup
     */
    public void modifPlat(Partie partie, boolean estNoir, String coup) {

        int cC = coupCol(coup);
        int cL = coupLigne(coup);
        Coordonnees c = new Coordonnees(cL, cC);

        if (c.estDansPlateau(this)) {
            plat[cL][cC].setColor(estNoir);
        }
    }

    /**
     * Afficher les délimitations haut et bas du plateau
     * 
     */
    private void displayBar() {
        out.print("  +");
        for (int c = 0; c < nbColonnes; c++) {
            out.print(" -");
        }
        out.println(" +");
    }

    /**
     * Récolter le numéro de la colonne du coup joué
     * 
     * @param coup le coup
     * @return retourner la colonne
     */
    public int coupCol(String coup) {
        char charCol = coup.charAt(0);
        int colToInt = Coordonnees.carColVersNum(charCol, getNbColonnes());

        return colToInt;
    }

    /**
     * Récolter le numéro de la ligne du coup joué
     * 
     * @param coup le coup
     * @return retourner la ligne
     */
    public int coupLigne(String coup) {
        char charLig1 = coup.charAt(1);
        char charLig2;
        String nb = "" + charLig1;
        int lig = 0;

        if (coup.length() == 3) {
            charLig2 = coup.charAt(2);
            nb += "" + charLig2;
        }

        lig = Integer.parseInt(nb);
        return lig;
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
