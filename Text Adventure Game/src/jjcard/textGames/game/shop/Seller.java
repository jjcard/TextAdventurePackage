package jjcard.textGames.game.shop;

import java.util.List;

import jjcard.textGames.game.IMob;
/**
 * 
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Seller {

	
	public void sell(IMob buyer, IShopItem item);
	
	public void sell(IMob buyer, IShopItem item, int amount);
	
	public List<IShopItem> getInventory();
}
