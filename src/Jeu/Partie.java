package Jeu;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Set;

/**
 *
 * @author A
 */
public class Partie {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;

    private TreeMap<String, String> coupJoues;
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
        this.coupJoues = new TreeMap<>();
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

                String coupJ1 = coupChoisi("Coup de " + j1.getNom() + " :");
                coupJoues.put(coupJ1, j1.getNom());
                plat.modifPlat(this, estNoir, coupJ1);

            } else if (nbT % 2 == 1) {

                String coupJ2 = coupChoisi("Coup de " + j2.getNom() + " :");
                coupJoues.put(coupJ2, j2.getNom());
                plat.modifPlat(this, !estNoir, coupJ2);
            }
            finiParVictoire = plat.victoire();
            nbT--;
        }

        if (finiParVictoire && nbT % 2 == 0) {
            out.println("Victoire de " + j2.getNom() + " !");
        } else if (finiParVictoire && nbT % 2 == 1) {
            out.println("Victoire de " + j1.getNom() + " !");
        }

        afficherCoupsFin(finiParVictoire);
    }

    private String coupChoisi(String s) {

        out.println(s);
        out.print("--> ");
        String coup = lireLigne();

        String msgCoupInterdit = "Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                + (char) (65 + this.getPlateau().getNbColonnes() - 1) + ", puis de 0 à "
                + (this.getPlateau().getNbLignes() - 1);

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

    public boolean coupValide(String coup) {
        int lig = 0, col = 0;
        boolean chaineValide = (coup != null && coup.length() == 2 || coup.length() == 3);
        if (chaineValide) {
            lig = plat.coupLigne(coup);
            col = plat.coupCol(coup);
        }

        return chaineValide && lig >= 0 && lig < plat.getNbLignes() && col >= 0 && col < plat.getNbColonnes();
    }

    private boolean coupDispo(String coup) {
        return coupJoues.get(coup) == null;
    }

    /**
     * Afficher les coups joués par les joueurs en fin de partie
     * 
     */
    private void afficherCoupsFin(boolean unGagnant) {
        String s = "";
        if (!unGagnant) {
            s = "Pas de gagnants, ";
        }

        int i = 1;
        out.println("\n" + s + "historique des coups : " + "\n");
        Set<String> coups = coupJoues.keySet();

        for (String c : coups) {
            out.println("N°" + i + " --> " + coupJoues.get(c) + " joue " + c);
            i++;
        }

        out.println("\n" + "Plateau final : " + "\n");
        plat.display();
    }

    /**
     * Récolter le plateau
     * 
     * @return retourner ce plateau
     */
    private Plateau getPlateau() {
        return plat;
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
