/**
 * Class Character provides information about game character.
 */
public class Character {
    /**
     * Default health.
     */
    public static final int defaultHealth = 100; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default level.
     */
    public static final int defaultLevel = 1; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default experience.
     */
    public static final int defaultExperience = 0; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default protection.
     */
    public static final int defaultProtection = 0; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default damage.
     */
    public static final int defaultDamage = 0; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default speed.
     */
    public static final double defaultSpeed = 0.0; //SER316 TASK 2 SPOT- BUGS FIX
    /**
     * Default points per level.
     */
    public static final int defaultPointsPerLevel = 100; //SER316 TASK 2 SPOT- BUGS FIX

    /**
     * Character health info.
     */
    int health = defaultHealth;
    /**
     * Character level info.
     */
    int level = defaultLevel;
    /**
     * Character experience info.
     */
    int experience = defaultExperience;
    /**
     * Character protection info.
     */
    int protection = defaultProtection;
    /**
     * Character damage info.
     */
    int damage = defaultDamage;
    /**
     * Character speed info.
     */
    double speed = defaultSpeed;
    /**
     * Character pointsPerLevel info.
     */
    int pointsPerLevel = defaultPointsPerLevel;

    /**
     * Method printInfo() is used to print
     * the information for the game character.
     */
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
