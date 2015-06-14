package jjcard.textGames.game.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationTest {
	private Mob mob = new Mob.Builder().standardName("Gelatinous Cube").build();//.altNames(new String[] {"The bane"}).description("Run").build();
	private Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
	private Location room = new Location("A room", "no not THE room");
	private Item item = new Item.Builder().standardName("vendor trash").info("doesn't do anything").build();//.altNames(new String[] {"worthless"}).build();
	@Before
	public void setUp() throws Exception {
		mob = new Mob.Builder().standardName("Gelatinous Cube").build();//.altNames(new String[] {"The bane"}).description("Run").build();
		item = new Item.Builder().standardName("vendor trash").info("doesn't do anything").build();//.altNames(new String[] {"worthless"}).build();
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
	public void getExitsDescriptionsTest(){
		Location l = new Location("Testing Room 1", "The first testing room");
		Location p = new Location("Testing room a", "The primary testing room");
		hallway.addExit("NORTHISH", l);
		hallway.addExit("SOUTHERNLY", p);
		
		Location s = new Location("Secret Tunnel", "SECRET TUNNEL! SECRET TUNNEL!");
		Exit secretExit = new Exit.Builder().hidden(true).location(s).standardName("DOWN").build();
		hallway.addExit(secretExit);
		
		String exitsDescrip = hallway.getExitsDescriptions();
		assertTrue(exitsDescrip.contains("NORTHISH"));
		assertTrue(exitsDescrip.contains("SOUTHERNLY"));
		assertTrue(exitsDescrip.contains("NORTH"));
		assertFalse(exitsDescrip.contains("DOWN"));
		assertFalse(exitsDescrip.endsWith(", "));
	}
	
	@Test
	public void itemTest(){
		assertEquals(hallway.getItem("vendor trash"), item);
//		assertEquals(hallway.getItem("worthless"), item);
//		assertEquals(hallway.getItem("woRthLeSs"), item);
		
		assertNull(hallway.getItem("worthless trash"));
	}
	@Test
	public void jsonTest() throws JsonParseException, JsonMappingException, IOException{
		String name = "fjksdafjlsd";
		String descrip = "a white walled testing facility";
		Map<String, IItem> inventory = new HashMap<>();
		inventory.put("hello", new Item.Builder().standardName("potato").build());
		Map<String, IMob> mobs = new HashMap<>();
		mobs.put("turret", new Mob.Builder().hostile(true).standardName("FrakenTurret").build());
		Location loc = new Location(name, descrip, inventory, mobs);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, loc);
		
		Location in = m.readValue(new ByteArrayInputStream(out.toByteArray()), Location.class);
		
		assertEquals(loc, in);
		assertEquals(name, in.getName());
		assertEquals(descrip, in.getDescription());
		assertEquals(mobs, in.getMobs());
		assertEquals(inventory, in.getInventory());
		
		
		
	}

}
