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
public class Case {

    private Coordonnees coord;
    private Color color;
    

    public Case(Coordonnees c) {
        this.coord = c;
        this.color = Color.NONE;
    }

    public Color getColor(Case c) {
        return this.color;
    }
    
    public boolean positionDispo() {
        return getColor(this).NONE != null;
    }

    public enum Color {
        BLACK, WHITE, NONE
    };

}
