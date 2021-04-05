package Jeu;

import java.util.List;
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

    @Override
    public void jouer(String coup, List<String> listCoups, Plateau plat, Partie p, boolean estNoir) {
        Random r1 = new Random();
        int ligneMax = plat.getNbLignes();
        int ligneAleat = r1.nextInt(ligneMax);
        String ligne = String.valueOf(ligneAleat);

        Random r2 = new Random();
        int colMax = plat.getNbColonnes();
        int colAleat = r2.nextInt(colMax);
        char charCol = Coordonnees.numVersCarCol(colAleat);
        String colonne = "" + charCol;

        String coupAleatoire = colonne + ligne;
        coup = coupAleatoire;

        if (p.coupDispo(coup)) {
            System.out.println("L'IA a joué : " + coup + "\n");
            listCoups.add(coup);
            plat.modifPlat(p, estNoir, coup);
        } else {
            jouer(coup, listCoups, plat, p, estNoir);
        }
    }
}
