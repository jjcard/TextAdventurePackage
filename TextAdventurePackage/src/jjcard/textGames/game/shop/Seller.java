package jjcard.textGames.game.shop;

import java.util.List;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.util.Experimental;
/**
 * 
 * Experimental: Subject to Change
 *
 */
@Experimental
public interface Seller {

	
	public IItem sell(IMob buyer, IShopItem item);
	
	public IItem sell(IMob buyer, IShopItem item, int amount);
	
	public List<IShopItem> getInventory();
}
