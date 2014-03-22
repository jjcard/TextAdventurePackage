package jjcard.textGames.test;

import static org.junit.Assert.*;
import jjcard.textGames.game.impl.Exit;
import jjcard.textGames.game.impl.Item;
import jjcard.textGames.game.impl.Location;
import jjcard.textGames.game.impl.Mob;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	private Mob mob = new Mob("Gelatinous Cube");
	private Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
	private Location room = new Location("A room", "no not THE room");
	private Item item = new Item("vendor trash", "doesn't do anything");
	@Before
	public void setUp() throws Exception {
		mob.setDescription("Run");
		item.setAltNames(new String[] {"worthless"});
		mob.setAltNames(new String[] {"The bane"});
		hallway.addExit(Exit.NORTH, room);
		room.addExit(Exit.SOUTH, hallway);
		hallway.addItem(item);
		hallway.addMob(mob);
	}

	@Test
	public void getDirectionTest() {
		assertEquals(hallway.getExitLocation("North"), room);
		assertEquals(hallway.getExitLocation("N"), room);
		
		assertEquals(room.getExitLocation("South"), hallway);
		assertEquals(room.getExitLocation("S"), hallway);
		
		
		assertNull(room.getExitLocation("go North") );
	}
	
	@Test
	public void mobTest(){
		assertEquals(hallway.getMob("Gelatinous Cube"), mob);
		assertEquals(hallway.getMob("The bane"), mob);
		assertEquals(hallway.getMob("ThE baNe"), mob);
		
		
		assertNull(hallway.getMob("bane cube"));
		
	}
	
	@Test
	public void itemTest(){
		assertEquals(hallway.getItem("vendor trash"), item);
		assertEquals(hallway.getItem("worthless"), item);
		assertEquals(hallway.getItem("woRthLeSs"), item);
		
		assertNull(hallway.getItem("worthless trash"));
	}

}
