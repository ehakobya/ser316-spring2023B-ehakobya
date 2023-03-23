public interface GamePlayInterface {

    public boolean addOpponent(Character opponent);

    public boolean removeOpponent(Character opponent);

    public int dealDamage(Character character);
    
    public int takeDamage(Character character, int blowDamage);
    
    public boolean levelUp(Character character);
    
    public void attack(Character character, Character opponent);
    
    public int play();
    
    
    
}