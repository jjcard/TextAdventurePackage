package jjcard.text.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.IExit;
import jjcard.text.game.IItem;
import jjcard.text.game.IMob;

public class LocationTest {
	private Mob mob;
	private Location hallway; 
	private Location room;
	private Item item;
	@Before
	public void setUp() throws Exception {
		mob = new Mob.Builder().name("Gelatinous Cube").build();
		hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		item = new Item.Builder().name("vendor trash").viewDescription("doesn't do anything").build();
		room = new Location("A room", "no not THE room");
		hallway.addExit(Exit.NORTH.getWithLocation(room));
		room.addExit(Exit.SOUTH_BUILD.location(hallway).build());
		hallway.addItem(item);
		hallway.addMob(mob);
	}
	@Test
	public void equalsAndHashcodeTest(){
		assertFalse(hallway.equals(null));
		assertFalse(hallway.equals("hallway"));
		assertTrue(hallway.equals(hallway));
		assertEquals(hallway.hashCode(), hallway.hashCode());
		
		assertFalse(hallway.equals(room));
		assertFalse(hallway.hashCode() == room.hashCode());
		Location blank = new Location("");
		Location room = new Location("A room", "no not THE room");
		Location room2 = new Location("A room", "no not THE room");
		
		assertTrue(room.equals(room2));
		assertEquals(room.hashCode(), room2.hashCode());
		assertTrue(room2.equals(room));
		assertFalse(room2.equals(blank));
		assertFalse(room.hashCode() == blank.hashCode());
		
		room.addItem(item);
		assertFalse(room.equals(room2));
		assertFalse(room2.equals(room));
		assertFalse(room.hashCode() == room2.hashCode());
		
		room2.addItem(item);
		assertTrue(room.equals(room2));
		assertEquals(room.hashCode(), room2.hashCode());
		assertTrue(room2.equals(room));
		
		room.addMob(mob);
		assertFalse(room.equals(room2));
		assertFalse(room2.equals(room));
		assertFalse(room.hashCode() == room2.hashCode());
		
		room2.addMob(mob);
		assertTrue(room.equals(room2));
		assertEquals(room.hashCode(), room2.hashCode());
		assertTrue(room2.equals(room));
		
		
		room.addExit(Exit.NORTH.getWithLocation(hallway));
		assertFalse(room.equals(room2));
		assertFalse(room2.equals(room));
		assertFalse(room.hashCode() == room2.hashCode());
		
		room2.addExit(Exit.NORTH.getWithLocation(hallway));
		assertTrue(room.equals(room2));
		assertEquals(room.hashCode(), room2.hashCode());
		assertTrue(room2.equals(room));
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
	public void getMobTest(){
		assertEquals(hallway.getMob("Gelatinous Cube"), mob);
//		assertEquals(hallway.getMob("The bane"), mob);
//		assertEquals(hallway.getMob("ThE baNe"), mob);
		
		assertNull(hallway.getMob("bane cube"));
		
	}
	@Test
	public void getExitsDescriptionsTest(){
		Location l = new Location("Testing Room 1", "The first testing room");
		Location p = new Location("Testing room a", "The primary testing room");
		hallway.addExit("NORTHISH", l);
		hallway.addExit("SOUTHERNLY", p);
		
		Location s = new Location("Secret Tunnel", "SECRET TUNNEL! SECRET TUNNEL!");
		Exit secretExit = new Exit.Builder().hidden(true).location(s).name("DOWN").build();
		hallway.addExit(secretExit);
		
		String exitsDescrip = hallway.getExitsDescriptions();
		assertTrue(exitsDescrip.contains("NORTHISH"));
		assertTrue(exitsDescrip.contains("SOUTHERNLY"));
		assertTrue(exitsDescrip.contains("NORTH"));
		assertFalse(exitsDescrip.contains("DOWN"));
		assertFalse(exitsDescrip.endsWith(", "));
		
		
		Exit secretExit2 = new Exit.Builder().hidden(true).location(hallway).name("UP").build();
		s.addExit(secretExit2);
		String roomDescrip = s.showRoom();
		//no non-hidden exits, should not show
		assertFalse(roomDescrip.contains(" The obvious exits are "));
		assertEquals(1, s.getExits().size());
	}
	
	@Test
	public void itemTest(){
		assertEquals(hallway.getItem("vendor trash"), item);
//		assertEquals(hallway.getItem("worthless"), item);
//		assertEquals(hallway.getItem("woRthLeSs"), item);
		
		assertNull(hallway.getItem("worthless trash"));
	}
	@Test
	public void jsonTest() throws Exception{
		String name = "fjksdafjlsd";
		String descrip = "a white walled testing facility";
		Map<String, IItem> inventory = new HashMap<>();
		inventory.put("hello", new Item.Builder().name("potato").build());
		Map<String, IMob> mobs = new HashMap<>();
		mobs.put("turret", new Mob.Builder().hostile(true).name("FrakenTurret").build());
		Location loc = new Location(name, descrip, inventory, mobs);
		assertEquals(inventory, loc.getInventory());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, loc);
		
		Location in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Location.class);
		
		
		assertEquals(name, in.getName());
		assertEquals(descrip, in.getDescription());
		assertEquals(mobs, in.getMobs());
		assertEquals(inventory, in.getInventory());
		assertEquals(loc, in);
	}
	@Test
	public void removeExitTest(){
		assertFalse(room.getExits().isEmpty());
		IExit exit = room.removeExit("South");
		assertNotNull(exit);
		assertEquals(hallway, exit.getLocation());
		assertTrue(room.getExits().isEmpty());
	}
	@Test
	public void removeMobTest(){
		assertFalse(hallway.getMobs().isEmpty());
		IMob actMob = hallway.removeMob("Gelatinous Cube");
		assertNotNull(actMob);
		assertEquals(mob, actMob);
		assertTrue(hallway.getMobs().isEmpty());
	}

}
