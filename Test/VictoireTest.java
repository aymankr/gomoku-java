
import Jeu.Partie;
import Joueurs.JoueurHumain;
import Positions.Plateau;
import java.util.ArrayList;
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
        boolean estIA = true;
        ArrayList<String> coupsJoues = new ArrayList<>();

        // test alignement sur diagonale
        j1.jouer("A0", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("B1", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("C2", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("D3", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("E4", coupsJoues, plat, p, estNoir, !estIA);

        boolean fini2 = plat.victoire("E4");
        assertTrue(fini2);

        plat = new Plateau(10, 10);

        // sur ligne
        j1.jouer("A0", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A1", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A2", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A3", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A4", coupsJoues, plat, p, estNoir, !estIA);

        boolean fini3 = plat.victoire("A4");
        assertTrue(fini3);

        plat = new Plateau(10, 10);
        // pas de victoire
        j1.jouer("A0", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A1", coupsJoues, plat, p, estNoir, !estIA);
        j2.jouer("A2", coupsJoues, plat, p, !estNoir, !estIA);
        j1.jouer("A3", coupsJoues, plat, p, estNoir, !estIA);
        j1.jouer("A4", coupsJoues, plat, p, estNoir, !estIA);

        boolean fini4 = plat.victoire("A4");
        assertFalse(fini4);
    }
}
