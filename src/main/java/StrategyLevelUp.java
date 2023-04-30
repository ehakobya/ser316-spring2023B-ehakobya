public class StrategyLevelUp {

    private static void levelUp(Barbarian character) {
        character.setDamage(character.getDamage() + 10);
        character.setSpeed(character.getSpeed() + 0.25);
        character.setProtection(character.getProtection() + 2);
    }

    private static void levelUp(Bard character) {
        character.setDamage(character.getDamage() + 2);
        character.setSpeed(character.getSpeed() + 0.5);
        character.setProtection(character.getProtection() / 2);
    }

    private static void levelUp(Ranger character) {
        character.setDamage(character.getDamage() + 10);
        character.setSpeed(character.getSpeed() + 0.5);
        character.setProtection(character.getProtection() % 5);
    }

    private static void levelUp(Rogue character) {
        character.setDamage(character.getDamage() + 3);
        character.setSpeed(character.getSpeed() + 1.25);
        character.setProtection(character.getProtection() + 3);
    }

    private static void levelUp(Wizard character) {
        character.setDamage(character.getDamage() + 5);
        character.setSpeed(character.getSpeed() + 1);
        character.setProtection(character.getProtection() + 1);
    }

    private static void levelUp(Druid character) {
        character.setDamage(character.getDamage() + 10);
        character.setSpeed(character.getSpeed() + 0.25);
        character.setProtection(character.getProtection() + 2);
    }

    public static void levelUp(Character character) {
        if(character.getClass().equals(Barbarian.class)){
            levelUp((Barbarian) character);
        }
        else if(character.getClass().equals(Bard.class)){
            levelUp((Bard) character);
        }
        else if(character.getClass().equals(Ranger.class)){
            levelUp((Ranger) character);
        }
        else if(character.getClass().equals(Wizard.class)){
            levelUp((Wizard) character);
        }
        else if(character.getClass().equals(Druid.class)){
            levelUp((Druid) character);
        }
        else if(character.getClass().equals(Rogue.class)){
            levelUp((Rogue) character);
        }
    }
}
