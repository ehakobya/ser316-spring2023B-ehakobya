/**
File: GamePlay.java
Author: Dr. Mehlhase
Date: unkown
Description: provides gameplay logic for characters to attack each other.
*/
import java.util.*;

/**
 Class: GamePlay
 Description: provides gameplay logic for characters to attack each other.
 */
public class GamePlay implements GamePlayInterface {

    public Character player;
    public List<Character> opponents;

    //SER316 TASK 2 SPOTBUGS FIX
//    public List<Character> remove;

    /**
     * Default constructor for Game Play.
     */
    public GamePlay() {
        this(new Barbarian());
    }

    /**
     * Parameterized constructor for Game Play.
     *
     * @param character type for player to use
     */
    public GamePlay(Character character) {
        this.player = character;
        this.opponents = new LinkedList<>();
        addOpponent(new Wizard());
        this.opponents.add(new Bard());
        this.opponents.add(new Druid());
        this.opponents.add(new Rogue());
        this.opponents.add(new Ranger());
        this.opponents.add(new Barbarian());
    }

    /**
     * Parameterized constructor for Game Play.
     * Allows choice of opponent.
     *
     * @param character type for player to use
     * @param opponent  type for opponent
     */
    public GamePlay(Character character, Character opponent) {
        this.player = character;
        this.opponents = new LinkedList<>();
        this.opponents.add(opponent);
    }

    @Override
    public boolean addOpponent(Character opponent) {
        this.opponents.add(opponent);
        return true;
    }

    @Override
    public boolean removeOpponent(Character opponent) {
        return this.opponents.remove(opponent);
    }

    @Override
    public int dealDamage(Character character) {
        if (character.health > 0) {
            character.experience += character.damage;
            if (character.health < 10) {
                return 2 * character.damage;
            } else {
                return character.damage;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int takeDamage(Character character, int blowDamage) {
        int damageTaken = blowDamage - character.protection;
        if (damageTaken < 0) {
            character.experience = character.experience - damageTaken;
            character.health = character.health - damageTaken / 2;
        } else {
            character.experience = character.experience + damageTaken / 2;
            character.health = character.health - damageTaken;
        }
        if (character.health < 0) {
            character.health = 0;
        }
        return damageTaken;
    }

    @Override
    public boolean levelUp(Character character) {
        if (character.experience >= character.pointsPerLevel) {
            if (character.experience == character.pointsPerLevel) {
                character.experience += 5;
            }

            character.level++;
            character.pointsPerLevel *= 2; // need more points to level up next time
            character.health = 100; // level up resets health

            //SER316 TASK 2 SPOTBUGS FIX
            if (character.getClass().getName().equals(Barbarian.class.getName())) {
                character.damage += 10;
                character.speed = character.speed + 0.25;
                character.protection += 2;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else if (character.getClass().getName().equals(Bard.class.getName())) {
                character.damage += character.damage / 2;
                character.speed += 0.5;
                character.protection += character.protection / 2;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else if (character.getClass().getName().equals(Druid.class.getName())) {
                character.damage += 10;
                character.speed += 0.25;
                character.protection += 2;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else if (character.getClass().getName().equals(Ranger.class.getName())) {
                character.damage += character.damage % 10;
                character.speed += 0.5;
                character.protection += character.protection % 5;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else if (character.getClass().getName().equals(Rogue.class.getName())) {
                character.damage += character.damage / 3;
                character.speed += 1.25;
                character.protection += 3;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else if (character.getClass().getName().equals(Wizard.class.getName())) {
                character.damage += 5;
                character.speed += 1;
                character.protection += 1;
            }
            //SER316 TASK 2 SPOTBUGS FIX
            else {
                character.damage++;
                character.speed += 0.25;
                character.protection++;
            }
            levelUp(character);
        }
        //SER316 TASK 2 SPOTBUGS FIX
        return false;
    }

    @Override
    public void attack(Character character, Character opponent) {

        if (character.health > 0 && opponent.health > 0) {

            int dmg = this.dealDamage(character);
            this.takeDamage(opponent, dmg);

            if (opponent.health > 0 && character.health > 0) {

                dmg = this.dealDamage(opponent);
                this.takeDamage(character, dmg);

                if (character.health > 0) {
                    this.levelUp(character);
                }
                if (opponent.health > 0) {
                    this.levelUp(opponent);
                }
            }
        }
    }

    @Override
    public int play() {
        int startingExperience = player.experience;
        for (Character opponent : opponents) {
            Character[] orderOfAttack = new Character[2];
            if (player.speed > opponent.speed) {
                orderOfAttack[0] = player;
                orderOfAttack[1] = opponent;
                player.experience += Math.ceil(player.speed - opponent.speed);
            } else {
                orderOfAttack[1] = opponent;
                orderOfAttack[0] = player;
                opponent.experience += Math.ceil(opponent.speed - player.speed);
            }
            attack(orderOfAttack[0], orderOfAttack[1]);
        }
        for (int o = 0; o < opponents.size(); o++) {
            if (opponents.get(o).health <= 0) {
                System.out.println(opponents.get(o).getClass().getName() + " removed\n");
                removeOpponent(opponents.get(o));
                o--;
            }
        }
        return player.experience - startingExperience;
    }
}