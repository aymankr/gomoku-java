package Tests;

import Jeu.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests unitaires des différentes fonctions utilisée lors du jeu.
 */
public class VictoireTest {

    final boolean estNoir = true;
    final int nbLg = 5;
    final int nbCol = 5;
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
    public void testVictoire() {

        // test alignement sur diagonale
        Case coupChoisi1 = plat.getCase(1, 1);

        Case c5 = plat.getCase(0, 0);
        Case c6 = plat.getCase(2, 2);
        Case c7 = plat.getCase(3, 3);
        Case c8 = plat.getCase(4, 4);

        coupChoisi1.setColor(estNoir);
        c5.setColor(estNoir);
        c6.setColor(estNoir);
        c7.setColor(!estNoir);
        c8.setColor(estNoir);

        boolean fini2 = plat.victoire();
        assertFalse(fini2);
        
        c7.setColor(estNoir);
        fini2 = plat.victoire();
        assertTrue(fini2);
        
         // sur ligne
        Case coupChoisi2 = plat.getCase(2, 3);

        Case c9 = plat.getCase(2, 0);
        Case c10 = plat.getCase(2, 1);
        Case c11 = plat.getCase(2, 2);
        Case c12 = plat.getCase(2, 4);

        coupChoisi2.setColor(estNoir);
        c9.setColor(estNoir);
        c10.setColor(estNoir);
        c11.setColor(!estNoir);
        c12.setColor(estNoir);

        boolean fini3 = plat.victoire();
        assertFalse(fini3);
        
        c11.setColor(estNoir);
        fini3 = plat.victoire();
        assertTrue(fini3);
    }
}
