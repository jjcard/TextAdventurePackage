package jjcard.textGames.game.Leveling;

public interface LevelingStrategy<A extends HasLeveling> {

	
	/**
	 * Returns the user of the LevelingStrategy
	 * @return
	 */
	public A getUser();
	
	/**
	 * updates the levelingStrategy and returns the result
	 * @return
	 */
	public A update();
	

}
