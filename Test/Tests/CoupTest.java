/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Jeu.Case;
import Jeu.Coordonnees;
import Jeu.JoueurHumain;
import Jeu.Partie;
import Jeu.Plateau;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author A
 */
public class CoupTest {

    final boolean estNoir = true;
    final int nbLg = 15;
    final int nbCol = 15;
    final int nbTours = 10;
    final JoueurHumain j1 = new JoueurHumain("Robert", estNoir);
    final JoueurHumain j2 = new JoueurHumain("Jean", !estNoir);
    Plateau plat = new Plateau(nbLg, nbCol);

    final Partie p = new Partie(
            j1,
            j2,
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
        assertFalse(p.coupValide("C16"));
        //assertFalse(p.coupValide("T4")); // envoi un message d'exception
    }

    @Test
    public void testConversionCoup() {
        String[] coups = {"A2", "B0", "K8", "A0"};

        // A2
        assertTrue(plat.coupCol(coups[0]) == 0);
        assertTrue(plat.coupLigne(coups[0]) == 2);

        // B0
        assertTrue(plat.coupCol(coups[1]) == 1);
        assertTrue(plat.coupLigne(coups[1]) == 0);

        // K8
        assertTrue(plat.coupCol(coups[2]) == 10);
        assertTrue(plat.coupLigne(coups[2]) == 8);

        // A0
        assertTrue(plat.coupLigne(coups[3]) == 0);
        assertTrue(plat.coupCol(coups[3]) == 0);
    }

    @Test
    public void testCoupJouable() {
        ArrayList<String> coupsJoues = new ArrayList<>();

        j1.jouer("B1", coupsJoues, plat, p, estNoir);
        j2.jouer("A1", coupsJoues, plat, p, !estNoir);
        j1.jouer("A2", coupsJoues, plat, p, estNoir);
        j2.jouer("B0", coupsJoues, plat, p, !estNoir);

        assertTrue(plat.getCase(0, 0).estJouable());
        assertTrue(plat.getCase(1, 2).estJouable());
        assertTrue(plat.getCase(2, 1).estJouable());
        assertFalse(plat.getCase(2, 3).estJouable());
    }

    /*@Test
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
}
