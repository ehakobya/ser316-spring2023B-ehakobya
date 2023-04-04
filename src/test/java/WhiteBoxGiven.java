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

    /**
     * Method: equalDP()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: simple attack test with test of experience after attack<br>
     */
    @Test
    public void equalDP() {
        Character wiz1 = new Wizard();
        Character wiz2 = new Wizard();

        GamePlay game = new GamePlay(wiz1, wiz2);
        game.attack(wiz1, wiz2);

        assertEquals(wiz1.experience, 7);
        assertEquals(wiz2.experience, 7);
    }

    /**
     * Method: playAttackerSpeedLessThanOpponent()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: First character has lower speed than second one.<br>
     */
    @Test
    public void playAttackerSpeedLessThanOpponent() {
        Character c1 = new Barbarian();
        Character c2 = new Wizard();

        GamePlay game = new GamePlay(c2, c1);
        game.play();

        assertEquals(102, c1.health);
        assertEquals(100, c2.health);

        assertEquals(15, c1.experience);
        assertEquals(12, c2.experience);

        assertEquals(1, c1.level);
        assertEquals(2, c2.level);
    }

    /**
     * Method: playAttackerSpeedGreaterThanOpponent()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: First character has more speed than second one.<br>
     */
    @Test
    public void playAttackerSpeedGreaterThanOpponent() {
        Character c1 = new Barbarian();
        Character c2 = new Wizard();
        c2.health = 1;

        GamePlay game = new GamePlay(c1, c2);
        game.play();

        assertEquals(100, c1.health);
        assertEquals(0, c2.health);

        assertEquals(10, c1.experience);
        assertEquals(7,c2.experience);

        assertEquals(1, c1.level);
        assertEquals(1, c2.level);
    }

    /**
     * Method: playGainExperience()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: Tests character gaining experience.<br>
     */
    @Test
    public void playGainExperience(){
        Character c1 = new Barbarian();
        Character c2 = new Wizard();

        GamePlay game = new GamePlay(c2, c1);

        assertEquals(0, c1.experience);
        assertEquals(0, c2.experience);
        assertEquals(12, game.play());

        c1.experience = 0;
        c2.experience = 0;

        GamePlay game2 = new GamePlay(c1, c2);

        assertEquals(0, c1.experience);
        assertEquals(0, c2.experience);
        assertEquals(10, game2.play());
    }

    /**
     * Method: removeOpponent()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: Tests a character being removed when dead.<br>
     */
    @Test
    public void removeOpponent(){
        Character c1 = new Barbarian();
        Character c2 = new Wizard();

        GamePlay game = new GamePlay(c2, c1);

        assertEquals(1, game.Opponents.size());
        assertTrue(game.removeOpponent(c1));
        assertFalse(game.removeOpponent(c1));
        assertEquals(0, game.Opponents.size());
    }

    /**
     * Method: addedOpponent()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: Tests character being added to opponent list.<br>
     */
    @Test
    public void addedOpponent(){
        Character c1 = new Barbarian();
        Character c2 = new Wizard();

        GamePlay game = new GamePlay(c2, c1);
        assertEquals(1, game.Opponents.size());
        Character bar = new Barbarian();
        assertTrue(game.addOpponent(bar));
        assertEquals(2, game.Opponents.size());
    }

    /**
     * Method: testLevelUp()<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Description: Tests characters leveling up after attacking.<br>
     */
    @Test
    public void testLevelUp() {
        Character c1 = new Rogue();
        Character c2 = new Ranger();
        Character c3 = new Bard();
        Character c4 = new Druid();

        c1.experience = 19;
        c2.experience = 14;
        c3.experience = 9;
        c4.experience = 14;


        GamePlay game1 = new GamePlay(c1, c2);
        game1.attack(c1, c2);

        GamePlay game2 = new GamePlay(c3, c4);
        game2.attack(c3, c4);

        assertEquals(2, c1.level);
        assertEquals(2, c2.level);
        assertEquals(2, c3.level);
        assertEquals(2, c4.level);
    }
}