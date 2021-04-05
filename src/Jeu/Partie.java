package Jeu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Partie {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    private List<String> coupsJoues;
    private Joueur j1, j2;
    private Plateau plat;
    private int nbTours;

    /**
     * Constructeur d'une partie
     * 
     * @param ja    joueur A
     * @param jb    joueur B
     * @param nbLig nombre de lignes
     * @param nbCol nombre de colonnes
     * @param nbT   nombre de tours
     */
    public Partie(Joueur ja, Joueur jb, int nbLig, int nbCol, int nbT) {
        this.j1 = ja;
        this.j2 = jb;
        this.nbTours = nbT;
        this.plat = new Plateau(nbLig, nbCol);
        this.coupsJoues = new ArrayList<>();
    }

    /**
     * Gestion d'une partie tour à tour ajout des coups joués dans l'affichage,
     * lorsque la partie est terminée afficher l'historique des coups
     * 
     */
    public void gererPartie() {
        boolean estNoir = true;
        boolean finiParVictoire = false;
        int nbT = nbTours;
        out.println("La partie dure " + nbT + " tours, à vous de jouer !" + "\n");

        while (!finiParVictoire && nbT > 0) {
            int tour = nbTours - nbT + 1;

            plat.display();
            out.println("\n" + "Tour " + tour + "\n");
            if (nbT % 2 == 0) {

                String coupJ1 = demandeCoup(j1.estUneIA(), j1);
                j1.jouer(coupJ1, coupsJoues, plat, this, estNoir);
                finiParVictoire = plat.victoire();

            } else if (nbT % 2 == 1) {

                String coupJ2 = demandeCoup(j2.estUneIA(), j2);
                j2.jouer(coupJ2, coupsJoues, plat, this, !estNoir);
                finiParVictoire = plat.victoire();
            }

            nbT--;
        }

        if (finiParVictoire && nbT % 2 == 0) {
            out.println("\n" + "----------- > Victoire de " + j2.getNom() + " < -----------");
        } else if (finiParVictoire && nbT % 2 == 1) {
            out.println("\n" + "----------- > Victoire de " + j1.getNom() + " < -----------");
        }

        affichageFin(finiParVictoire);
    }

    /**
     * Renvoyer le coup choisi par un joueur
     * 
     * @param s le coup
     * @return retourner ce coup
     */
    private String coupChoisi(String s) {

        out.println(s);
        out.print("--> ");
        String coup = lireLigne();

        String msgCoupInterdit = "Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                + (char) (65 + this.plat.getNbColonnes()) + ", puis de 0 à " + (this.plat.getNbLignes());

        try {
            if (!coupValide(coup)) {
                out.println(msgCoupInterdit);
                coup = coupChoisi(s);

            } else if (!coupDispo(coup)) {
                out.println(coup + " a déjà été joué, réessayez.");
                coup = coupChoisi(s);
            }
        } catch (Exception e) {
            out.println(msgCoupInterdit);
            coup = coupChoisi(s);
        }

        return coup;
    }

    private String demandeCoup(boolean estIA, Joueur j) {
        String coup = "";

        if (!estIA) {
            coup = coupChoisi("Coup de " + j.getNom() + " :");
        }
        return coup;
    }

    /**
     * Vérifier si un coup est valide
     * 
     * @param coup le coup
     * @return retourner vrai s'il est valide
     */
    public boolean coupValide(String coup) {
        int lig = 0, col = 0;
        boolean chaineValide = (coup != null && coup.length() == 2 || coup.length() == 3);
        if (chaineValide) {
            lig = plat.coupLigne(coup);
            col = plat.coupCol(coup);
        }

        return chaineValide && lig >= 0 && lig <= plat.getNbLignes() && col >= 0 && col <= plat.getNbColonnes();
    }

    /**
     * Vérifier si un coup est disponible
     * 
     * @param coup le coup
     * @return retourner vrai si disponible
     */
    public boolean coupDispo(String coup) {
        boolean dispo = true;

        for (String c : coupsJoues) {
            if (c.contains(coup)) {
                dispo = false;
            }
        }
        return dispo;
    }

    /**
     * Affichage de la fin de partie
     * 
     */
    private void affichageFin(boolean unGagnant) {
        String s = "";
        if (!unGagnant) {
            s = "Pas de gagnant, ";
        }

        int i = 1;
        out.println("\n" + s + "historique des coups : " + "\n");

        for (String c : coupsJoues) {
            out.println("N°" + i + " --> " + c);
            i++;
        }

        out.println("\n" + "Plateau final : " + "\n");
        plat.display();
    }

    /**
     * Lire l'entrée de l'utilisateur
     * 
     * @return retourner la réponse
     */
    private static String lireLigne() {
        return in.nextLine().trim();
    }
}
