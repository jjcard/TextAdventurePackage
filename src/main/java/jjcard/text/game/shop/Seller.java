package jjcard.text.game.shop;

import java.util.List;

import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.util.Experimental;
/**
 * 
 *
 */
@Experimental
public interface Seller {

	
	IItem sell(IMob buyer, IShopItem item);
	
	IItem sell(IMob buyer, IShopItem item, int amount);
	
	List<IShopItem> getInventory();
}
