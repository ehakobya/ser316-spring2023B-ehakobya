import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<GamePlay> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<GamePlay>) classUnderTest;
    }

    // Define all classes to be tested
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {GamePlay1.class},
            {GamePlay2.class},
            {GamePlay3.class},
            {GamePlay4.class},
            {GamePlay5.class}
        };
        return Arrays.asList(classes);
    }

    private GamePlay createGame() throws Exception {
        Constructor<GamePlay> constructor = classUnderTest.getConstructor();
        return constructor.newInstance();
    }

    GamePlay game;

    @org.junit.Before
    public void setUp() throws Exception {
        game = createGame();
    }


    // normal experience when healthy
    // equivalence partition 10 <= health <= 100
    @Test
    public void dealtDamageNormalExperience() {

        Barbarian b = new Barbarian();

        // Boundary Value: 100 (upper boundary)
        game.dealDamage(b); // deal some damage
        assertEquals(0, b.experience); // make sure experience is at 0
        assertEquals(100, b.health);
        assertEquals(10, b.experience);
        assertEquals(10, game.dealDamage(b));

        // Boundary Value: 99 (one below upper boundary)
        b.health = 99; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(99, b.health);
        assertEquals(10, b.experience);
        assertEquals( 10, game.dealDamage(b));

        // Boundary Value: 50 (middle)
        b.health = 50; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(50, b.health);
        assertEquals(10, b.experience);
        assertEquals( 10, game.dealDamage(b));

        // Boundary Value: 11 (one above lower boundary)
        b.health = 11; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(11, b.health);
        assertEquals(10, b.experience);
        assertEquals( 10, game.dealDamage(b));

        // Boundary Value: 10 (lower boundary)
        b.health = 10; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(10, b.health);
        assertEquals(10, b.experience);
        assertEquals( 10, game.dealDamage(b));
    }

    // double damage when health below 10
    // equivalence partition 1 <= health < 10
    @Test
    public void dealtDamageNormalExperienceDoubleDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary)
        b.health = 9; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(9, b.health);
        assertEquals(10, b.experience);
        assertEquals( 20, game.dealDamage(b));

        // Boundary Value: 5 (middle)
        b.health = 5; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(5, b.health);
        assertEquals(10, b.experience);
        assertEquals( 20, game.dealDamage(b));

        // Boundary Value: 2 (one above lower boundary)
        b.health = 2; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(2, b.health);
        assertEquals(10, b.experience);
        assertEquals( 20, game.dealDamage(b));

        // Boundary Value: 1 (lower boundary)
        b.health = 1; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(1, b.health);
        assertEquals(10, b.experience);
        assertEquals( 20, game.dealDamage(b));
    }

    // double damage when health below 10
    // equivalence partition 1 <= health < 10
    @Test
    public void dealtDamageNoNormalExperienceNoDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary)
        b.health = 0; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(0, b.health);
        assertEquals(0, b.experience);
        assertEquals( 0, game.dealDamage(b));

        // Boundary Value: 5 (one below upper bound)
        b.health = -1; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(-1, b.health);
        assertEquals(0, b.experience);
        assertEquals( 0, game.dealDamage(b));

        // Boundary Value: 2 (lower bound - extreme case)
        b.health = -10; // set health to boundary value
        b.experience = 0; // reset experience
        game.dealDamage(b);
        assertEquals(-10, b.health);
        assertEquals(0, b.experience);
        assertEquals( 0, game.dealDamage(b));
    }

    // take damage when protection > damage
    // equivalence partition 1 <= Damage < Protection(10)
    @Test
    public void takenDamageProtectionGreaterThanDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 9 (upper boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 9); // take damage by boundary value
        assertEquals(100, b.health);
        assertEquals(1, b.experience);

        // Boundary Value: 5 (middle)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 5);
        assertEquals(102, b.health);
        assertEquals(5, b.experience);

        // Boundary Value: 5 (one above lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 2);
        assertEquals(104, b.health);
        assertEquals(8, b.experience);

        // Boundary Value: 1 (lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 1);
        assertEquals(104, b.health);
        assertEquals(9, b.experience);
    }

    // take damage when protection <= damage
    // equivalence partition protection(10) <= 10
    @Test
    public void takenDamageProtectionLessThanDamage() {

        Barbarian b = new Barbarian();

        // Boundary Value: 50 (upper boundary - extreme case)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 50); // take damage by boundary value
        assertEquals(60, b.health); // 100 - ((50 - 10) / 2)
        assertEquals(20, b.experience);

        // Boundary Value: 11 (one above lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 11);
        assertEquals(99, b.health); // 100 - ((11 - 10) / 2)
        assertEquals(0, b.experience);

        // Boundary Value: 10 (lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 10);
        assertEquals(100, b.health);
        assertEquals(0, b.experience);
    }

    // health below zero case
    // equivalence partition: Protection <= Health <= Damage
    @Test
    public void takenDamageHealthBelowZero() {

        Barbarian b = new Barbarian();

        // Boundary Value: 200 (upper boundary - extreme case)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 200); // take damage by boundary value
        assertTrue(0 < b.health);
        assertEquals(0, b.experience);

        // Boundary Value: 101 (one above lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 101);
        assertTrue(0 < b.health);
        assertEquals(0, b.experience);

        // Boundary Value: 100 (lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 100);
        assertTrue(0 < b.health);
        assertEquals(0, b.experience);
    }
}