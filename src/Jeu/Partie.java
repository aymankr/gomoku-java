/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private Plateau p;
    private Match m;
    private ArrayList<String> coupJouesJoueur1, coupJouesJoueur2;
    private Joueur j1, j2;

    Partie(Joueur ja, Joueur jb, Match m) {
        this.j1 = ja;
        this.j2 = jb;
        this.m = m;
    }

    public void gererPartie() throws CoupChoisiException  {
        boolean estNoir = true;
        boolean tourNoir = true;
        int nbTours = m.getNbTours() / 2;

        while (!partieTerminee()) {
            if (nbTours % 2 == 0) {
                out.println(m.getJoueur(estNoir).getNom() + " joue un coup : " + "");
                String coupJ1 = coupChoisi();
                out.println(m.getJoueur(estNoir).getNom() + "a joué le coup : " + coupJ1);
                m.getJoueur(estNoir).setTour(!tourNoir);
                nbTours--;

            } else if (nbTours % 2 == 1) {
                out.println(m.getJoueur(!estNoir).getNom() + " joue un coup : " + "");
                // add dans la liste
                out.println(m.getJoueur(!estNoir).getNom() + "a joué le coup : ");
                m.getJoueur(!estNoir).setTour(tourNoir);
                nbTours--;
            }

        }
    }

    // vérifier aussi si 5 cases de meme color sont alignées
    public boolean partieTerminee() {
        return j1.getNbTours() == m.getNbTours() && j2.getNbTours() == m.getNbTours();
    }

    private String coupChoisi() throws CoupChoisiException {
        String coup = lireLigne();

        if (coup.charAt(0) < 65 && coup.charAt(0) > (char) p.getColonne() + 65 && coup.charAt(1) < 47
                && coup.charAt(1) > 47 + p.getLigne()) {
            throw new CoupChoisiException("Coup choisi inconnu : " + coup + "\n" + ".Les coups autorisés sont de A à "
                    + (char) (65 + p.getColonne()) + "\n" + "puis de 0 à " + p.getLigne());

        }
        return coup;
    }

    private static String lireLigne() {
        return in.nextLine().trim();
    }
}
