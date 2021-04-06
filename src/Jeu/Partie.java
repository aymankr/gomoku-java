
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

            plat.display();
            out.println("\n" + "Tour " + tour + "\n");
            if (nbT % 2 == 0) {

                /*String coupJ1 = demandeCoup(j1.estUneIA(), j1, premierCoup);
                j1.jouer(coupJ1, coupsJoues, plat, this, estNoir);
                finiParVictoire = plat.victoire();
                premierCoup = false;**/
                finiParVictoire = tourJoueur(j1,premierCoup,estNoir);

            } else if (nbT % 2 == 1) {

                finiParVictoire = tourJoueur(j2,premierCoup,!estNoir);
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
     * Le tour
     */
    private boolean tourJoueur(Joueur j, boolean premCoup, boolean estNoir){
        
       
        String coupJ = demandeCoup(j.estUneIA(), j, premCoup);
        if(coupJ.equals("q")){
            finiParInterruption=true;
        }
        else{
        j.jouer(coupJ, coupsJoues, plat, this, estNoir);
        }
        return plat.victoire();
        
    }

    /**
     * Renvoyer le coup choisi par un joueur
     * 
     * @param s le coup
     * @return retourner ce coup
     */
    private String coupChoisi(String s, boolean premierCoup) {

        out.println(s);
        out.print("--> ");
        String coup = lireLigne();

        String msgCoupInterdit = "Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
                + (char) (65 + this.plat.getNbColonnes()) + ", puis de 0 à " + (this.plat.getNbLignes() + "\n" + "Ecrivez sous la forme : A1");

        try {

            if (coup.equals("q")){
                out.println("Quitter");
            }
            else if (!coupValide(coup, premierCoup)) {
                coup = coupChoisi(s, premierCoup);
            }
        } catch (Exception e) {
            out.println(msgCoupInterdit);
            coup = coupChoisi(s, premierCoup);
        }

        return coup;
    }


    private String demandeCoup(boolean estIA, Joueur j, boolean premierCoup) {
        String coup = "";

        if (!estIA) {
            coup = coupChoisi("Coup de " + j.getNom() + " :", premierCoup);
        }
        return coup;
    }

    

    /**
     * Vérifier si un coup est valide
     * 
     * @param coup le coup
     * @return retourner vrai s'il est valide
     */
    public boolean coupValide(String coup, boolean premierCoup) {
        boolean b;

        int lig = 0, col = 0;
        boolean chaineValide = (coup != null && coup.length() == 2 || coup.length() == 3);
        if (chaineValide) {
            lig = plat.coupLigne(coup);
            col = plat.coupCol(coup);
        }

        b = chaineValide && lig >= 0 && lig <= plat.getNbLignes() && col >= 0 && col <= plat.getNbColonnes();

<<<<<<< HEAD
        if (!b){
            out.println("Coup choisi inconnu : " + coup + ", les coups autorisés sont de A à "
        + (char) (65 + this.plat.getNbColonnes()) + ", puis de 0 à " + (this.plat.getNbLignes() + "\n" + "Ecrivez sous la forme : A1"));
        }
        else{
            for (String cp : coupsJoues) {
                if (cp.contains(coup)) {
                    b = false;
                }
            }
            if (!b){
                out.println(coup + " a déjà été joué, réessayez.");
            }
            else{
                Case c = plat.getCase(lig, col);
                if (!c.estJouable() && !premierCoup){
                    b = false;
                    out.println(coup + " n'est pas jouable, il faut jouer à côté d’une case déjà occupée.");
=======
    /**
     * Vérifier si un coup est disponible
     * 
     * @param coup le coup
     * @return retourner vrai ssi disponible
     */
    public boolean coupDispo(String coup) {
        boolean dispo = true;
>>>>>>> e50556cc9d05274efd4c030f231c2fe373f7fccc

                }
            }
        }
        return b;
    }

<<<<<<< HEAD




=======
    /**
     * Vérifier si la case du coup est jouable
     * 
     * @param coup le coup
     * @return retourner vrai ssi jouable
     */
    public boolean coupJouable(String coup) {
        int lig = plat.coupLigne(coup);
        int col = plat.coupCol(coup);
        Case c = plat.getCase(lig, col);
        return c.estJouable();
    }
    
>>>>>>> e50556cc9d05274efd4c030f231c2fe373f7fccc
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
        String s = in.nextLine().trim();
        
        return s;
    }
}
