package jjcard.textGames.game.shop;

import java.util.List;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.parser.ITextTokenType;
/**
 * 
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Seller<T extends ITextTokenType> {

	
	public void sell(IMob buyer, T item);
	
	public void sell(IMob buyer, T item, int amount);
	
	public List<IItem> getInventory();
}
