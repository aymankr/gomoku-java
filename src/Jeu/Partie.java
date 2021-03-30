package Jeu;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Partie {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = System.out;
    private Match m;
    private ArrayList<String> coupJouesJoueur1, coupJouesJoueur2;
    private Joueur j1, j2;

    public Partie(Joueur ja, Joueur jb, Match m) {
        this.j1 = ja;
        this.j2 = jb;
        this.m = m;
    }

    public void gererPartie() throws CoupChoisiException  {
        boolean estNoir = true;
        int nbTours = m.getNbTours() / 2;
        out.println("La partie dure " + nbTours + " tours, à vous de jouer !");

        while (!partieTerminee() && nbTours > 0) {
            if (nbTours % 2 == 0) {
                out.println(m.getJoueur(estNoir).getNom() + " joue un coup : " + "");
                String coupJ1 = coupChoisi();
                coupJouesJoueur1.add(coupJ1);
                // ajout de case, set la position en black
                out.println(m.getJoueur(estNoir).getNom() + "a joué le coup : " + coupJ1);
                nbTours--;

            } else if (nbTours % 2 == 1) {
                out.println(m.getJoueur(!estNoir).getNom() + " joue un coup : " + "");
                String coupJ2 = coupChoisi();
                coupJouesJoueur2.add(coupJ2);
                // ajout de case, set la position en white
                out.println(m.getJoueur(!estNoir).getNom() + "a joué le coup : ");
                nbTours--;
            }

        }
    }

    // vérifier aussi si 5 cases de meme color sont alignées
    public boolean partieTerminee() {
        return j1.getNbTours() == m.getNbTours() && j2.getNbTours() == m.getNbTours();
    }

    public String coupChoisi() throws CoupChoisiException {
        String coup = lireLigne();

        if (coup.charAt(0) < 65 && coup.charAt(0) > (char) p.getColonne() + 65 && coup.charAt(1) < 47
                && coup.charAt(1) > 47 + m.getPlateau().getLigne()) {
            throw new CoupChoisiException("Coup choisi inconnu : " + coup + "\n" + ".Les coups autorisés sont de A à "
                    + (char) (65 + m.getPlateau().getColonne()) + "\n" + "puis de 0 à " + m.getPlateau().getLigne());

        }
        return coup;
    }

    private static String lireLigne() {
        return in.nextLine().trim();
    }
}
