package Joueurs;

import Jeu.Partie;
import Positions.Plateau;
import java.util.ArrayList;

/**
 * Classe dérivée de Joueur pour créer une joueur humain
 * 
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class JoueurHumain extends Joueur {

    /**
     * Constructeur d'un joueur humain
     *
     * @param nom son nom
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
     * @param estIA vrai ssi le joueur est une IA
     */
    @Override
    public void jouer(String coup, ArrayList<String> listCoups, Plateau plat, Partie p, boolean estNoir,
            boolean estIA) {
        listCoups.add(coup);
        plat.modifPlat(estNoir, coup);
        plat.actualiserPlateau(coup);
    }
}
