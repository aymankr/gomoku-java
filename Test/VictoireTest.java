

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
        plat.getCase(1, 1).setColor(estNoir);
        plat.getCase(0, 0).setColor(estNoir);
        plat.getCase(2, 2).setColor(estNoir);
        plat.getCase(3, 3).setColor(!estNoir);
        plat.getCase(4, 4).setColor(estNoir);

        boolean fini2 = plat.victoire();
        assertFalse(fini2);

        plat.getCase(3, 3).setColor(estNoir);
        fini2 = plat.victoire();
        assertTrue(fini2);

        // sur ligne
        plat.getCase(2, 3).setColor(estNoir);
        plat.getCase(2, 0).setColor(estNoir);
        plat.getCase(2, 1).setColor(estNoir);
        plat.getCase(2, 2).setColor(!estNoir);
        plat.getCase(2, 4).setColor(estNoir);

        boolean fini3 = plat.victoire();
        assertFalse(fini3);

        plat.getCase(2, 2).setColor(estNoir);
        fini3 = plat.victoire();
        assertTrue(fini3);
    }
}
