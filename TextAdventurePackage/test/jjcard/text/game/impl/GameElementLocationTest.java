package jjcard.text.game.impl;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.IExit;
import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.impl.Exit;
import jjcard.text.game.impl.GameElementLocation;
import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Location;
import jjcard.text.game.impl.Mob;

public class GameElementLocationTest {

	private Mob mob;
	private GameElementLocation hallway; 
	private GameElementLocation room;
	private Item item;
	@Before
	public void setUp() throws Exception {
		mob = new Mob.Builder().name("Gelatinous Cube").build();
		hallway = new GameElementLocation.Builder().name("Hallway").roomDescription("It's a hallway. What more do you want.").build();
		item = new Item.Builder().name("vendor trash").viewDescription("doesn't do anything").build();
		room = new GameElementLocation.Builder().name("A room").roomDescription( "no not THE room").build();
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
		GameElementLocation l = new GameElementLocation.Builder().name("Testing Room 1").roomDescription("The first testing room").build();
		GameElementLocation p = new GameElementLocation.Builder().name("Testing room a").roomDescription("The primary testing room").build();
		hallway.addExit("NORTHISH", l);
		hallway.addExit("SOUTHERNLY", p);
		
		GameElementLocation s = new GameElementLocation.Builder().name("Secret Tunnel").roomDescription("SECRET TUNNEL! SECRET TUNNEL!").build();
		Exit secretExit = new Exit.Builder().hidden(true).location(s).name("DOWN").build();
		hallway.addExit(secretExit);
		
		String exitsDescrip = hallway.getExitsDescriptions();
		assertTrue(exitsDescrip, exitsDescrip.contains("NORTHISH"));
		assertTrue(exitsDescrip, exitsDescrip.contains("SOUTHERNLY"));
		assertTrue(exitsDescrip, exitsDescrip.contains("NORTH"));
		assertFalse(exitsDescrip, exitsDescrip.contains("DOWN"));
		assertFalse(exitsDescrip, exitsDescrip.endsWith(", "));
		
		
		Exit secretExit2 = new Exit.Builder().hidden(true).location(hallway).name("UP").build();
		s.addExit(secretExit2);
		String roomDescrip = s.showRoom();
		//no non-hidden exits, should not show
		assertFalse(roomDescrip, roomDescrip.contains(" The obvious exits are "));
		assertEquals(1, s.getExits().size());
	}
	
	@Test
	public void itemTest(){
		assertEquals(hallway.getItem("vendor trash"), item);
		
		assertNull(hallway.getItem("worthless trash"));
	}
	@Test
	public void jsonTest() throws JsonParseException, JsonMappingException, IOException{
		String name = "fjksdafjlsd";
		String descrip = "a white walled testing facility";
		Map<String, IItem> inventory = new HashMap<>();
		inventory.put("hello", new Item.Builder().name("potato").build());
		Map<String, IMob> mobs = new HashMap<>();
		mobs.put("turret", new Mob.Builder().hostile(true).name("FrakenTurret").build());
		GameElementLocation loc = new GameElementLocation.Builder().name(name).roomDescription(descrip).inventory(inventory).mobs(mobs).build();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, loc);
		
		GameElementLocation in = m.readValue(new ByteArrayInputStream(out.toByteArray()), GameElementLocation.class);
		
		assertEquals(loc, in);
		assertEquals(name, in.getName());
		assertEquals(descrip, in.getDescription());
		assertEquals(mobs, in.getMobs());
		assertEquals(inventory, in.getInventory());
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
