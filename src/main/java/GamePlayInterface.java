public interface GamePlayInterface {

    /**
     * Utility method to add an opponent to the list of opponents.
     *
     * @param opponent to add
     * @return true if successful, false otherwise
     */
    public boolean addOpponent(Character opponent);

    /**
     * Utility method to remove an opponent from the list of opponents.
     *
     * @param opponent to remove
     * @return true if successful, false otherwise
     */
    public boolean removeOpponent(Character opponent);

    /**
     * Function to add experience points for attacking character and returning the damage the
     * character tries to deal. Experience is only gained if the character still has more
     * than 0 health, damage is also only dealt when health > 0. If the health of a character
     * is less than 10 they deal double damage.
     *
     * @param character that is dealing damage
     * @return damage stat of character as int
     */
    public int dealDamage(Character character);

    /**
     * Function to add experience points for attacked character as well as update their health
     * based on the attack and their protection. If their protection is higher than the
     * blowDamage then the character will heal by half of that difference they will also gain
     * the full difference as experience
     * <p>If their protection is lower or equal than the blowDamage then the character will take
     * half the difference as experience and the health will be reduced by the full difference.
     * </p>
     * <p>If the difference by half is 0.5 we floor it.
     * </p>
     * Health cannot go below 0
     *
     * @param character  that is being attacked
     * @param blowDamage full force of attack without protection factored in
     * @return amount of damage actually taken by the character as int
     */
    public int takeDamage(Character character, int blowDamage);

    /**
     * Function to level up characters correctly, if they have enough points to do so.
     * You can assume that the new stats are what we want, so they are not wrong. So this
     * method is not wrong, it is the way we want it!
     *
     * @param character Character object
     * @return boolean true if character leveld up, false if they did not
     */
    public boolean levelUp(Character character);

    /**
     * Function that facilitates the attacker dealing
     * damage to their opponent and then the opposite.
     * <p>A character can only attack if both still have health greater than 0,
     * this needs to be true for both attacks happening here
     * </p>
     * You do NOT have the implemented methods for this but just 5 implemented versions in the
     * .class files in the cls directory. So you need to Blackbox test this method based on
     * the description you get here. As you see the method returns void, so no return type.
     * You need to come up with a way to still test if this method does what it is supposed
     * to do. It is up to you to figure that out.
     *
     * <p>This method uses dealDamage and takeDamage from above,
     * which you should BlackBox test first. <p>
     * An attack only happens if health>0 for both characters. The first character attacks
     * first, by using dealsDamage and the opponent takesDamage. Then the characters level up
     * (call levelUp on both) -- if health > 0
     * </p>
     * Then the other character attacks, same procedure as above
     *
     * @param character that is attacking
     * @param opponent  that is being attacked
     */
    public void attack(Character character, Character opponent);

    /**
     * This method returns the amount of experience points earned by the player during one
     * round of attacks. White box test me in assignment 3 (not in 2) !
     * What this method does:
     * - The player will attack each opponent once,
     * and each opponent will attack the player once.
     * - The player will just iterate through the list of opponents in the order they are in
     * - The attack from an opponent and the attack on the same opponent happen right
     * after one another. The order in which the attacks happen are based on the speed of
     * the opponent and player. The faster of the two attacks first, then the slower. If equal the player attacks first.
     * The character that attacks first is awarded the difference of the two speeds rounded up
     * to the next integer in experience points.Your player then moves onto the next opponent.
     * - The attack and levelUp are in separate methods (for whitebox testing in assignment 3
     * you can assume that these methods work correctly and it just shows up as "node"
     * in your graph)
     *
     * @return the amount of experience points that the play acquired during play as int
     */
    public int play();

}