package jjcard.textGames.game.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemTest {

	@Test
	public void Itemtest() {
		Item item = new Item.ItemBuilder().standardName("basic item").info("it shows off the true potential of an item...which isn't much").build();
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
		Weapon weapon = new Weapon.WeaponBuilder().standardName("sword").info("pointy end").attack(2).build();
		assertEquals(weapon.getStandardName(), "sword");
		assertEquals(weapon.getInfo(), "pointy end");
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
	public void jsonTest() throws JsonGenerationException, JsonMappingException, IOException{
		int cost = 55;
		String name = "fsafsd";
		Item item = new Item.ItemBuilder().standardName(name).cost(cost).build();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, item);
		
		
		Item in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Item.class);
		
		assertEquals(item, in);
		assertEquals(55, in.getCost());
		assertEquals(name, in.getStandardName());
	}

}
