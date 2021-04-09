package Positions;

import java.util.Arrays;

/**
 * Classe pour utiliser des coordonnées
 * 
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class Coordonnees {

    private final int ligne;
    private final int colonne;
    private static final char CAR_PREMIERE_COLONNE = 'A';

    /**
     * Constructeur d'un Coordonnees
     *
     * @param numLigne numéro de ligne
     * @param numColonne numéro de colonne
     */
    public Coordonnees(int numLigne, int numColonne) {
        ligne = numLigne;
        colonne = numColonne;
    }

    /**
     * Convertir un caractère de colonne en numéro de colonne
     *
     * @param nomCol le caractère
     * @return retourner le numéro de colonne
     */
    public static int carColVersNum(char nomCol) {
        return nomCol - CAR_PREMIERE_COLONNE;
    }

    /**
     * Convertir numéro de colonne en caractère
     *
     * @param numCol numéro
     * @return retourner le caractère
     */
    public static char numVersCarCol(int numCol) {
        return (char) (CAR_PREMIERE_COLONNE + numCol);
    }

    /**
     * Convertir des coordonnees en chaine
     *
     * @return retourner la chaine
     */
    public String coordEnString() {
        String lig = String.valueOf(ligne);
        String col = "" + numVersCarCol(colonne);

        return col + lig;
    }

    /**
     * Convertir une chaine en coordonnées
     *
     * @param coup le coup
     * @return retourner la chaine
     */
    public static Coordonnees convertCoord(String coup) {

        char charCol = coup.charAt(0);
        int colToInt = Coordonnees.carColVersNum(charCol);

        char charLig1 = coup.charAt(1);
        char charLig2;
        String nb = "" + charLig1;
        int lig = 0;

        if (coup.length() == 3) {
            charLig2 = coup.charAt(2);
            nb += "" + charLig2;
        }

        lig = Integer.parseInt(nb);

        return new Coordonnees(lig, colToInt);
    }

    /**
     * Indique si la coordonnée est dans le plateau
     *
     * @param p le plateau
     * @return retourne vrai ssi les coordonnées sont dans le plateau
     */
    public boolean estDansPlateau(Plateau p) {
        return this.ligne >= 0 && this.colonne >= 0 && this.ligne <= p.getNbLignes()
                && this.colonne <= p.getNbColonnes();
    }

    /**
     * Retourner la coordonnée suivante (sans décalage) selon la direction
     *
     * @param d la direction
     * @return retourner cette coordonnée
     */
    public Coordonnees suivante(Direction d) {
        return new Coordonnees(ligne + d.mvtVertic(), colonne + d.mvtHoriz());
    }

    /**
     * Récolter un tableau de coordonnées voisines (8 maximum) à celle actuelle
     *
     * @param p le plateau
     * @return retourner ce tableau
     */
    public Coordonnees[] voisines(Plateau p) {
        Coordonnees[] voisines = new Coordonnees[8];
        int nbVoisines = 0;
        for (Direction d : Direction.toutes()) {
            if (suivante(d).estDansPlateau(p)) {
                voisines[nbVoisines] = suivante(d);
                nbVoisines++;

            }
        }
        return Arrays.copyOf(voisines, nbVoisines);
    }

    /**
     * Récolter un tableau de coordonnées suivantes (jusqu'au décalage de 5) à
     * la coordonnée actuelle suivant une direction choisie
     *
     * @param d la direction
     * @param p le plateau
     * @return retourner ce tableau
     */
    private Coordonnees[] suivantes(Direction d, Plateau p) {
        Coordonnees[] suiv = new Coordonnees[4];
        int facteur_Decalage = 0;

        for (int i = 0; i < 4; i++) {
            Coordonnees c = new Coordonnees(this.ligne + d.mvtVertic() + facteur_Decalage * d.mvtVertic(),
                    this.colonne + d.mvtHoriz() + facteur_Decalage * d.mvtHoriz());
            if (c.estDansPlateau(p)) {
                suiv[i] = new Coordonnees(c.ligne, c.colonne);
            }
            facteur_Decalage++;
        }
        return suiv;
    }

    /**
     * Récolter un tableau de coordonnées voisines sur les directions
     * complémentaires
     *
     * @param p le plateau
     * @param tabComp tableau de directions complémentaires
     * @return retourner le tableau de coordonnées
     */
    public Coordonnees[] voisinesComplementaires(Plateau p, Direction[] tabComp) {
        Coordonnees[] tabVoisins = new Coordonnees[8];

        int nbVoisines = 0;

        for (Direction d : tabComp) {
            Coordonnees[] suiv = suivantes(d, p);

            for (int i = 0; i < 4; i++) {
                if (suiv[i] != null && suiv[i].estDansPlateau(p)) {
                    tabVoisins[nbVoisines] = suiv[i];
                    nbVoisines++;
                }
            }
        }
        return Arrays.copyOf(tabVoisins, nbVoisines);
    }

    /**
     * Récolter la ligne de la coordonnée actuelle
     *
     * @return retourner le numéro de la ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Récolter la colonne
     *
     * @return retourner la colonne
     */
    public int getCol() {
        return colonne;
    }
}
