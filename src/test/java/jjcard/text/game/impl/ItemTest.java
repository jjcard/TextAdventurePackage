package jjcard.text.game.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

	@Test
	public void basicItemTest() {
		Item item = new Item.Builder().name("basic item").viewDescription("it shows off the true potential of an item...which isn't much").build();
		assertEquals("basic item", item.getName());
		assertEquals("it shows off the true potential of an item...which isn't much", item.getViewDescription());
		
		item.setHidden(true);
		item.setMovable(false);
		item.setCost(1);
		item.setLevel(99);
		item.setRoomDescription("an item is in the room");

		assertTrue(item.isHidden());
		assertFalse(item.isMovable());
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
	public void setLevelTest_validate() {
		Item item = new Item.Builder().name("basic item").viewDescription("it shows off the true potential of an item...which isn't much").build();
		assertEquals(0, item.getLevel());

		item.setLevel(55);
		assertEquals(55, item.getLevel());

		item.setLevel(-5);
		assertEquals(0, item.getLevel());
	}
	@Test
	public void WeaponTest(){
		Weapon weapon = new Weapon.Builder().name("sword").viewDescription("pointy end").attack(2).build();
		assertEquals(weapon.getName(), "sword");
		assertEquals(weapon.getViewDescription(), "pointy end");
		assertEquals(weapon.getAttack(), 2);
		
		weapon.setCost(3);
		assertEquals(weapon.getCost(), 3);
		
		assertEquals(weapon.getCritChance(), 0);
		weapon.setCritChance(5);
		assertEquals(weapon.getCritChance(), 5);
		weapon.setDurability(112);
		assertEquals(weapon.getDurability(), 112);
		
		
	}
	@Test
	public void jsonTest() throws IOException{
		int cost = 55;
		String name = "fsafsd";
		Item item = new Item.Builder().name(name).cost(cost).build();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, item);
		
		Item in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Item.class);
		
		assertEquals(item, in);
		assertEquals(55, in.getCost());
		assertEquals(name, in.getName());
	}
	@Test
	public void armourJsonTest() throws IOException{
		int cost = 54;
		String name = "chainmail";
		int def = 100;
		boolean hidden = false;
		Armour armour = new Armour.Builder().name(name)
				.cost(cost).defense(def).hidden(hidden).build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, armour);
		
		Armour in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Armour.class);
		
		assertEquals(armour, in);
		assertEquals(cost, in.getCost());
		assertEquals(name, in.getName());
		assertEquals(def, in.getDefense());
		assertEquals(hidden, in.isHidden());
	}
	
	@Test
	public void weaponJsonTest() throws IOException{
		int cost = 54;
		String name = "sword";
		int attack = 100;
		boolean hidden = false;
		int critChance = 50;
		int dur = 10;
		Weapon weapon = new Weapon.Builder().name(name)
				.cost(cost).attack(attack).critChance(critChance).durability(dur).hidden(hidden).build();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, weapon);
		
		Weapon in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Weapon.class);
		
		assertEquals(weapon, in);
		assertEquals(cost, in.getCost());
		assertEquals(name, in.getName());
		assertEquals(attack, in.getAttack());
		assertEquals(hidden, in.isHidden());
		assertEquals(critChance, in.getCritChance());
		assertEquals(dur, in.getDurability());
	}

}