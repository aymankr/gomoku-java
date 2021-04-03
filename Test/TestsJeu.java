
import Jeu.*;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.TreeMap;

/**
 * Tests unitaires des différentes fonctions utilisée lors du jeu.
 */
public class TestsJeu {

    private final boolean estNoir = true;
    private final int nbLg = 15;
    private final int nbCol = 15;
    private final int nbTours = 10;
    private final JoueurHumain j1 = new JoueurHumain("Robert", estNoir);
    private final JoueurHumain j2 = new JoueurHumain("Jean", !estNoir);
    private final Plateau plat = new Plateau(nbLg, nbCol);

    private final Partie p = new Partie(
            new JoueurHumain("Robert", estNoir),
            new JoueurHumain("Jean", !estNoir),
            nbLg,
            nbCol,
            nbTours
    );

    @Test
    public void testCoupValide() {

        // coups valides
        assertTrue(p.coupValide("A5"));
        assertTrue(p.coupValide("D11"));
        assertTrue(p.coupValide("A13"));
        assertTrue(p.coupValide("F9"));

        // coups invalides

        assertFalse(p.coupValide("A23"));
        //assertFalse(p.coupValide("T4"));
    }

    /* @Test
    public void testNomJoueurValide() {

        // noms valides
        assertTrue(Jeu.nomValide(j1.getNom()));
        assertTrue(Jeu.nomValide(j2.getNom()));
        assertTrue(Jeu.nomValide("a"));
        
        // noms invalides

        assertFalse(Jeu.nomValide("nomDu33"));
        assertFalse(Jeu.nomValide("Jean-Luc"));
        assertFalse(Jeu.nomValide(null));
    } */
    
    @Test
    public void testConversionCoup() {
        String[] coups = {"A2", "B0", "K8", "A0"};

        // A2
        assertTrue(plat.coupLigne(coups[0]) == 2);
        assertTrue(plat.coupCol(coups[0]) == 0);

        // B0
        assertTrue(plat.coupLigne(coups[1]) == 0);
        assertTrue(plat.coupCol(coups[1]) == 1);

        // K8
        assertTrue(plat.coupLigne(coups[2]) == 8);
        assertTrue(plat.coupCol(coups[2]) == 10);

        // A0
        /*  System.out.println(plat.coupCol(coups[4]));
       assertTrue(plat.coupLigne(coups[4]) == 0);
        assertTrue(plat.coupCol(coups[4]) == -1);*/
    }

    @Test
    public void testCoordVoisines() {
        Coordonnees c = new Coordonnees(0, 5);
        Coordonnees[] tab = c.coordCasesVois(plat);
        for (Coordonnees co : tab) {
            System.out.println(co.getLigne() + ", " + co.getCol());
        }
    }
}
