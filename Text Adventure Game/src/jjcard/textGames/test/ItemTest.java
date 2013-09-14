package jjcard.textGames.test;

import static org.junit.Assert.*;

import org.junit.Test;
import jjcard.textGames.game.*;

public class ItemTest {

	@Test
	public void Itemtest() {
		Item item = new Item("basic item", "it shows off the true potential of an item...which isn't much");
		assertEquals("basic item", item.getStandardName());
		assertEquals("it shows off the true potential of an item...which isn't much", item.getInfo());
		
		item.setHidden(true);
		item.setMovable(false);
		item.setCost(1);
		item.setLevel(99);
		item.setRoomDescription("an item is in the room");
		
		assertEquals(item.isHidden(), true);
		assertEquals(item.isMovable(), false);
		assertEquals(item.getCost(), 1);
		assertEquals(item.getLevel(), 99);
		assertEquals(item.getRoomDescription(), "an item is in the room");
		
		item.changeCost(1);
		assertEquals(item.getCost(), 2);
		item.changeLevel(-98);
		assertEquals(item.getLevel(), 1);
		assertEquals(item.getUse(), ItemUse.Item);
		
	}
	@Test
	public void WeaponTest(){
		Weapon weapon = new Weapon("sword", "pointy end", 2);
		assertEquals(weapon.getStandardName(), "sword");
		assertEquals(weapon.getInfo(), "pointy end");
		assertEquals(weapon.getAttack(), 2);
		
		weapon.setCost(3);
		assertEquals(weapon.getCost(), 3);
		
		assertEquals(weapon.getCrit(), 1);
		weapon.setCrit(5);
		assertEquals(weapon.getCrit(), 5);
		weapon.setDurability(112);
		assertEquals(weapon.getDurability(), 112);
		
		
	}

}
