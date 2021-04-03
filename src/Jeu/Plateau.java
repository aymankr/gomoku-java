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

    public boolean partieTerminee() {
        boolean casesAlignees = false;
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                Case c = plat[i][j];
                casesAlignees = c.victoireAlignement(this, plat);
            }
        }
        return casesAlignees;
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

    public void modifPlat(Partie partie, boolean estNoir, String coup) {

        int cC = coupCol(coup);
        int cL = coupLigne(coup);
        Coordonnees c = new Coordonnees(cC, cL);

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

    public int coupCol(String coup) {
        char charCol = coup.charAt(0);
        int colToInt = Coordonnees.carColVersNum(charCol, getNbColonnes());

        return colToInt;
    }

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
