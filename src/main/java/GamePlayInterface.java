/**
 * Interface GamePLayInterface for the game.
 */
public interface GamePlayInterface {
    /**
     * Method to add an opponent/character to the game.
     * @param opponent type of character to be added to the game
     * @return true if successful, false otherwise
     */
    boolean addOpponent(Character opponent);

    /**
     * Method to remove an opponent/character from the game.
     * @param opponent type of character to be removed from the game
     * @return true if successful, false otherwise
     */
    boolean removeOpponent(Character opponent);

    /**
     * Method to add experience points for attacking character and
     * returning the damage the character tries to deal.
     * @param character the one who deals the damage
     * @return damage stat of character as int
     */
    int dealDamage(Character character);

    /**
     * Method to add experience points for attacked character as well as
     * update their health based on the attack and their protection.
     * @param character the one who gets attacked
     * @param blowDamage full force of attack without protection factored in
     * @return amount of damage actually taken by the character as int
     */
    int takeDamage(Character character, int blowDamage);

    /**
     * Method to level up characters correctly,
     * if they have enough points to do so.
     * @param character the one who levels up
     * @return boolean true if character leveled up, false if they did not
     */
    boolean levelUp(Character character);

    /**
     * Method that facilitates the attacker dealing damage to their opponent
     * and then the opposite.
     * @param character the one who attacks
     * @param opponent the one who gets attacked
     */
    void attack(Character character, Character opponent);

    /**
     * Method returns the amount of experience points earned by the player
     * during one round of attacks.
     * @return the amount of experience points that
     * the play acquired during play as int
     */
    int play();
}
