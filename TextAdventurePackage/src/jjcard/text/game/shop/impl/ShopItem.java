package jjcard.text.game.shop.impl;

import jjcard.text.game.IItem;
import jjcard.text.game.shop.IShopItem;

public class ShopItem<T extends IItem> implements IShopItem{
	
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
		amount = -1;
		this.viewDescription = null;
	}
	public ShopItem(T item, int price, int amount){
		this.item = item;
		if (item == null){
			throw new NullPointerException();
		}
		if (amount < 0){
			throw new IllegalArgumentException("amount must be zero or greater");
		}
		if (price < 0){
			throw new IllegalArgumentException("price must be zero or greater");
		}
		this.amount = amount;
		this.viewDescription = null;
	}
	public ShopItem(T item, int price, int amount, String viewDescription){
		this.item = item;
		if (item == null){
			throw new NullPointerException();
		}
		if (amount < 0){
			throw new IllegalArgumentException("amount must be zero or greater");
		}
		if (price < 0){
			throw new IllegalArgumentException("price must be zero or greater");
		}
		this.amount = amount;
		this.viewDescription = viewDescription;
	}
	public IItem getItem(){
		return item;
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
		return amount == -1;
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
}
