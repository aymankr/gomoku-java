package Joueurs;

import Jeu.Partie;
import Positions.Case;
import Positions.Plateau;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe dérivée de Joueur pour créer une IA
 *
 * @author Ayman KACHMAR, Mathieu RAKOTOARISOA
 */
public class JoueurIA extends Joueur {

    /**
     * Constructeur d'un joueur IA
     *
     * @param nom le nom
     * @param estNoir sa couleur
     */
    public JoueurIA(String nom, boolean estNoir) {
        super(nom, estNoir);
        this.estIA = true;
    }

    /**
     * Jouer un coup pour une IA en cherchant un coup parmi les coups jouables
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

        ArrayList<String> cJouables = Case.getListCoupsJouables();
        Random r = new Random();
        int max = cJouables.size();
        int indexAleatoire = r.nextInt(max);
        String cp = cJouables.get(indexAleatoire);

        if (p.coupValide(cp, false, estIA)) {
            System.out.println("L'IA a joué : " + cp + "\n");
            listCoups.add(cp);
            plat.modifPlat(estNoir, cp);
            plat.actualiserPlateau(cp);

        } else {
            jouer(cp, listCoups, plat, p, estNoir, estIA);
        }
    }
}
