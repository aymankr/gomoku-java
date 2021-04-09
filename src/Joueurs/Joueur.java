package Joueurs;

import Jeu.Partie;
import Positions.Plateau;
import java.util.ArrayList;

/**
 * Classe abstraite "mère" pour créer des joueurs
 * 
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
abstract public class Joueur {

    protected boolean estIA;
    private final boolean estNoir;
    private String nom;

    /**
     * Constructeur du joueur
     *
     * @param n le nom
     * @param estNoir sa couleur
     */
    public Joueur(String n, boolean estNoir) {
        this.estNoir = estNoir;
        this.nom = n;
        if (estIA) {
            nom = "IA";
        }
    }

    /**
     * Renvoyer le nom du joueur en question
     *
     * @return retourner le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Indique si le joueur actuel est une IA
     *
     * @return retourne vrai ssi le joueur est une IA
     */
    public boolean estUneIA() {
        return estIA;
    }

    /**
     * Méthode abstraite pour jouer un coup
     *
     * @param coup le coup
     * @param listCoups la liste des coups
     * @param plat le plateau
     * @param p la partie
     * @param estNoir vrai ssi le joueur est noir
     * @param estIA vrai ssi le joueur est une IA
     */
    public abstract void jouer(String coup, ArrayList<String> listCoups, Plateau plat, Partie p, boolean estNoir,
            boolean estIA);
}
