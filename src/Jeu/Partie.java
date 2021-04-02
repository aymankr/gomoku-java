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

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;

    private TreeMap<String, String> coupJoues;
    private Joueur j1, j2;
    private Plateau p;
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
        this.p = new Plateau(nbLig, nbCol);
        this.coupJoues = new TreeMap<String, String>();
    }

    public void gererPartie() {
        int nbT = nbTours;
        out.println("La partie dure " + nbT + " tours, à vous de jouer !");

        while (!partieTerminee() && nbT > 0) {
            int tour = nbTours - nbT + 1;

            p.display();
            out.println("Tour : " + tour);
            if (nbT % 2 == 0) {

                String coupJ1 = coupChoisi("Coup de : " + j1.getNom());
                coupJoues.put(coupJ1, j1.getNom());

                // ajout de case dans l'affichage, set la position en black

            } else if (nbT % 2 == 1) {

                String coupJ2 = coupChoisi("Coup de : " + j2.getNom());
                coupJoues.put(coupJ2, j2.getNom());

                // ajout de case, set la position en white
            }
            nbT--;

        }
        afficherCoupsFin();
    }

    // vérifier aussi si 5 cases de meme color sont alignées
    public boolean partieTerminee() {
        return j1.getNbToursJoues() == this.getNbTours() && j2.getNbToursJoues() == this.getNbTours();
    }

    public String coupChoisi(String s) {
        out.println(s);
        out.print("--> ");
        String coup = lireLigne();

        if (coup.charAt(0) < 65 || coup.charAt(0) >= (char) this.getPlateau().getNbColonnes() + 65
                || coup.charAt(1) < 47 || coup.charAt(1) > 47 + this.getPlateau().getNbLignes()) {
            out.println("Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                    + (char) (65 + this.getPlateau().getNbColonnes() - 1) + ", puis de 0 à "
                    + (this.getPlateau().getNbLignes() - 1));
            coup = coupChoisi(s);
        } else if (!coupDispo(coup)) {
            out.println(coup + " a déjà été joué, réessayez.");
            coup = coupChoisi(s);
        }

        return coup;

    }

    private boolean coupDispo(String coup) {
        return coupJoues.get(coup) == null;
    }

    /**
     * Afficher les coups joués par les joueurs en fin de partie
     * 
     */
    private void afficherCoupsFin() {
        out.println("Fin de partie, historique des coups : ");
        Set<String> coups = coupJoues.keySet();

        for (String c : coups) {
            System.out.println(coupJoues.get(c) + " a joué : " + c);
        }
    }

    /*
     * public void jouerCoup(Case c, boolean estNoir, String coup) { if
     * (c.positionDispo()) { c.setColor(estNoir); } else { c.setColor(!estNoir); }
     * ajoutCoupPlat(coup); }
     * 
     * public void ajoutCoupPlat(String coup) { char charCol = coup.charAt(0); int
     * colToInt = Coordonnees.carColVersNum(charCol, p);
     * 
     * }
     * 
     * public Joueur getJoueur(boolean estNoir) { if (estNoir) { return j1; } else {
     * return j2; } }
     */

    /**
     * Récolter le nombre de tours de la partie
     * 
     * @return retourner ce nombre
     */
    private int getNbTours() {
        return nbTours;
    }

    /**
     * Récolter le plateau
     * 
     * @return retourner ce plateau
     */
    private Plateau getPlateau() {
        return p;
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
