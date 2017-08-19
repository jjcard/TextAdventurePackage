package jjcard.text.game.util;

import static jjcard.text.game.util.WorldUtil.ReturnStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jjcard.text.game.IItem;
import jjcard.text.game.impl.Armour;
import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Location;
import jjcard.text.game.impl.Mob;
import jjcard.text.game.impl.Player;
import jjcard.text.game.impl.Weapon;
import jjcard.text.game.util.WorldUtil;
import jjcard.text.game.util.WorldUtil.ReturnStatus;

public class WorldUtilTest {
	Location location;
	Player player;
	WorldUtil<Player> util;
	
	@Before
	public void setUp(){
		 location = new Location("Test location", "What? they can't all be zingers");
		 player = new Player.Builder().name("The Player").build();
		 util = new WorldUtil<>(location, player);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullLocationTest() {
		Player player = new Player.Builder().build();
        new WorldUtil<>(null, player);
	}
	@Test(expected = IllegalArgumentException.class)
	public void nullPlayerTest() {
		Location location = new Location("");
		 new WorldUtil<Player>(location, null);
		
	}
	@Test
	public void initTest(){
		Location location = new Location("Test location", "What? they can't all be zingers");
		Player player = new Player.Builder().name("The Player").build();
		WorldUtil<Player> util = new WorldUtil<>(location, player);
		
		assertNotNull(util);
		
		assertNotNull(location);
		assertNotNull(player);
		
		assertEquals(location, util.getCurrent());
		
		assertEquals(player, util.getPlayer());
	}
	@Test(expected = IllegalArgumentException.class)
	public void setPlayerNullTest(){
		util.setPlayer(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void setCurrentNullTest(){
		util.setCurrent(null);
	}
	@Test
	public void equipArmourTest(){
		String armourName = "QA Confidence Armour";
		Armour armour = new Armour.Builder().name(armourName)
				.roomDescription("The protection of adequate test coverage").defense(44).build();
		assertNull(util.getPlayer().getArmour());
		util.getPlayer().addItem(armour);
		
		assertFalse(util.getPlayer().getInventory().isEmpty());
		
		assertEquals(NOT_FOUND, util.equipArmour("A misspelled preference"));
		
		assertNull(util.getPlayer().getArmour());
		assertTrue(util.getPlayer().containsItem(armourName));
		
		assertEquals(SUCCESS, util.equipArmour(armourName));
		assertNotNull(util.getPlayer().getArmour());
		assertEquals(armour, util.getPlayer().getArmour());
		assertFalse(util.getPlayer().containsItem(armourName));
		assertTrue(util.getPlayer().getInventory().isEmpty());
		
		
		Armour armour2 = new Armour.Builder().name("continuous integration")
				.roomDescription("If you are always testing, then you have nothing to worry about").defense(19).build();
		util.getPlayer().addItem(armour2);
		
		assertEquals(SUCCESS, util.equipArmour("continuous integration"));
		assertNotNull(util.getPlayer().getArmour());
		assertEquals(armour2, util.getPlayer().getArmour());
		assertTrue(util.getPlayer().containsItem(armourName));
		assertFalse(util.getPlayer().getInventory().isEmpty());
		assertTrue(util.getPlayer().containsItem(armourName));
		
		//equipping non-armour to armour slot
		String weaponName = "The Rubber duck";
		Weapon weapon = new Weapon.Builder().name(weaponName)
				.roomDescription("Does a surprising amount of work").attack(44).build();
		util.getPlayer().addItem(weapon);
		
		assertEquals(FAILURE, util.equipArmour(weaponName));
		assertTrue(util.getPlayer().containsItem(weaponName));
	}
	@Test
	public void equipWeaponTest(){
		String weaponName = "The Rubber duck";
		Weapon weapon = new Weapon.Builder().name(weaponName)
				.roomDescription("Does a surprising amount of work").attack(44).build();
		assertNull(util.getPlayer().getWeapon());
		util.getPlayer().addItem(weapon);
		
		assertFalse(util.getPlayer().getInventory().isEmpty());
		
		assertEquals(NOT_FOUND, util.equipWeapon("Coding while tired"));
		
		assertNull(util.getPlayer().getWeapon());
		assertTrue(util.getPlayer().containsItem(weaponName));
		
		assertEquals(SUCCESS, util.equipWeapon(weaponName));
		assertNotNull(util.getPlayer().getWeapon());
		assertEquals(weapon, util.getPlayer().getWeapon());
		assertFalse(util.getPlayer().containsItem(weaponName));
		assertTrue(util.getPlayer().getInventory().isEmpty());
		
		
		Weapon weapon2 = new Weapon.Builder().name("continuous integration")
				.roomDescription("If you are always deploying, bugs will barely matter").attack(19).build();
		util.getPlayer().addItem(weapon2);
		
		assertEquals(SUCCESS, util.equipWeapon("continuous integration"));
		assertNotNull(util.getPlayer().getWeapon());
		assertEquals(weapon2, util.getPlayer().getWeapon());
		assertTrue(util.getPlayer().containsItem(weaponName));
		assertFalse(util.getPlayer().getInventory().isEmpty());
		assertTrue(util.getPlayer().containsItem(weaponName));
		
		
		//equipping a non-weapon
		String armourName = "QA Confidence Armour";
		Armour armour = new Armour.Builder().name(armourName)
				.roomDescription("The protection of adequate test coverage").defense(44).build();
		util.getPlayer().addItem(armour);
		
		assertEquals(FAILURE, util.equipWeapon(armourName));
		assertTrue(util.getPlayer().containsItem(armourName));
	}
	@Test
	public void unequipWeaponTest(){
		String weaponName = "The Rubber duck";
		Weapon weapon = new Weapon.Builder().name(weaponName)
				.roomDescription("Does a surprising amount of work").attack(44).build();
		assertNull(util.getPlayer().getWeapon());
		assertFalse(util.unequipWeapon());
		assertTrue(util.getPlayer().getInventory().isEmpty());
		
		util.setPlayerWeapon(weapon);
		assertNotNull(util.getPlayer().getWeapon());
		assertTrue(util.unequipWeapon());
		assertFalse(util.getPlayer().getInventory().isEmpty());
		assertTrue(util.getPlayer().containsItem(weaponName));
	}
	@Test
	public void unequipArmourTest(){
		String armourName = "QA Confidence Armour";
		Armour armour = new Armour.Builder().name(armourName)
				.roomDescription("The protection of adequate test coverage").defense(44).build();
		assertNull(util.getPlayer().getArmour());
		assertFalse(util.unequipArmour());
		assertTrue(util.getPlayer().getInventory().isEmpty());
		
		util.setPlayerArmour(armour);
		assertNotNull(util.getPlayer().getArmour());
		assertTrue(util.unequipArmour());
		assertFalse(util.getPlayer().getInventory().isEmpty());
		assertTrue(util.getPlayer().containsItem(armourName));
	}
	@Test
	public void lootAllMobTest(){
		IItem item = new Item.Builder().name("Oil Can").build();
		Mob mob = new Mob.Builder().name("Mecha-Goblin")
				.viewDescription("It never wondered at all, just made itself Awesome").validateFields(false).addItem(item).health(10)
				.build();
		
		util.getCurrent().addMob(mob);
		assertTrue(mob.isAlive());
		assertFalse(mob.isHidden());
		assertTrue(mob.containsItem("Oil Can"));
		assertFalse(util.getPlayer().containsItem("Oil Can"));
		
		assertEquals(NOT_FOUND, util.lootAllMob("Ghost of Christmas past"));
		assertFalse(mob.getInventory().isEmpty());
		
		//still alive
		assertEquals(FAILURE, util.lootAllMob("Mecha-Goblin"));
		assertFalse(mob.getInventory().isEmpty());
		
		//He's dead jim
		mob.setHealth(0);
		assertFalse(mob.isAlive());
		assertEquals(SUCCESS, util.lootAllMob("Mecha-Goblin"));
		assertTrue(mob.getInventory().isEmpty());
		assertTrue(util.getPlayer().containsItem("Oil Can"));
	}
	@Test
	public void returnStatusTest(){
		assertTrue(ReturnStatus.SUCCESS.isSuccess());
		assertFalse(ReturnStatus.NOT_FOUND.isSuccess());
		assertFalse(ReturnStatus.FAILURE.isSuccess());
	}
}
