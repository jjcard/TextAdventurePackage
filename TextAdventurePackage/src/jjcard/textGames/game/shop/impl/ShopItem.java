package jjcard.textGames.game.shop.impl;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.shop.IShopItem;

public class ShopItem<T extends IItem> implements IShopItem{
	
	private final T item;
	private final int amount;
	private int price;
	private final String description;
	
	
	public ShopItem(T item, int price){
		this.item = item;
		if (item == null){
			throw new NullPointerException();
		}
		if (price < 0){
			throw new IllegalArgumentException("price must be zero or greater");
		}
		amount = -1;
		this.description = null;
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
		this.description = null;
	}
	public ShopItem(T item, int price, int amount, String description){
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
		this.description = description;
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
		if (description != null){
			return description;
		}
		return item.getRoomDescription();
	}
	@Override
	public int getPrice(){
		return price;
	}
}
