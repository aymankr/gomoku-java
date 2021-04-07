
import java.util.ArrayList;
import java.util.Random;

public class JoueurIA extends Joueur {

    /**
     * Constructeur d'un joueur IA
     *
     * @param nom     le nom
     * @param estNoir sa couleur
     */
    JoueurIA(String nom, boolean estNoir) {
        super(nom, estNoir);
        this.estIA = true;
    }

    /**
     * Jouer un coup pour une IA en cherchant une coordonnée voisine aléatoire d'une
     * case déjà jouée, puis parmi ces voisines en choisir une qui est jouable
     *
     * @param coup      le coup
     * @param listCoups la liste des coups
     * @param plat      le plateau
     * @param p         la partie
     * @param estNoir   vrai ssi le joueur est noir
     */
    @Override
    public void jouer(String coup, ArrayList<String> listCoups, Plateau plat, Partie p, boolean estNoir,
            boolean estIA) {

        Random r1 = new Random();
        int max1 = listCoups.size();
        int indexAleatoire1 = r1.nextInt(max1);
        String coupExistant = listCoups.get(indexAleatoire1);
        Coordonnees coordExistant = Coordonnees.convertCoord(coupExistant);

        Coordonnees[] voisines = coordExistant.voisines(plat);
        String[] coupsPossibles = new String[voisines.length];

        int i = 0;
        for (Coordonnees c : voisines) {
            coupsPossibles[i] = c.coordEnString();
            i++;
        }

        Random r2 = new Random();
        int max2 = coupsPossibles.length;
        int indexAleatoire2 = r2.nextInt(max2);
        String cp = coupsPossibles[indexAleatoire2];
        

        if (p.coupValide(cp, false, estIA)) {
            System.out.println("L'IA a joué : " + cp + "\n");
            listCoups.add(cp);
            plat.modifPlat(p, estNoir, cp);
            plat.actualiserPlateau(cp);

        } else {
            jouer(cp, listCoups, plat, p, estNoir, estIA);
        }
    }
}
