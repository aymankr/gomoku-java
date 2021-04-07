
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author A
 */
public class Partie {

    private static Scanner in = new Scanner(System.in);
    private static PrintStream out = System.out;
    private static boolean finiParInterruption;
    private ArrayList<String> coupsJoues;
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
        this.finiParInterruption = false;
    }

    /**
     * Gestion d'une partie tour à tour ajout des coups joués dans l'affichage,
     * lorsque la partie est terminée afficher l'historique des coups
     * 
     */
    public void gererPartie() {
        boolean premierCoup = true;
        boolean estNoir = true;
        boolean finiParVictoire = false;
        int nbT = nbTours;
        out.println("La partie dure " + nbT + " tours, à vous de jouer !" + "\n");

        while (!finiParInterruption && !finiParVictoire && nbT > 0) {
            int tour = nbTours - nbT + 1;

            plat.affiche();
            out.println("\n" + "Tour " + tour + "\n");
            if (nbT % 2 == 0) {

                finiParVictoire = tourJoueur(j1, premierCoup, estNoir, j1.estIA);

            } else if (nbT % 2 == 1) {

                finiParVictoire = tourJoueur(j2, premierCoup, !estNoir, j2.estIA);
            }
            premierCoup = false;

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
     * Jouer le tour du joueur et renvoyer vrai s'il y a victoire après le coup joué
     * 
     * @param j le joueur
     * @param premCoup vrai ssi premier coup
     * @param estNoir vrai ssi noir
     * @param estIA vrai ssi c'est une IA
     * @return retourner vrai s'il y a victoire
     */
    private boolean tourJoueur(Joueur j, boolean premCoup, boolean estNoir, boolean estIA) {
        String coupJ;
        if (!estIA) {
            coupJ = demandeCoup(estIA, j, premCoup);
            if (coupJ.equals("q")) {
                finiParInterruption = true;
                coupJ = "A0";
            } else {
                j.jouer(coupJ, coupsJoues, plat, this, estNoir, estIA);
            }
        } else {
            j.jouer(" ", coupsJoues, plat, this, estNoir, estIA);
            coupJ = coupsJoues.get(coupsJoues.size() - 1);
        }
        return plat.victoire(coupJ);
    }

    /**
     * Renvoyer le coup choisi par un joueur
     * 
     * @param s le coup
     * @return retourner ce coup
     */
    private String coupChoisi(String s, boolean premierCoup, boolean estIA) {

        out.println(s);
        out.print("--> ");
        String coup = lireLigne();

        String msgCoupInterdit = "Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                + (char) (65 + this.plat.getNbColonnes()) + ", puis de 0 à "
                + (this.plat.getNbLignes() + "\n" + "Ecrivez sous la forme : A1");

        try {

            if (coup.equals("q")) {
                out.println("\n" + "Partie interrompue.");
            } else if (!coupValide(coup, premierCoup, estIA)) {
                coup = coupChoisi(s, premierCoup, estIA);
            }
        } catch (Exception e) {
            out.println(msgCoupInterdit);
            coup = coupChoisi(s, premierCoup, estIA);
        }

        return coup;
    }

    /**
     * Demander un coup pour les joueurs humains
     * 
     * @param estIA vrai ssi c'est une IA
     * @param j le joueur 
     * @param premierCoup vrai ssi c'est le premier coup
     * @return retourner le coup joué
     */
    private String demandeCoup(boolean estIA, Joueur j, boolean premierCoup) {
        String coup = "";

        if (!estIA) {
            coup = coupChoisi("Coup de " + j.getNom() + " :", premierCoup, estIA);
        }
        return coup;
    }

    /**
     * Vérifier si un coup est valide
     * 
     * @param coup le coup
     * @return retourner vrai s'il est valide
     */
    public boolean coupValide(String coup, boolean premierCoup, boolean estIA) {
        boolean b;
        Coordonnees coord = null;

        boolean chaineValide = (coup != null && coup.length() == 2 || coup.length() == 3);
        if (chaineValide) {
            coord = Coordonnees.convertCoord(coup);
        }

        b = chaineValide && coord.estDansPlateau(plat);

        if (!b) {
            out.println("Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                    + (char) (65 + this.plat.getNbColonnes()) + ", puis de 0 à "
                    + (this.plat.getNbLignes() + "\n" + "Ecrivez sous la forme : A1"));
        } else {
            for (String cp : coupsJoues) {
                if (cp.contains(coup)) {
                    b = false;
                }
            }
            if (!b && !estIA) {
                out.println(coup + " a déjà été joué, réessayez.");
            } else if (!plat.getCase(coord.getLigne(), coord.getCol()).estJouable() && !premierCoup) {
                b = false;
                out.println(coup + " n'est pas jouable, il faut jouer à côté d’une case déjà occupée.");

            }
        }
        return b;
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
        plat.affiche();
    }

    /**
     * Lire l'entrée de l'utilisateur
     * 
     * @return retourner la réponse
     */
    private static String lireLigne() {
        String s = in.nextLine().trim();

        return s;
    }
}
