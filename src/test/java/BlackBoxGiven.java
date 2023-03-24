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
        assertEquals(60, b.health); // 100 - ((50 - 10))
        assertEquals(20, b.experience);

        // Boundary Value: 11 (one above lower boundary)
        b.health = 100;
        b.experience = 0; // reset experience
        game.takeDamage(b, 11);
        assertEquals(99, b.health); // 100 - ((11 - 10))
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
        b.health = 9;
        b.experience = 0; // reset experience
        game.takeDamage(b, 10); // take damage by boundary value
        assertTrue(0 < b.health);
        assertEquals(0, b.experience);

        // Boundary Value: 101 (one above lower boundary)
        b.health = 1;
        b.experience = 0; // reset experience
        game.takeDamage(b, 10);
        assertTrue(0 < b.health);
        assertEquals(0, b.experience);
    }

    // Half health characters, no level up
    // equivalence partition:   Character 1: Health > 10 ==> 50 Health
    //                          Character 2: Health > 10 ==> 60 Health
    @Test
    public void attackHalfHealthNoLevelUp() {

        Barbarian c1 = new Barbarian();
        Wizard c2 = new Wizard();

        c1.health = 60;
        c1.experience = 0;
        c2.health = 60;
        c2.experience = 0;

        // BOUNDARY 60 HEALTH /////////////////////
        // 1st Round //////////////////////////////
        // character 1 deals 10 damage to character 2
        game.dealDamage(c1);
        assertEquals(10, c1.experience);
        // c1.exp = 10 | c1.health = 60 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 0  | c2.health = 60 | c2.prot = 10 | c2.dmg = 10


        // character 2 takes damage (calculate)
        game.takeDamage(c2, game.dealDamage(c1));
        assertEquals(60, c2.health); // c2.prot - 10 loses protection
        assertEquals(0, c2.experience);
        // c1.exp = 10 | c1.health = 60 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 0 + 0  | c2.health = 60 | c2.prot = 10 - 10 = 0 | c2.dmg = 10


        // character 2 deals 10 damage to character 1
        game.dealDamage(c2);
        assertEquals(10, c2.experience); // c2.exp += 10
        // c1.exp = 10 | c1.health = 60 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 0 + 10  | c2.health = 60 | c2.prot = 0  | c2.dmg = 10


        // character 1 takes damage (calculate)
        game.takeDamage(c1, game.dealDamage(c2));
        assertEquals(60, c1.health); // loses protection
        assertEquals(0, c1.experience); // c2. exp = 10
        // c1.exp = 10 | c1.health = 60 | c1.prot = 10 - 10 = 0| c1.dmg = 10
        // c2.exp = 10 | c2.health = 60 | c2.prot = 0  | c2.dmg = 10


        // 2nd Round //////////////////////////////
        // character 1 deals 10 damage to character 2 AGAIN
        game.dealDamage(c1);
        assertEquals(10, c1.experience);
        // c1.exp = 10 + 10 | c1.health = 60 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 10      | c2.health = 60 | c2.prot = 0 | c2.dmg = 10


        // character 2 takes damage (calculate) AGAIN
        game.takeDamage(c2, game.dealDamage(c1));
        assertEquals(50, c2.health);
        assertEquals(15, c2.experience);
        // c1.exp = 20     | c1.health = 60           | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 10 + 5 | c2.health = 60 - 10 = 50 | c2.prot = 0 | c2.dmg = 10


        // character 2 deals 10 damage to character 1 AGAIN
        game.dealDamage(c2);
        assertEquals(25, c2.experience);
        // c1.exp = 20      | c1.health = 60 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 15 + 10 | c2.health = 50 | c2.prot = 0 | c2.dmg = 10


        // character 1 takes damage (calculate) AGAIN
        game.takeDamage(c1, game.dealDamage(c2));
        assertEquals(50, c1.health);
        assertEquals(25, c1.experience);
        // c1.exp = 20 + 5 = 25 | c1.health = 60 - 10 = 50 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 25          | c2.health = 50           | c2.prot = 0 | c2.dmg = 10


        // none level up
        assertFalse(game.levelUp(c1));
        assertFalse(game.levelUp(c2));



        // BOUNDARY 11 HEALTH /////////////////////
        c1.health = 11;
        c1.experience = 0;
        c2.health = 11;
        c2.experience = 0;
        // 1st Round //////////////////////////////
        // character 1 deals 10 damage to character 2
        game.dealDamage(c1);
        assertEquals(10, c1.experience);
        // c1.exp = 10 | c1.health = 11 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 0  | c2.health = 11 | c2.prot = 10 | c2.dmg = 10


        // character 2 takes damage (calculate)
        game.takeDamage(c2, game.dealDamage(c1));
        assertEquals(11, c2.health); // loses protection
        assertEquals(5, c2.experience);
        // c1.exp = 10 | c1.health = 11 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 5  | c2.health = 11 | c2.prot = 0  | c2.dmg = 10


        // character 2 deals 10 damage to character 1
        game.dealDamage(c2);
        assertEquals(15, c2.experience);
        // c1.exp = 10 | c1.health = 11 | c1.prot = 10 | c1.dmg = 10
        // c2.exp = 15 | c2.health = 11 | c2.prot = 0  | c2.dmg = 10


        // character 1 takes damage (calculate)
        game.takeDamage(c1, game.dealDamage(c2));
        assertEquals(11, c1.health); // loses protection
        assertEquals(15, c1.experience);
        // c1.exp = 15 | c1.health = 11 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 15 | c2.health = 11 | c2.prot = 0 | c2.dmg = 10


        // 2nd Round //////////////////////////////
        // character 1 deals 10 damage to character 2 AGAIN
        game.dealDamage(c1);
        assertEquals(25, c1.experience);
        // c1.exp = 25 | c1.health = 11 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 15 | c2.health = 11 | c2.prot = 0 | c2.dmg = 10


        // character 2 takes damage (calculate) AGAIN
        game.takeDamage(c2, game.dealDamage(c1));
        assertEquals(1, c2.health);
        assertEquals(20, c2.experience);
        // c1.exp = 25 | c1.health = 11 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 20 | c2.health = 1  | c2.prot = 0 | c2.dmg = 10


        // character 2 deals 10 damage to character 1 AGAIN
        game.dealDamage(c2);
        assertEquals(10, c2.experience);
        // c1.exp = 25 | c1.health = 11 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 30 | c2.health = 1  | c2.prot = 0 | c2.dmg = 10


        // character 1 takes damage (calculate) AGAIN
        game.takeDamage(c1, game.dealDamage(c2));
        assertEquals(1, c1.health);
        assertEquals(30, c1.experience);
        // c1.exp = 30 | c1.health = 1 | c1.prot = 0 | c1.dmg = 10
        // c2.exp = 30 | c2.health = 1 | c2.prot = 0 | c2.dmg = 10

        // none level up
        assertFalse(game.levelUp(c1));
        assertFalse(game.levelUp(c2));
    }
}




//                b.health = 100;
//                b.experience = 0; // reset experience
//                game.takeDamage(b, 50); // take damage by boundary value
//                assertEquals(60, b.health); // 100 - ((50 - 10)
//                assertEquals(20, b.experience);

// Boundary Value: 2 (lower bound - extreme case)
//        b.health = 99; // set health to boundary value
//        b.experience = 0; // reset experience
//        game.dealDamage(b);
//        assertEquals(99, b.health);
//        assertEquals(10, b.experience);
//        assertEquals( 10, game.dealDamage(b));
//
//                b.health = 100;
//                b.experience = 0; // reset experience
//                game.takeDamage(b, 10);
//                assertEquals(100, b.health);
//                assertEquals(0, b.experience);


