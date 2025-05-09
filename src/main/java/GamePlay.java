import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class GamePlay implements GamePlayInterface {

    public Character player;
    public List<Character> opponents;
    //public List<Character> remove;

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
        // use instance methods instead of calling data structure methods directly
        // ensures proper abstraction utilization, the instance methods should work
        // even if underlying data structure is swapped
        addOpponent(new Bard());
        addOpponent(new Druid());
        addOpponent(new Rogue());
        addOpponent(new Ranger());
        addOpponent(new Barbarian());
    }

    /**
     * Parameterized constructor for Game Play.
     * Allows choice of opponent.
     *
     * @param character type for player to use
     * @param opponent type for opponent
     */
    public GamePlay(Character character, Character opponent) {
        this.player = character;
        this.opponents = new LinkedList<>();
        this.opponents.add(opponent);
    }

    /**
     * Utility method to add an opponent to the list of opponents.
     *
     * @param opponent to add
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addOpponent(Character opponent) {
        /* remove unnecessary if conditions there is no reason to complicate the code,
           it can only introduce bugs */
        return this.opponents.add(opponent);
    }

    /**
     * Utility method to remove an opponent from the list of opponents.
     *
     * @param opponent to remove
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeOpponent(Character opponent) {
        /* remove unnecessary if conditions there is no reason to complicate the code,
           it can only introduce bugs */
        return this.opponents.remove(opponent);
    }

    /**
     * Function to add experience points for attacking character and returning the damage
     * the character tries to deal. Experience is only gained if the character still has
     * more than 0 health, damage is also only dealt when health > 0.
     * If the health of a character is less than 10 they deal double damage.
     *
     * @param character that is dealing damage
     * @return damage stat of character as int
     */
    @Override
    public int dealDamage(Character character) {
        // Health less than 0 or more than a 100 should not be possible
        if (character.health < 0 || character.health > 100)  {
            System.out.println("ERROR: character health outside of 0-100 range!");
            return -1;
        }
        // Health is valid, but 0 ... character dead?
        if (character.health == 0) {
            return 0;
        }

        // If health is valid, damage == character.damage
        int damage = character.damage;
        // if health less than 10 - double the damage
        if (character.health < 10) {
            damage *= 2;
        }

        // experience gained == damage
        character.experience += damage;

        // return damage
        return damage;
    }

    /**
     * Function to add experience points for attacked character as well as update their
     * health based on the attack and their protection.
     * If their protection is higher than the blowDamage then the character will heal by half
     * of that difference they will also gain the full difference as experience
     *
     * <p>If their protection is lower or equal than the blowDamage then the character will take
     * half the difference as experience and the health will be reduced by the full difference.
     *
     *
     * <p>If the difference by half is 0.5 we floor it.
     *
     * <p>Health cannot go below 0
     *
     * @param character that is being attacked
     * @param blowDamage full force of attack without protection factored in
     * @return amount of damage actually taken by the character as int
     */
    @Override
    public int takeDamage(Character character, int blowDamage) {
        //  Health less than 0 or more than a 100 should not be possible
        if (character.health < 0 || character.health > 100) {
            System.out.println("ERROR: character health outside of 0-100 range!");
            return -1;
        }
        // Calculate if any damage is taken
        int damageTaken = blowDamage - character.protection;
        // Protection is higher than blowDamage
        if (damageTaken < 0) {
            // Health heals by half, set to 100 if gets above 100
            character.health += Math.floor(damageTaken * -1 / 2.0);
            // Experience grows by damageTaken
            character.experience += damageTaken * -1;
            // No actual damage is taken in this case, so return 0
            damageTaken = 0;
        } else {
            // Experience grows by half
            character.experience += Math.floor(damageTaken / 2.0);
            // actual damage is being taken, decrease health, set to 0 if gets below 0
            character.health -= damageTaken;
        }

        // Make health valid if below 0 or above 100
        if (character.health < 0) {
            character.health = 0;
        }
        if (character.health > 100) {
            character.health = 100;
        }

        // return actual damage taken
        return damageTaken;
    }

    /**
     * Function to level up characters correctly, if they have enough points to do so.
     * You can assume that the new stats are what we want, so they are not wrong.
     * So this method is not wrong, it is the way we want it!
     *
     * @param character Character to check for levelUp
     * @return boolean true if character leveld up, false if they did not
     */
    @Override
    public boolean levelUp(Character character) {
        boolean leveledUp = false;
        while (character.experience >= character.pointsPerLevel) {
            // If XP matches threshold exactly, bonus boost
            if (character.experience == character.pointsPerLevel) {
                character.experience += 5;
            }

            character.level++;
            character.pointsPerLevel *= 2;
            character.health = 100;

            // Apply character-specific stat increases
            if (character instanceof Barbarian) {
                character.damage += 10;
                character.speed += 0.25;
                character.protection += 2;
            } else if (character instanceof Bard) {
                character.damage += character.damage / 2;
                character.speed += 0.5;
                character.protection += character.protection / 2;
            } else if (character instanceof Druid) {
                character.damage += 10;
                character.speed += 0.25;
                character.protection += 2;
            } else if (character instanceof Ranger) {
                character.damage += character.damage % 10;
                character.speed += 0.5;
                character.protection += character.protection % 5;
            } else if (character instanceof Rogue) {
                character.damage += character.damage / 3;
                character.speed += 1.25;
                character.protection += 3;
            } else if (character instanceof Wizard) {
                character.damage += 5;
                character.speed += 1;
                character.protection += 1;
            } else {
                character.damage++;
                character.speed += 0.25;
                character.protection++;
            }

            leveledUp = true;
        }

        return leveledUp;
    }

    /**
     * Function that facilitates the attacker dealing damage to their opponent and then
     * the opposite.
     *
     * <p>A character can only attack if both still have health greater than 0, this needs
     * to be true for both attacks happening here
     *
     * <p>You do NOT have the implemented methods for this but just 5 implemented versions
     * in the .class files in the cls directory. So you need to Blackbox test this method
     * based on the description you get here.
     * As you see the method returns void, so no return type. You need to come up with a way to
     * still test if this method does what it is supposed to do. It is up to you to figure
     * that out.
     *
     * <p>This method uses dealDamage and takeDamage from above, which you should BlackBox test
     * first.
     *
     * <p>An attack only happens if health>0 for both characters
     * The first character attacks first, by using dealsDamage and the opponent takesDamage.
     * Then the characters level up (call levelUp on both) -- if health > 0
     *
     * <p>Then the other character attacks, same procedure as above
     *
     *
     *
     * @param character that is attacking
     * @param opponent that is being attacked
     */
    @Override
    public void attack(Character character, Character opponent) {
        if (character.health > 0 && opponent.health > 0) {
            int blowCharacter = this.dealDamage(character);
            this.takeDamage(opponent, blowCharacter);

            if (character.health > 0) {
                this.levelUp(character);
            }
            if (opponent.health > 0) {
                this.levelUp(opponent);
            }

            if (opponent.health > 0 && character.health > 0) {
                int blowOpponent = this.dealDamage(opponent);
                this.takeDamage(character, blowOpponent);

                if (character.health > 0) {
                    this.levelUp(character);
                }
                if (opponent.health > 0) {
                    this.levelUp(opponent);
                }
            }
        }
    }

    /**
     * This method returns the amount of experience points earned by the player during one round
     * of attacks.
     * White box test me in assignment 3 (not in 2) !
     * What this method does:
     * - The player will attack each opponent once, and each opponent will attack the player once.
     * - The player will just iterate through the list of opponents in the order they are in
     * - The attack from an opponent and the attack on the same opponent happen right after one
     * another. The order in which the attacks happen are based on the speed of the opponent and
     * player. The faster of the two attacks first, then the slower. If equal the player attacks
     * first. The character that attacks first is awarded the difference of the two speeds
     * rounded up to the next integer in experience points. Your player then moves onto the
     * next opponent.
     * - The attack and levelUp are in separate methods (for whitebox testing in assignment 3 you
     * can assume that these methods work correctly and it just shows up as "node" in your graph)
     *
     * @return the amount of experience points that the play acquired during play as int
     */
    @Override
    public int play() {
        int startingExperience = player.experience;
        for (Character opponent : opponents) {
            //determine order of attack and give experience points for attacking first
            Character[] orderOfAttack = new Character[2];
            if (player.speed > opponent.speed) {
                orderOfAttack[0] = player;
                orderOfAttack[1] = opponent;
                player.experience += Math.ceil(player.speed - opponent.speed);
            } else { // formatting.. easier to read
                // correct the ordering of attack, otherwise player always attacked first
                // and the method did not work according to the spec
                orderOfAttack[0] = opponent;
                orderOfAttack[1] = player;
                opponent.experience += Math.ceil(opponent.speed - player.speed);
             }

            // attack in order
            attack(orderOfAttack[0], orderOfAttack[1]);

        }

        // remove opponents that have <= 0 health
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
