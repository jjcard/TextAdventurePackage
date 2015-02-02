package jjcard.textGames.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jjcard.textGames.game.IWorld;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.impl.BasicTextParser;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;
import jjcard.textGames.game.parser.impl.TextDictionary;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {
	IWorld<BasicTextTokenType, ReturnCom> world;
	Player player;
	Location local;
	Location hallway;
	Mob mob;
	ITextParser<BasicTextTokenType> parser;

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
		ITextTokenStream<BasicTextTokenType> ck = world.parseInput("move south");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.MOVE);
		assertEquals(ck.getFirstObject().getToken(), "south");
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.MOVED_DIRECTION);
		
	}
	@Test
	public void MobsWorldTest(){
		local.addMob( mob);
		assertEquals(world.getCurrent().getMob("goblin"), mob);
		ITextTokenStream<BasicTextTokenType> ck = world.parseInput("Look goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		ck = world.parseInput("look at goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.LOOK_MOB);
		
		ck = world.parseInput("Loot all goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOT);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.LOOT_MOB_ALIVE);
		
	
	}
	@Test
	public void WorldCombatTest(){
		local.addMob(mob);
		ITextTokenStream<BasicTextTokenType> ck = world.parseInput("attack goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.ATTACK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		assertEquals(mob.getHealth(), 10);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(ReturnCom.ATTACK_MOB, rc);
		assertEquals(mob.getHealth(), 6);
		assertTrue(mob.getStatusList().isEmpty());
		Item coin = new Item.Builder().standardName("coin").info("a single golden coin").build();
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
		Armour wool = new Armour.Builder().standardName("wool").info("its itchness might be a defense").level(0).defense(4).build();
		player.addItem( wool);
		ITextTokenStream<BasicTextTokenType> ck = world.parseInput("equip wool");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.EQUIP);
		assertEquals(ck.getFirstObject().getToken(), "wool");
		assertEquals(player.getFullDefense(), 8);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(ReturnCom.EQUIPPED_ARMOUR, rc);
		assertEquals(8 + 4, player.getFullDefense() );
	
		
		Weapon weapon = new Weapon.Builder().standardName("shank").info("it can also be used as a verb").attack(3).build();
		player.addItem(weapon);
		ck = world.parseInput("equip shank");
		assertEquals( BasicTextTokenType.EQUIP, ck.getVerb().getType());
		assertEquals( "shank", ck.getFirstObject().getToken());
		assertEquals(player.getFullAttack(), 5);
		rc = world.executeCommands(ck);
		assertEquals( ReturnCom.EQUIPPED_WEAPON, rc);
		assertEquals(player.getFullAttack(), 8);
		assertEquals(player.getStandardWeaponKey(), "shank");
		
		
		ck = world.parseInput("unequip shank");
		assertEquals(BasicTextTokenType.UNEQUIP, ck.getVerb().getType() );
		assertEquals( "shank", ck.getFirstObject().getToken());
		assertFalse(player.containsItem("shank"));
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.UNEQUIPPED_WEAPON);
		assertNull(player.getWeapon());
		assertTrue(player.containsItem("shank"));
		assertNull(player.getStandardWeaponKey());
		assertEquals(player.getFullAttack(), 5);
		
		ck = world.parseInput("UnEquiP wool");
		assertEquals(BasicTextTokenType.UNEQUIP, ck.getVerb().getType() );
		assertEquals(ck.getFirstObject().getToken(), "wool");
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
		ITextTokenStream<BasicTextTokenType> ck = world.parseInput("get item");
		assertEquals(ck.getFirstObject().getToken(), "item");
		assertEquals(ck.getFirstObject().getType(), BasicTextTokenType.ITEM);
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.GET);
		ReturnCom rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.GOT_ITEM);
		assertTrue(player.containsItem("item"));
		assertFalse(world.getCurrent().containsItem("item"));
		
		ck = world.parseInput("drop item");
		assertEquals(ck.getFirstObject().getToken(), "item");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.DROP);
		rc = world.executeCommands(ck);
		assertEquals(rc, ReturnCom.ITEM_DROPPED);
		assertFalse(player.containsItem("item"));
		assertTrue(world.getCurrent().containsItem("item"));
	}

	@Before
	public void setUp(){
		
		 player = new Player.Builder().standardName("jjcard").maxHealth(50).health(50).defense(8).attack(5).build();
		 local = new Location("entry room", "A barren room.");
		 
		 Item item = new Item.Builder().standardName("item").build();
		 local.addItem(item);
		 hallway = new Location("hallway","a long hallway with one torch.");
		local.addExit("NORTH", hallway);
		hallway.addExit(Exit.SOUTH.getWithLocation(local));
		 world = new World(local, player);
		 world.setTextParser(getParser());
		 
		 
		 mob = new Mob.Builder().standardName("Goblin").health(10).defense(1).attack(4).build();
		mob.setDescription("You can tell its a goblin because it's green and broccoli usually doesn't try to kill you");
		
	}
	private ITextParser<BasicTextTokenType> getParser(){
		if (parser == null){
			parser = new BasicTextParser<BasicTextTokenType>();
			TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<BasicTextTokenType>(BasicTextTokenType.values());
			dictionary.putAll(BasicTextTokenType.DIRECTION, Exit.DEFAULT_VALUES);
			dictionary.putAll(BasicTextTokenType.ITEM, "coin", "item");
			dictionary.putAll(BasicTextTokenType.WEAPON, "shank");
			dictionary.putAll(BasicTextTokenType.ENEMY, "goblin");
			dictionary.putAll(BasicTextTokenType.ARMOR, "wool");
			parser.setTextDictionary(dictionary);
			//TODO make this less tedious..			
		}

		
		return parser;
	}

}
