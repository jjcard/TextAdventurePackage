package jjcard.textGames.game.shop;

import java.util.List;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
/**
 * 
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Seller {

	
	public IItem sell(IMob buyer, IShopItem item);
	
	public IItem sell(IMob buyer, IShopItem item, int amount);
	
	public List<IShopItem> getInventory();
}
