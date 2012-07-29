package jjcard.textGames.test;

import static org.junit.Assert.*;
import jjcard.textGames.game.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {
	World world;
	Player player;
	Location local;
	Location hallway;

	@Test
	public void MovingTest() {
		assertEquals(player, world.getPlayer());
		assertEquals(local, world.getCurrent());
		world.goDirection("NORTH");
		assertEquals(hallway, world.getCurrent());
		world.goDirection(Location.SOUTH);
		assertEquals("entry room", world.getCurrent().getName());
		world.goDirection(Location.NORTH);
		assertEquals("hallway", world.getCurrent().getName());
		
	}
	@Test
	public void ItemWorldTest(){
		assertTrue(world.getCurrent().containsItem("item"));
		assertFalse(player.containsItem("item"));
		CommandAndKey ck = world.praseInput("get item");
		assertEquals(ck.getKey(), "item");
		assertEquals(ck.getCommand(), Commands.GET);
		ReturnCom rc = world.basicOperations(ck);
		assertEquals(rc, ReturnCom.GOT_ITEM);
		assertTrue(player.containsItem("item"));
		assertFalse(world.getCurrent().containsItem("item"));
		
		ck = world.praseInput("drop item");
		assertEquals(ck.getKey(), "item");
		assertEquals(ck.getCommand(), Commands.DROP);
		rc = world.basicOperations(ck);
		assertEquals(rc, ReturnCom.ITEM_DROPPED);
		assertFalse(player.containsItem("item"));
		assertTrue(world.getCurrent().containsItem("item"));
	}
	@Before
	public void setUp(){
		
		 player = new Player("jjcard");
		 local = new Location("entry room", "A barren room");
		 Item item = new Item("item");
		 local.addItem("item", item);
		 hallway = new Location("hallway","a long hallway with one torch");
		local.addExit("NORTH", hallway);
		hallway.addExit(Location.SOUTH, local);
		 world = new World(local, player);
		
	}

}
