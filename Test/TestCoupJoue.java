
import Jeu.JoueurHumain;
import Jeu.Match;
import Jeu.Plateau;
import Jeu.Partie;

/**
 *
 * @author A
 */
public class TestCoupJoue {

    public void coupCorrect() {
        boolean estNoir = true;

        JoueurHumain j1 = new JoueurHumain("joueurA", estNoir);
        JoueurHumain j2 = new JoueurHumain("joueurB", !estNoir);
        Partie partie = new Partie(j1, j2, 15, 15, 10);

        String coup1 = "A2";
        String coupTest = partie.coupChoisi("");

        assertEquals(coup1.equals(coupTest));
    }
}
