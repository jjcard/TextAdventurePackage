package jjcard.textGames.game.shop;

import java.util.List;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.parser.ITextTokenType;

public interface Buyer<T extends ITextTokenType> {

	public void buy(IMob seller, T item);
	
	public void buy(IMob seller, T item, int amount);
	
	public List<IItem> getInventory();
	
}
