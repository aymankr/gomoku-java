package Positions;

/**
 * Classe permettant d'utiliser des cases
 *
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class Case {

    private final Coordonnees coord;
    private Color color;
    private boolean jouable;
    private boolean gagnante;

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
                if (jouable) {
                    g = '.';
                } else {
                    g = ' ';
                }
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
     * @param p le plateau
     * @param plat les cases du plateau
     * @param coup le coup
     */
    public void actualiseCaseJouable(Plateau p, Case[][] plat, String coup) {
        Case cs;

        Coordonnees c = Coordonnees.convertCoord(coup);
        cs = p.getCase(c.getLigne(), c.getCol());

        Coordonnees[] cVoisines = c.voisines(p);

        for (Coordonnees cv : cVoisines) {
            Case cas = p.getCase(cv.getLigne(), cv.getCol());

            cas.jouable = (cas.jouable || cas.color.equals(Color.NONE) && !cs.color.equals(Color.NONE));
        }
    }

    /**
     * Vérifier si pour un plateau, 5 cases de même Color sont alignées
     *
     * @param p le plateau
     * @param plat les cases du plateau
     */
    public void setGagnante(Plateau p, Case[][] plat) {

        int nbAlignees = 0;

        for (Direction[] dirs : Direction.toutesComplementaires()) {

            nbAlignees = 0;
            Coordonnees[] coordVois = coord.voisinesComplementaires(p, dirs);

            for (Coordonnees c : coordVois) {
                Case caseTmp = plat[c.getLigne()][c.getCol()];

                if (!caseTmp.color.equals(Color.NONE) && caseTmp.color.equals(this.color)) {
                    nbAlignees++;
                }
            }
            gagnante = (gagnante || nbAlignees >= 4);
        }
    }

    /**
     * Retourner si une case est jouable
     *
     * @return retourne vrai ssi elle est jouable
     */
    public boolean estJouable() {
        return jouable;
    }

    /**
     * Retourner si une case est jouable
     *
     * @return retourne vrai ssi elle est jouable
     */
    public boolean estGagnante() {
        return gagnante;
    }
}
