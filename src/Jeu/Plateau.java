package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author A
 */
public class Plateau {

    

    private final int nbCols, nbRows;
    private final Case[][] plat;

    public Plateau(int nbRows, int nbCols) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.plat = new Case[nbRows][nbCols];
        initPlateau();
    }
    
    private void initPlateau(){
        for(int i = 0; i< nbRows; i++){
            for(int j =0; j< nbCols;j++){
                Coordonnees c = new Coordonnees(i,j);
                plat[i][j] = new Case(c);
            }
        }
    }

    public void display() {
        char col = 65;
        System.out.print("    ");
        for (int c = 0; c < nbCols; c++) {

            System.out.print(col);
            System.out.print(" ");
            col++;
        }
        
        System.out.println();

        displayBar();

        for (int r = 0; r < nbRows; r++) {
            System.out.print(r);
            System.out.print(" |");

            for (int c = 0; c < nbCols; c++) {
              

                System.out.print(plat[r][c].getAffichable());
                System.out.print(" ");
            }

            System.out.println(" |");

        }
        displayBar();
    }

    private void displayBar() {
        System.out.print("  +");
        for (int c = 0; c < nbCols; c++) {
            System.out.print(" -");
        }
        System.out.println(" +");
    }



    public int getLigne() {
        return nbRows;
    }
    public int getColonne() { 
        return nbCols;
    }
}
