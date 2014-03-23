package jjcard.textGames.test;

import static org.junit.Assert.*;

import jjcard.textGames.game.IWorld;
import jjcard.textGames.game.impl.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {
	IWorld world;
	Player player;
	Location local;
	Location hallway;
	Mob mob;

	@Test
	public void MovingTest() {
		assertEquals(player, world.getPlayer());
		assertEquals(local, world.getCurrent());
		world.goDirection("NORTH");
		assertEquals(hallway, world.getCurrent());
		world.goDirection("SOUTH");
		assertEquals("entry room", world.getCurrent().getName());
		world.goDirection("north");
		assertEquals("hallway", world.getCurrent().getName());
		CommandAndKey ck = world.parseInput("move south");
		assertEquals(ck.getCommand(), Commands.MOVE);
		assertEquals(ck.getKey(), "south");
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.MOVED_DIRECTION);
		
	}
	@Test
	public void MobsWorldTest(){
		local.addMob( mob);
		assertEquals(world.getCurrent().getMob("goblin"), mob);
		CommandAndKey ck = world.parseInput("Look goblin");
		assertEquals(ck.getCommand(), Commands.LOOK);
		assertEquals(ck.getKey(), "goblin");
		ck = world.parseInput("look at goblin");
		assertEquals(ck.getCommand(), Commands.LOOK);
		assertEquals(ck.getKey(), "goblin");
		
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.LOOK_MOB);
		
		ck = world.parseInput("Loot all goblin");
		assertEquals(ck.getCommand(), Commands.LOOT_ALL);
		assertEquals(ck.getKey(), "goblin");
		
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.LOOT_MOB_ALIVE);
		
	
	}
	@Test
	public void WorldCombatTest(){
		local.addMob(mob);
		CommandAndKey ck = world.parseInput("attack goblin");
		assertEquals(ck.getCommand(), Commands.ATTACK);
		assertEquals(ck.getKey(), "goblin");
		assertEquals(mob.getHealth(), 10);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(ReturnCom.ATTACK_MOB, rc);
		assertEquals(mob.getHealth(), 6);
		assertTrue(mob.getStatusList().isEmpty());
		Item coin = new Item.ItemBuilder().standardName("coin").info("a single golden coin").build();
		mob.addItem( coin);
		
		assertTrue(mob.containsItem("coin"));
		rc = world.executeCommands(ck);
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.ATTACK_MOB_KILLED);
		assertTrue(mob.isDead());
		assertEquals(mob.getHealth(), 0);
		assertFalse(player.containsItem("coin"));
		rc = world.executeCommands(world.parseInput("loot all goblin"));
		assertEquals(rc, ReturnCom.LOOT_MOB);
		assertTrue(player.containsItem("coin"));
			
	}
	@Test
	public void EquipWorldTest(){
		Armour wool = new Armour.ArmourBuilder().standardName("wool").info("its itchness might be a defense").level(0).defense(4).build();
		player.addItem( wool);
		CommandAndKey ck = world.parseInput("equip wool");
		assertEquals(ck.getCommand(), Commands.EQUIP);
		assertEquals(ck.getKey(), "wool");
		assertEquals(player.getFullDefense(), 8);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(ReturnCom.EQUIPPED_ARMOUR, rc);
		assertEquals(player.getFullDefense(), 8 + 4);
	
		
		Weapon weapon = new Weapon.WeaponBuilder().standardName("shank").info("it can also be used as a verb").attack(3).build();
		player.addItem(weapon);
		ck = world.parseInput("equip shank");
		assertEquals(Commands.EQUIP, ck.getCommand());
		assertEquals(ck.getKey(), "shank");
		assertEquals(player.getFullAttack(), 5);
		rc = world.executeCommands(ck);
		assertEquals( ReturnCom.EQUIPPED_WEAPON, rc);
		assertEquals(player.getFullAttack(), 8);
		assertEquals(player.getStandardWeaponKey(), "shank");
		
		
		ck = world.parseInput("unequip shank");
		assertEquals(ck.getCommand(), Commands.UNEQUIP);
		assertEquals(ck.getKey(), "shank");
		assertFalse(player.containsItem("shank"));
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.UNEQUIPPED_WEAPON);
		assertNull(player.getWeapon());
		assertTrue(player.containsItem("shank"));
		assertNull(player.getStandardWeaponKey());
		assertEquals(player.getFullAttack(), 5);
		
		ck = world.parseInput("UnEquiP wool");
		assertEquals(ck.getCommand(), Commands.UNEQUIP);
		assertEquals(ck.getKey(), "wool");
		assertFalse(player.containsItem("wool"));
		
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.UNEQUIPPED_ARMOUR);
		assertTrue(player.containsItem("wool"));
		assertEquals(player.getFullDefense(), 8);
		
		
		
		
	}
	@Test
	public void ItemWorldTest(){
		assertTrue(world.getCurrent().containsItem("item"));
		assertFalse(player.containsItem("item"));
		CommandAndKey ck = world.parseInput("get item");
		assertEquals(ck.getKey(), "item");
		assertEquals(ck.getCommand(), Commands.GET);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.GOT_ITEM);
		assertTrue(player.containsItem("item"));
		assertFalse(world.getCurrent().containsItem("item"));
		
		ck = world.parseInput("drop item");
		assertEquals(ck.getKey(), "item");
		assertEquals(ck.getCommand(), Commands.DROP);
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.ITEM_DROPPED);
		assertFalse(player.containsItem("item"));
		assertTrue(world.getCurrent().containsItem("item"));
	}

	@Before
	public void setUp(){
		
		 player = new Player.PlayerBuilder().standardName("jjcard").curHelath(50).defense(8).attack(5).build();
		 local = new Location("entry room", "A barren room.");
		 
		 Item item = new Item.ItemBuilder().standardName("item").build();
		 local.addItem(item);
		 hallway = new Location("hallway","a long hallway with one torch.");
		local.addExit("NORTH", hallway);
		hallway.addExit(Exit.SOUTH, local);
		 world = new World(local, player);
		 
		 mob = new Mob.MobBuilder().standardName("Goblin").curHelath(10).defense(1).attack(4).build();
		mob.setDescription("You can tell its a goblin because it's green and broccoli usually doesn't try to kill you");
		
	}

}
