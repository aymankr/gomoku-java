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
   
    private ArrayList<String> coupJouesJoueur1, coupJouesJoueur2;
    private Joueur j1, j2;
    private Plateau p;
    private int nbTours;

    public Partie(Joueur ja, Joueur jb,  int nbLig, int nbCol, int nb) {
        this.j1 = ja;
        this.j2 = jb;
        this.nbTours = nb;
        this.p = new Plateau(nbLig, nbCol);
    }

    public void gererPartie() throws CoupChoisiException  {
        boolean estNoir = true;
        int nbT = nbTours / 2;
        out.println("La partie dure " + nbT + " tours, à vous de jouer !");
        p.display();

        while (!partieTerminee() && nbT > 0) {
            if (nbT % 2 == 0) {
                out.println(this.j1.getNom() + " joue un coup : " + "");
                String coupJ1 = coupChoisi();
                coupJouesJoueur1.add(coupJ1);
                // ajout de case, set la position en black
                out.println(this.j1.getNom() + "a joué le coup : " + coupJ1);
                nbT--;

            } else if (nbT % 2 == 1) {
                out.println(this.j2.getNom() + " joue un coup : " + "");
                String coupJ2 = coupChoisi();
                coupJouesJoueur2.add(coupJ2);
                // ajout de case, set la position en white
                out.println(this.j2.getNom() + "a joué le coup : ");
                nbT--;
            }

        }
    }

    // vérifier aussi si 5 cases de meme color sont alignées
    public boolean partieTerminee() {
        return j1.getNbTours() == this.getNbTours() && j2.getNbTours() == this.getNbTours();
    }

    public String coupChoisi() throws CoupChoisiException {
        String coup = lireLigne();

        if (coup.charAt(0) < 65 && coup.charAt(0) > (char) this.getPlateau().getColonne() + 65 && coup.charAt(1) < 47
                && coup.charAt(1) > 47 + this.getPlateau().getLigne()) {
            throw new CoupChoisiException("Coup choisi inconnu : " + coup + "\n" + ".Les coups autorisés sont de A à "
                    + (char) (65 + this.getPlateau().getColonne()) + "\n" + "puis de 0 à " + this.getPlateau().getLigne());

        }
        return coup;
    }
    
    private void afficherCoupsFin() {
        out.println("Coups du joueur 1 : ");
        for (String c1 : coupJouesJoueur1) {
            out.println(c1);
        }

        out.println("Coups du joueur 2 : ");
        for (String c2 : coupJouesJoueur2) {
            out.println(c2);
        }
    }

    public void jouerCoup(Case c, boolean estNoir, String coup) {
        if (c.positionDispo()) {
            c.setColor(estNoir);
        }
        else {
            c.setColor(!estNoir);
        }
        ajoutCoupPlat(coup);
    }

    public void ajoutCoupPlat(String coup) {
        char charCol = coup.charAt(0);
        int colToInt = Coordonnees.carColVersNum(charCol, p);

    }

    public Joueur getJoueur(boolean estNoir) {
        if (estNoir) {
            return j1;
        }
        else {
            return j2;
        }
    }

    public int getNbTours() {
        return nbTours;
    }
    
    public Plateau getPlateau() {
        return p;
    }

    public void run() {
        
    }

    private static String lireLigne() {
        return in.nextLine().trim();
    }
}
