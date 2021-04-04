/*.........
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

    public char getAffichable() {
        char g;
        switch (color) {
        case BLACK:
            g = 'X';
            break;
        case WHITE:
            g = 'O';
            break;
        default:
            g = ' ';
            break;
        }
        return g;
    }

    public void setColor(boolean estNoir) {
        if (estNoir) {
            this.color = Color.BLACK;
        } else {
            this.color = Color.WHITE;
        }
    }

    private enum Color {
        BLACK, WHITE, NONE
    };

    public boolean victoireAlignement(Plateau p, Case[][] plat) {

        int nbAlignees = 0;
        boolean victoire = false;

        for (Direction[] dirs : Direction.toutesComplementaires()) {

            nbAlignees = 0;
            Coordonnees[] coordVois = coord.voisComplementaires(p, dirs);

            for (Coordonnees c : coordVois) {

                Case caseTmp = plat[c.getLigne()][c.getCol()];

                if (caseTmp.color.equals(this.color) && !caseTmp.color.equals(Color.NONE)) {
                    nbAlignees++;

                }
            }
            if (nbAlignees >= 4) {
                victoire = true;
            }
        }

        return victoire;
    }
}
