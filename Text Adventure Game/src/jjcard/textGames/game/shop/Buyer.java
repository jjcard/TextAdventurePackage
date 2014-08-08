package jjcard.textGames.game.shop;

import jjcard.textGames.game.IMob;
import jjcard.textGames.game.parser.ITextTokenType;
/**
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Buyer<T extends ITextTokenType> {

	public void buy(IMob seller, T item);
	
	public void buy(IMob seller, T item, int amount);
	
	
}
