package jjcard.textGames.game.impl;

import static org.junit.Assert.*;
import jjcard.textGames.game.impl.Exit;
import jjcard.textGames.game.impl.Item;
import jjcard.textGames.game.impl.Location;
import jjcard.textGames.game.impl.Mob;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	private Mob mob = new Mob.MobBuilder().standardName("Gelatinous Cube").build();//.altNames(new String[] {"The bane"}).description("Run").build();
	private Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
	private Location room = new Location("A room", "no not THE room");
	private Item item = new Item.ItemBuilder().standardName("vendor trash").info("doesn't do anything").build();//.altNames(new String[] {"worthless"}).build();
	@Before
	public void setUp() throws Exception {
		mob = new Mob.MobBuilder().standardName("Gelatinous Cube").build();//.altNames(new String[] {"The bane"}).description("Run").build();
		item = new Item.ItemBuilder().standardName("vendor trash").info("doesn't do anything").build();//.altNames(new String[] {"worthless"}).build();
		hallway.addExit(Exit.NORTH.getWithLocation(room));
		room.addExit(Exit.SOUTH_BUILD.location(hallway).build());
		hallway.addItem(item);
		hallway.addMob(mob);
	}

	@Test
	public void getDirectionTest() {
		assertEquals(hallway.getExitLocation("North"), room);
//		assertEquals(hallway.getExitLocation("N"), room);
		
		assertEquals(room.getExitLocation("South"), hallway);
//		assertEquals(room.getExitLocation("S"), hallway);
		
		
		assertNull(room.getExitLocation("go North") );
	}
	
	@Test
	public void mobTest(){
		assertEquals(hallway.getMob("Gelatinous Cube"), mob);
//		assertEquals(hallway.getMob("The bane"), mob);
//		assertEquals(hallway.getMob("ThE baNe"), mob);
		
		
		assertNull(hallway.getMob("bane cube"));
		
	}
	
	@Test
	public void itemTest(){
		assertEquals(hallway.getItem("vendor trash"), item);
//		assertEquals(hallway.getItem("worthless"), item);
//		assertEquals(hallway.getItem("woRthLeSs"), item);
		
		assertNull(hallway.getItem("worthless trash"));
	}

}
