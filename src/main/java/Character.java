public class Character {
    int health=100;
    int level=1;
    int experience=0;
    int protection=0;
    int damage=0;
    double speed=0.0;
    int pointsPerLevel=100;

    public void printInfo() {
        System.out.println("Class: " + this.getClass().toString());
        System.out.println("Level: " + level);
        System.out.println("Health: " + health);
        System.out.println("Experience: " + experience);
        System.out.println("Protection: " + protection);
        System.out.println("Damage: " + damage);
        System.out.println("Speed: " + speed);
        System.out.println("Points per Level: " + pointsPerLevel);
        System.out.println("\n");
    }
}