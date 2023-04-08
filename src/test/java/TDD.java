/**
 * File: TDD.java<br>
 * Author: Edgar Hakobyan<br>
 * Date: 04/03/2023<br>
 * Description: Test driven development<br>
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Class: TDD.<br>
 * Description: Test driven development class.
 */
public class TDD {

    GamePlay game;

    @org.junit.Before
    public void setUp() {
        game = new GamePlay();
    }

    /**
     * Method: dealtDamageNormalExperience().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: 10 <= health <= 100<br>
     */
    @Test
    public void dealtDamageNormalExperience() {

        Barbarian b = new Barbarian();

        // Boundary Value: 100 (upper boundary)
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));

        // Boundary Value: 10 (lower boundary)
        b.health = 10;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));

        // Boundary Value: 11 (one above lower boundary)
        b.health = 11;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));

        // Boundary Value: 50 (middle)
        b.health = 50;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));

        // Boundary Value: 99 (one below upper boundary)
        b.health = 99;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));
    }

    /**
     * Method: dealtDamageNormalExperienceDoubleDamage().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: 1 <= health < 10<br>
     */
    @Test
    public void dealtDamageNormalExperienceDoubleDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary)
        b.health = 9;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(20, game.dealDamage(b));

        // Boundary Value: 5 (middle)
        b.health = 5;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(20, game.dealDamage(b));

        // Boundary Value: 2 (one above lower boundary)
        b.health = 2;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(20, game.dealDamage(b));

        // Boundary Value: 1 (lower boundary)
        b.health = 1;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(10, b.experience);
        assertEquals(20, game.dealDamage(b));
    }


    /**
     * Method: dealtDamageNoExperienceNoDamage().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: health <= 0<br>
     */
    @Test
    public void dealtDamageNoExperienceNoDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: -10 (lower bound - extreme case)
        b.health = -10;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(0, b.experience);
        assertEquals(0, game.dealDamage(b));

        // Boundary Value: -1 (one below upper bound)
        b.health = -1;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(0, b.experience);
        assertEquals(0, game.dealDamage(b));

        // Boundary Value: 0 (upper boundary)
        b.health = 0;
        b.experience = 0;
        game.dealDamage(b);
        assertEquals(0, b.experience);
        assertEquals(0, game.dealDamage(b));
    }


    /**
     * Method: takenDamageProtectionGreaterThanDamage().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Protection > Damage<br>
     */
    @Test
    public void takenDamageProtectionGreaterThanDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary)
        game.takeDamage(b, 9);
        assertEquals(100, b.health);
        assertEquals(1, b.experience);

        // Boundary Value: 5 (middle)
        b.health = 100;
        b.experience = 0;
        game.takeDamage(b, 5);
        assertEquals(102, b.health);
        assertEquals(5, b.experience);

        // Boundary Value: 2 (one above lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 2);
        assertEquals(104, b.health);
        assertEquals(8, b.experience);

        // Boundary Value: 1 (lower boundary)
        b.health = 100;
        b.experience = 0;
        game.takeDamage(b, 1);
        assertEquals(104, b.health);
        assertEquals(9, b.experience);
    }

    /**
     * Method: takenDamageProtectionLessThanDamage().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Protection <= Damage<br>
     */
    @Test
    public void takenDamageProtectionLessThanDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: Protection == Damage (upper boundary)
        game.takeDamage(b, 10);
        assertEquals(100, b.health);
        assertEquals(0, b.experience);

        // Boundary Value: Protection < Damage (one lower than upper boundary)
        b.health = 100;
        b.experience = 0;
        game.takeDamage(b, 11);
        assertEquals(99, b.health);
        assertEquals(0, b.experience);

        // Boundary Value: Protection < Damage (lower boundary)
        b.health = 100;
        b.experience = 0;
        game.takeDamage(b, 20);
        assertEquals(90, b.health);
        assertEquals(5, b.experience);
    }

    /**
     * Method: takenDamageHealthBelowZero().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Health <= Protection <= Damage<br>
     */
    @Test
    public void takenDamageHealthBelowZero() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary - extreme case)
        b.health = 1;
        b.experience = 0;
        game.takeDamage(b, 15);
        assertEquals(0, b.health);
    }

    /**
     * Method: attackNormalHealth().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Both characters normal health<br>
     */
    @Test
    public void attackNormalHealth() {

        Barbarian c1 = new Barbarian();
        Wizard c2 = new Wizard();

        game.attack(c1, c2); // c1 attacks c2
        assertEquals(102, c1.health);
        assertEquals(91, c2.health);
    }

    /**
     * Method: attackAttackerFullOpponentZeroHealth().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Attacker HP == 100 && Opponent HP == 0<br>
     */
    @Test
    public void attackAttackerFullOpponentZeroHealth() {

        Barbarian c1 = new Barbarian();
        Wizard c2 = new Wizard();

        c1.health = 100;
        c1.damage = 10;
        c2.health = 0;
        c2.damage = 10;
        game.attack(c1, c2); // c1 attacks c2
        assertEquals(100, c1.health);
        assertEquals(0, c2.health);
        assertEquals(0, c1.experience);
        assertEquals(0, c2.experience);
    }

    /**
     * Method: attackZeroHealth().<br>
     * Type: Test method<br>
     * Inputs: None<br>
     * Returns: Void<br>
     * Equivalence Partition: Both characters at 0 health<br>
     */
    @Test
    public void attackZeroHealth() {

        Barbarian c1 = new Barbarian();
        Wizard c2 = new Wizard();

        // Boundary Value: 11, 11 (lower boundary)
        c1.health = 0;
        c1.damage = 10;
        c2.health = 0;
        c2.damage = 10;
        game.attack(c1, c2); // c1 attacks c2
        assertEquals(0, c1.experience);
        assertEquals(0, c2.experience);
        assertEquals(1, c2.protection);
    }
}
