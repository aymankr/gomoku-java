

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author A
 */
public class CoordonneesTest {

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
    public void testVoisines() {

        Coordonnees coupChoisi = new Coordonnees(0, 0);

        Coordonnees[] cVoisins1 = {
            new Coordonnees(1, 0),
            new Coordonnees(0, 1),
            new Coordonnees(1, 1)};

        Coordonnees[] tabV1 = coupChoisi.voisines(plat);
        int i1 = 0;

        for (Coordonnees coord : tabV1) {
            assertTrue(coord.getLigne() == cVoisins1[i1].getLigne());
            assertTrue(coord.getCol() == cVoisins1[i1].getCol());
            i1++;
        }
    }

    @Test
    public void testVoisinesComplementaires() {
        // indice 0 : N-S, 1 : E-O, 2 : NE-SO, 3 : NO-SE
        Direction[][] toutes = Direction.toutesComplementaires();

        Coordonnees coupChoisi = new Coordonnees(0, 0);

        // alignement haut-bas
        Coordonnees[] cVoisins1 = {
            new Coordonnees(1, 0),
            new Coordonnees(2, 0),
            new Coordonnees(3, 0),
            new Coordonnees(4, 0),};

        Coordonnees[] tabV1 = coupChoisi.voisinesComplementaires(plat, toutes[0]);
        int i1 = 0;

        for (Coordonnees coord : tabV1) {
            assertTrue(coord.getLigne() == cVoisins1[i1].getLigne());
            assertTrue(coord.getCol() == cVoisins1[i1].getCol());
            i1++;
        }

        // alignement gauche-droite
        Coordonnees[] cVoisins2 = {
            new Coordonnees(0, 1),
            new Coordonnees(0, 2),
            new Coordonnees(0, 3),
            new Coordonnees(0, 4),};

        Coordonnees[] tabV2 = coupChoisi.voisinesComplementaires(plat, toutes[1]);
        int i2 = 0;

        for (Coordonnees coord : tabV2) {
            assertTrue(coord.getLigne() == cVoisins2[i2].getLigne());
            assertTrue(coord.getCol() == cVoisins2[i2].getCol());
            i2++;
        }

        // alignement diagonales bas gauche - haut droit
        Coordonnees[] cVoisins3 = {
            new Coordonnees(1, 1),
            new Coordonnees(2, 2),
            new Coordonnees(3, 3),
            new Coordonnees(4, 4)};

        Coordonnees[] tabV3 = coupChoisi.voisinesComplementaires(plat, toutes[2]);
        int i3 = 0;

        for (Coordonnees coord : tabV3) {
            assertTrue(coord.getLigne() == cVoisins2[i3].getLigne());
            assertTrue(coord.getCol() == cVoisins2[i3].getCol());
            i3++;
        }

        // autre coup
        Coordonnees coupChoisi2 = new Coordonnees(4, 4);

        Coordonnees[] cVoisins4 = {
            new Coordonnees(3, 3),
            new Coordonnees(2, 2),
            new Coordonnees(1, 1),
            new Coordonnees(0, 0)};

        // alignement haut gauche - bas droit
        Coordonnees[] tabV4 = coupChoisi2.voisinesComplementaires(plat, toutes[3]);

        int i4 = 0;

        for (Coordonnees coord : tabV4) {
            assertTrue(coord.getLigne() == cVoisins4[i4].getLigne());
            assertTrue(coord.getCol() == cVoisins4[i4].getCol());
            i4++;
        }

    }

    @Test
    public void testDansPlateau() {

        assertTrue((new Coordonnees(1, 0).estDansPlateau(plat)));
        assertTrue((new Coordonnees(4, 4).estDansPlateau(plat)));
        assertFalse((new Coordonnees(4, 5).estDansPlateau(plat)));
        assertFalse((new Coordonnees(-1, 2).estDansPlateau(plat)));
    }

    @Test
    public void testCharVersNum() {

        assertTrue(Coordonnees.carColVersNum('A') == 0);
        assertTrue(Coordonnees.carColVersNum('B') == 1);
        assertTrue(Coordonnees.carColVersNum('K') == 10);
        assertTrue(Coordonnees.carColVersNum('Z') == 25);
    }

    @Test
    public void testNumVersChar() {

        assertTrue(Coordonnees.numVersCarCol(0) == 'A');
        assertTrue(Coordonnees.numVersCarCol(25) == 'Z');
        assertTrue(Coordonnees.numVersCarCol(10) == 'K');
    }

    @Test
    public void testCoordEnString() {
        Coordonnees c1 = new Coordonnees(0, 0);
        Coordonnees c2 = new Coordonnees(4, 0);
        Coordonnees c3 = new Coordonnees(2, 3);

        assertTrue(c1.coordEnString().equals("A0"));
        assertTrue(c2.coordEnString().equals("A4"));
        assertTrue(c3.coordEnString().equals("D2"));
    }
}
