
import Jeu.CoupChoisiException;
import Jeu.JoueurHumain;
import Jeu.Match;
import Jeu.Plateau;
import Jeu.Partie;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author A
 */
public class TestCoupJoue {


    
    @Test
    public void coupCorrect() throws CoupChoisiException {
        boolean estNoir = true;
        
        JoueurHumain j1 = new JoueurHumain("joueurA", estNoir);
        JoueurHumain j2 = new JoueurHumain("joueurB", !estNoir);
        Plateau p = new Plateau(10,10);
        Match m = new Match(20, j1, j2);
        Partie partie = new Partie(j1, j2, m);
        
        String coup1 = "A2";
        //String coupTest = partie.coupChoisi(coup1);
        String coup2 = "B0";
        String coup3 = "a5";
        String coup4 = "H9";
        
        //assertTrue(coup1.equals(partie.coupChoisi(coup1)));
    }
}
