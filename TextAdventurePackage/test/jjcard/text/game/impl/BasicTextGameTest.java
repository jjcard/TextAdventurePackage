package jjcard.text.game.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jjcard.text.game.impl.Armour;
import jjcard.text.game.impl.BasicTextGame;
import jjcard.text.game.impl.Exit;
import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Location;
import jjcard.text.game.impl.Mob;
import jjcard.text.game.impl.Player;
import jjcard.text.game.impl.Weapon;
import jjcard.text.game.parser.ITextParser;
import jjcard.text.game.parser.ITextTokenStream;
import jjcard.text.game.parser.impl.BasicTextParser;
import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.TextDictionary;

public class BasicTextGameTest {

	BasicTextGame game;
	Player player;
	Location local;
	Location hallway;
	Mob mob;
	ITextParser<BasicTextTokenType> parser;
	@Before
	public void setUp(){
		
		 player = new Player.Builder().name("jjcard").maxHealth(50).health(50).defense(8).attack(5).build();
		 local = new Location("entry room", "A barren room.");
		 
		 Item item = new Item.Builder().name("item").build();
		 local.addItem(item);
		 hallway = new Location("hallway","a long hallway with one torch.");
		local.addExit("NORTH", hallway);
		hallway.addExit(Exit.SOUTH.getWithLocation(local));
		game = new BasicTextGame(local, player);
		game.setTextParser(getParser());
		 
		 
		mob = new Mob.Builder().name("Goblin").health(10).defense(1).attack(4).build();
		mob.setViewDescription("You can tell it's a goblin because it's green and broccoli usually doesn't try to kill you.");
		
	}
	@Test
	public void MovingTest() {
		assertEquals(player, game.getPlayer());
		assertEquals(local, game.getCurrent());
		game.movePlayer("NORTH", null);
		assertEquals(hallway, game.getCurrent());
		game.movePlayer("SOUTH", null);
		assertEquals("entry room", game.getCurrent().getName());
		game.movePlayer("north", null);
		assertEquals("hallway", game.getCurrent().getName());
		ITextTokenStream<BasicTextTokenType> ck = game.parseInput("move south");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.MOVE);
		assertEquals(ck.getFirstObject().getToken(), "south");
		 game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.MOVED_DIRECTION);
		
	}
	@Test
	public void MobsWorldTest(){
		local.addMob(mob);
		assertEquals(game.getCurrent().getMob("goblin"), mob);
		ITextTokenStream<BasicTextTokenType> ck = game.parseInput("Look goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		ck = game.parseInput("look at goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.LOOK_MOB);
		
		ck = game.parseInput("Loot all goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.LOOT);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.LOOT_MOB_ALIVE);
		
	
	}
	@Test
	public void WorldCombatTest(){
		local.addMob(mob);
		ITextTokenStream<BasicTextTokenType> ck = game.parseInput("attack goblin");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.ATTACK);
		assertEquals(ck.getFirstObject().getToken(), "goblin");
		assertEquals(mob.getHealth(), 10);
		 game.executeCommands(ck);
//		assertEquals(ReturnCom.ATTACK_MOB, rc);
		assertEquals(mob.getHealth(), 6);
		assertTrue(mob.getStatusList().isEmpty());
		Item coin = new Item.Builder().name("coin").viewDescription("a single golden coin").build();
		mob.addItem( coin);
		
		assertTrue(mob.containsItem("coin"));
		 game.executeCommands(ck);
		 game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.ATTACK_MOB_KILLED);
		assertTrue(mob.isDead());
		assertEquals(mob.getHealth(), 0);
		assertFalse(player.containsItem("coin"));
		 game.executeCommands(game.parseInput("loot all goblin"));
//		assertEquals(rc, ReturnCom.LOOT_MOB);
		assertTrue(player.containsItem("coin"));
			
	}
	@Test
	public void EquipWorldTest(){
		Armour wool = new Armour.Builder().name("wool").viewDescription("its itchness might be a defense").level(0).defense(4).build();
		player.addItem( wool);
		ITextTokenStream<BasicTextTokenType> ck = game.parseInput("equip wool");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.EQUIP);
		assertEquals(ck.getFirstObject().getToken(), "wool");
		assertEquals(player.getFullDefense(), 8);
		game.executeCommands(ck);
//		assertEquals(ReturnCom.EQUIPPED_ARMOUR, rc);
		assertEquals(8 + 4, player.getFullDefense() );
	
		
		Weapon weapon = new Weapon.Builder().name("shank").viewDescription("it can also be used as a verb").attack(3).build();
		player.addItem(weapon);
		ck = game.parseInput("equip shank");
		assertEquals( BasicTextTokenType.EQUIP, ck.getVerb().getType());
		assertEquals( "shank", ck.getFirstObject().getToken());
		assertEquals(player.getFullAttack(), 5);
		 game.executeCommands(ck);
//		assertEquals( ReturnCom.EQUIPPED_WEAPON, rc);
		assertEquals(player.getFullAttack(), 8);
		assertEquals(player.getWeaponKey(), "shank");
		
		
		ck = game.parseInput("unequip shank");
		assertEquals(BasicTextTokenType.UNEQUIP, ck.getVerb().getType() );
		assertEquals( "shank", ck.getFirstObject().getToken());
		assertFalse(player.containsItem("shank"));
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.UNEQUIPPED_WEAPON);
		assertNull(player.getWeapon());
		assertTrue(player.containsItem("shank"));
		assertNull(player.getWeaponKey());
		assertEquals(player.getFullAttack(), 5);
		
		ck = game.parseInput("UnEquiP wool");
		assertEquals(BasicTextTokenType.UNEQUIP, ck.getVerb().getType() );
		assertEquals(ck.getFirstObject().getToken(), "wool");
		assertFalse(player.containsItem("wool"));
		
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.UNEQUIPPED_ARMOUR);
		assertTrue(player.containsItem("wool"));
		assertEquals(player.getFullDefense(), 8);
	}
	@Test
	public void ItemWorldTest(){
		assertTrue(game.getCurrent().containsItem("item"));
		assertFalse(player.containsItem("item"));
		ITextTokenStream<BasicTextTokenType> ck = game.parseInput("get item");
		assertEquals(ck.getFirstObject().getToken(), "item");
		assertEquals(ck.getFirstObject().getType(), BasicTextTokenType.ITEM);
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.GET);
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.GOT_ITEM);
		assertTrue(player.containsItem("item"));
		assertFalse(game.getCurrent().containsItem("item"));
		
		ck = game.parseInput("drop item");
		assertEquals(ck.getFirstObject().getToken(), "item");
		assertEquals(ck.getVerb().getType(), BasicTextTokenType.DROP);
		game.executeCommands(ck);
//		assertEquals(rc, ReturnCom.ITEM_DROPPED);
		assertFalse(player.containsItem("item"));
		assertTrue(game.getCurrent().containsItem("item"));
	}

	private ITextParser<BasicTextTokenType> getParser(){
		if (parser == null){
			parser = new BasicTextParser<>();
			TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>(
                    BasicTextTokenType.values()).putAll(BasicTextTokenType.DIRECTION, Exit.DEFAULT_VALUES)
					.putAll(BasicTextTokenType.ITEM, "coin", "item").putAll(BasicTextTokenType.WEAPON, "shank")
					.putAll(BasicTextTokenType.ENEMY, "goblin").putAll(BasicTextTokenType.ARMOR, "wool");
			parser.setTextDictionary(dictionary);
			//TODO make this less tedious..			
		}
		return parser;
	}

}
