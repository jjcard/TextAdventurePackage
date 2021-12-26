package jjcard.text.game;

import jjcard.text.game.util.Experimental;

/**
 *
 */
@Experimental
public interface IStatus {
	
	
	/**
	 * Get the name that should be displayed for this status
	 * @return name
	 */
    String getName();
	/**
	 * Returns true if status takes effect before Mob's turn
	 * @return
	 */
    boolean isBeforeTurn();
	/**
	 * Returns true if status takes effect after Mob's turn
	 * @return
	 */
    boolean isAfterTurn();
	/**
	 * Performs the status effect on mob and returns result. 
	 * mobsTurn is true if it is the turn of the Mob this status is attached to.
	 * @param mobsTurn the boolean
	 */
    void effect(boolean mobsTurn);
	/**
	 * Should return true when the status is done and can be removed from mob.
	 * Should be called after by jjcard.text.game after each check for turn or effect.
	 * @return
	 */
    boolean isDone();
	
	

}
