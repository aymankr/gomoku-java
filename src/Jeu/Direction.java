/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu;

/**
 * Les 4 directions cardinales, ainsi que les 4 directions intermédiaires.
 */
public enum Direction {

    NORD, SUD, EST, OUEST, NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST;

    static Direction[] toutes() {
        Direction[] directions
                = {NORD, SUD, EST, OUEST, NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST};
        return directions;
    }


    /**
     * Renvoyer un tableau de directions complémentaires : NORD SUD
     * 
     * @return retourner ce tableau
     */
    private static Direction[] dirComplementaires1() {
        Direction[] directions = { NORD, SUD };
        return directions;
    }

    /**
     * EST OUEST
     * 
     * @return retourner ce tableau
     */
    private static Direction[] dirComplementaires2() {
        Direction[] directions = { EST, OUEST };
        return directions;
    }

    /**
     * NORD EST et SUD OUEST
     * 
     * @return retourner ce tableau
     */
    private static Direction[] dirComplementaires3() {
        Direction[] directions = { NORD_EST, SUD_OUEST };
        return directions;
    }

    /**
     * NORD OUEST ET SUD EST
     * 
     * @return retourner ce tableau
     */
    private static Direction[] dirComplementaires4() {
        Direction[] directions = { NORD_OUEST, SUD_EST };
        return directions;
    }

    /**
     * Renvoyer l'ensemble des tableaux de directions complémentaires
     * 
     * @return retourner ce tableau
     */
    public static Direction[][] toutesComplementaires() {
        Direction[][] dirComp = { dirComplementaires1(), dirComplementaires2(), dirComplementaires3(),
                dirComplementaires4() };
        return dirComp;
    }

    /**
     * Renvoie le nombre de cases parcourues horizontalement lorsqu'on suit cette
     * direction (0 pour Nord et Sud, -1 pour Ouest, 1 pour Est).
     *
     * @return nombre de cases horizontales de cette direction
     */
    int mvtHoriz() {
        int dh = -2;
        switch (this) {
        case NORD:
        case SUD:
            dh = 0;
            break;
        case EST:
        case NORD_EST:
        case SUD_EST:
            dh = 1;
            break;
        case OUEST:
        case NORD_OUEST:
        case SUD_OUEST:
            dh = -1;
            break;
        }
        return dh;
    }

    /**
     * Renvoie le nombre de cases parcourues verticalement lorsqu'on suit cette
     * direction (0 pour Est et Ouest, -1 pour Nord, 1 pour Sud).
     *
     * @return nombre de cases verticales de cette direction
     */
    int mvtVertic() {
        int dv = -2;
        switch (this) {
        case EST:
        case OUEST:
            dv = 0;
            break;
        case NORD:
        case NORD_EST:
        case NORD_OUEST:
            dv = -1;
            break;
        case SUD:
        case SUD_EST:
        case SUD_OUEST:
            dv = 1;
            break;
        }
        return dv;
    }

}
