package jjcard.textGames.game.shop;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
/**
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Buyer {

	public void buy(IMob seller, IItem item);
	
	public void buy(IMob seller, IItem item, int amount);
	
	
}
