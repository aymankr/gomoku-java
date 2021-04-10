package Positions;

import java.util.ArrayList;

/**
 * Classe permettant d'utiliser des cases
 *
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class Case {

    private final Coordonnees coord;
    private Couleur couleur;
    private boolean jouable;
    private boolean gagnante;
    private static ArrayList<String> coupsJouables = new ArrayList<>();

    /**
     * Constructeur d'une case
     *
     * @param c sa coordonnée
     */
    public Case(Coordonnees c) {
        this.coord = c;
        this.couleur = Couleur.NONE;
        this.jouable = false;
    }

    /**
     * Affichage de la case actuelle suivant sa couleur
     *
     * @return retourner le caractère associé à sa couleur
     */
    public char afficheCase() {
        char g;
        switch (couleur) {
            case BLACK:
                g = 'X';
                break;
            case WHITE:
                g = 'O';
                break;
            default:
                if (jouable) {
                    g = '.';
                    coupsJouables.add(this.coord.coordEnString());
                } else {
                    g = ' ';
                }
                break;
        }
        return g;
    }

    /**
     * Modifier la couleur d'une case si le joueur est Noir
     *
     * @param estNoir vrai si le joueur est noir
     */
    public void setCouleur(boolean estNoir) {
        if (estNoir) {
            this.couleur = Couleur.BLACK;
        } else {
            this.couleur = Couleur.WHITE;
        }
    }

    /**
     * Les 3 types de cases possibles
     */
    private enum Couleur {
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

            cas.jouable = (cas.jouable || cas.couleur.equals(Couleur.NONE) && !cs.couleur.equals(Couleur.NONE));
        }
    }

    /**
     * Vérifier si pour un plateau, 5 cases de même couleur sont alignées
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

                if (!caseTmp.couleur.equals(Couleur.NONE) && caseTmp.couleur.equals(this.couleur)) {
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

    /**
     * Liste des coups jouables
     *
     * @return retourner la liste
     */
    public static ArrayList getListCoupsJouables() {
        return coupsJouables;
    }
}
