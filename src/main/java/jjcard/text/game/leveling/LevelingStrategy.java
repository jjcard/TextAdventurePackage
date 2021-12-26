package jjcard.text.game.leveling;

import jjcard.text.game.util.Experimental;

/**
 * A strategy to level
 *
 * @param <A>
 */
@Experimental
public interface LevelingStrategy<A extends HasLeveling> {

	
	/**
	 * Returns the user of the LevelingStrategy
	 * @return
	 */
	public A getUser();
	
	/**
	 * updates the user and returns the result
	 * @return
	 */
	public A update();
	

}
