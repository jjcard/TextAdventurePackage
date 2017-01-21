package jjcard.text.game.shop.impl;

import jjcard.text.game.IItem;
import jjcard.text.game.shop.IShopItem;
import jjcard.text.game.util.NotFoundException;

public class ShopItem<T extends IItem> implements IShopItem{
	
	protected static final int INFINITE_FLAG = -1;
	private final T item;
	private final int amount;
	private int price;
	private final String viewDescription;
	
	
	public ShopItem(T item, int price){
		this.item = item;
		if (item == null){
			throw new NullPointerException();
		}
		if (price < 0){
			throw new IllegalArgumentException("price must be zero or greater");
		}
		amount = INFINITE_FLAG;
		this.viewDescription = null;
		this.price = price;
	}
	public ShopItem(T item, int price, int amount){
		this(item, price, amount, null);
	}
	public ShopItem(T item, int price, int amount, String viewDescription){
		this.item = item;
		if (item == null){
			throw new NullPointerException();
		}
		if (amount < INFINITE_FLAG){
			throw new IllegalArgumentException("amount must be -1 or greater");
		}
		if (price < 0){
			throw new IllegalArgumentException("price must be zero or greater");
		}
		this.price = price;
		this.amount = amount;
		this.viewDescription = viewDescription;
	}
	/**
	 * returns the IITem this SHopItem has. Doesn't check if available
	 * @return
	 */
	public IItem getItem(){
		return item;
	}
	/**
	 * Returns the bought item if it available to purchase otherwise throws exception
	 * @return bought item
	 * @throws NotFoundException if not available
	 */
	public IItem getBoughtItem() throws NotFoundException{
		if (isAvailable()){
			return item;
		} else {
			throw new NotFoundException();
		}
	}

	public int getAmount(){
		return amount;
	}
	@Override
	public String getName() {
		return item.getName();
	}
	@Override
	public boolean isInfinite() {
		return amount == INFINITE_FLAG;
	}
	@Override
	public String getRoomDescription() {
		return item.getRoomDescription();
	}
	public String getViewDescription(){
		if (viewDescription != null){
			return  viewDescription;
		}
		return item.getViewDescription();
	}
	@Override
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}
}
