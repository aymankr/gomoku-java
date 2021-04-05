package Jeu;

import java.util.List;

public class JoueurHumain extends Joueur {

    /**
     * Constructeur d'un joueur humain
     * 
     * @param nom     son nom
     * @param estNoir sa couleur
     */
    public JoueurHumain(String nom, boolean estNoir) {
        super(nom, estNoir);
        this.estIA = false;
    }

    /**
     * Jouer un coup pour un joueur humain
     *
     * @param coup le coup
     * @param listCoups la liste des coups
     * @param plat le plateau
     * @param p la partie
     * @param estNoir vrai ssi le joueur est noir
     */
    @Override
    public void jouer(String coup, List<String> listCoups, Plateau plat, Partie p, boolean estNoir) {
        listCoups.add(coup);
        plat.modifPlat(p, estNoir, coup);
    }
}
