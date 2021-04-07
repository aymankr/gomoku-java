/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void testCoupJouable() {
        boolean estIA = true;
        ArrayList<String> coupsJoues = new ArrayList<>();

        j1.jouer("B1", coupsJoues, plat, p, estNoir, !estIA);
        j2.jouer("A1", coupsJoues, plat, p, !estNoir, estIA);
        j1.jouer("A2", coupsJoues, plat, p, estNoir, !estIA);
        j2.jouer("B0", coupsJoues, plat, p, !estNoir, estIA);

        assertTrue(plat.getCase(0, 0).estJouable());
        assertTrue(plat.getCase(1, 2).estJouable());
        assertTrue(plat.getCase(2, 1).estJouable());
        assertFalse(plat.getCase(2, 3).estJouable());

    }

}
