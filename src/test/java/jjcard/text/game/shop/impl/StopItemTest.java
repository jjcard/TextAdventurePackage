package jjcard.text.game.shop.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jjcard.text.game.IItem;
import jjcard.text.game.impl.Item;

public class StopItemTest {

	@Test
	public void initTest1() {
		IItem item = new Item("testItem");
		ShopItem<IItem> shopItem = new ShopItem<>(item, 4);
		assertNotNull(shopItem);
		assertEquals(4, shopItem.getPrice());
		assertTrue(shopItem.isInfinite());
	}
	@Test
	public void initTest2() {
		IItem item = new Item("testItem");
		ShopItem<IItem> shopItem = new ShopItem<>(item, 4, 3);
		assertNotNull(shopItem);
		assertEquals(4, shopItem.getPrice());
		assertFalse(shopItem.isInfinite());
		assertEquals(3, shopItem.getAmount());
	}
	
	@Test
	public void initTest3() {
		IItem item = new Item("testItem");
		ShopItem<IItem> shopItem = new ShopItem<>(item, 4, 3, "shop item descrip");
		assertNotNull(shopItem);
		assertEquals(4, shopItem.getPrice());
		assertFalse(shopItem.isInfinite());
		assertEquals(3, shopItem.getAmount());
		assertEquals("shop item descrip", shopItem.getViewDescription());
	}
	@Test
	public void setPriceTest(){
		IItem item = new Item("testItem");
		ShopItem<IItem> shopItem = new ShopItem<>(item, 4);
		assertEquals(4, shopItem.getPrice());
		
		shopItem.setPrice(10);
		assertEquals(10, shopItem.getPrice());
	}
}
