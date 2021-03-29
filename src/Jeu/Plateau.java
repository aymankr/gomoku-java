/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author A
 */
public class Plateau {

    Map<Integer, String> plat = new HashMap<>();
    
    
    private final int nbCols, nbRows;
    private final char[][] image;


    public Plateau(int nbCols, int nbRows) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.image = new char[nbCols][nbRows];
    }
    
   

    public void display(){
        char col = 65;
        System.out.print("    ");
        for (int c=0; c< nbCols; c++){
            
            System.out.print(col);
            System.out.print(" ");
            col++;
        }
        displayRows();
        System.out.println();
        
        displayBar();
          
       
        for (int r = 0; r < nbRows; r++) {
            System.out.print(r);
            System.out.print(" |");
            
            for (int c = 0; c < nbCols; c++) {
                image[c][r] = 32;
              
                System.out.print(image[c][r]);
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
    
    
    private void displayRows() {
        
    }
    

    }
