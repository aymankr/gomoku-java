/*.........
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author A
 */
public class Case {

    private Coordonnees coord;
    private Color color;
    private boolean jouable;

    /**
     * Constructeur d'une case
     * 
     * @param c sa coordonnée
     */
    public Case(Coordonnees c) {
        this.coord = c;
        this.color = Color.NONE;
        this.jouable = false;
    }

    /**
     * Affichage de la case actuelle suivant sa Color
     * 
     * @return retourner le caractère associé à sa Color
     */
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

    /**
     * Modifier la Color d'une case si le joueur est Noir
     * 
     * @param estNoir vrai si le joueur est noir
     */
    public void setColor(boolean estNoir) {
        if (estNoir) {
            this.color = Color.BLACK;
        } else {
            this.color = Color.WHITE;
        }
    }

    /**
     * Les 3 types de cases possibles
     */
    private enum Color {
        BLACK, WHITE, NONE
    };

    /**
     * Actualisation d'une case si elle est jouable
     * 
     * @param p    le plateau
     * @param plat les cases du plateau
     */
    public void actualiseCaseJouable(Plateau p, Case[][] plat) {
        Case caseTmp;
        Coordonnees[] cVoisines = coord.voisines(p);

        for (Coordonnees c : cVoisines) {
            caseTmp = plat[c.getLigne()][c.getCol()];

            if (caseTmp.color.equals(Color.NONE) && !this.color.equals(Color.NONE)) {
                caseTmp.jouable = true;
            }
        }
    }

    /**
     * Vérifier si pour un plateau, 5 cases de même Color sont alignées
     * 
     * @param p    le plateau
     * @param plat les cases du plateau
     * @return retourner vrai si alignement
     */
    public boolean victoireAlignement(Plateau p, Case[][] plat) {

        int nbAlignees = 0;
        boolean victoire = false;

        for (Direction[] dirs : Direction.toutesComplementaires()) {

            nbAlignees = 0;
            Coordonnees[] coordVois = coord.voisinesComplementaires(p, dirs);

            for (Coordonnees c : coordVois) {

                Case caseTmp = plat[c.getLigne()][c.getCol()];

                if (!caseTmp.color.equals(Color.NONE) && caseTmp.color.equals(this.color)) {
                    nbAlignees++;
                }
            }

            if (nbAlignees >= 4) {
                victoire = true;
            }
        }
        return victoire;
    }

    /**
     * Retourner si une case est jouable
     * 
     * @return retourne vrai ssi elle est jouable
     */
    public boolean estJouable() {
        return jouable;
    }
}
