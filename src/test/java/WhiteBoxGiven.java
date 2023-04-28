import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxGiven {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    // simple attack test with test of experience after attack
    @Test
    public void equalDP() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.attack(wiz1, wiz2);

        assertEquals(wiz1.experience, 0);
        assertEquals(wiz2.experience, 0);
    }

}