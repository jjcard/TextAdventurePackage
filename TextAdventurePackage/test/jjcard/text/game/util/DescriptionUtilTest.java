package jjcard.text.game.util;

import static org.junit.Assert.*;

import org.junit.Test;

import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.impl.Exit;
import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Location;
import jjcard.text.game.impl.Mob;
import jjcard.text.game.util.DescriptionUtil;

public class DescriptionUtilTest {

	@Test
	public void showRoomExitsTest() {
		Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		
		Location s = new Location("Secret Tunnel", "SECRET TUNNEL! SECRET TUNNEL!");
		Exit secretExit = new Exit.Builder().hidden(true).location(s).name("DOWN").build();
		hallway.addExit(secretExit);
		
		Exit secretExit2 = new Exit.Builder().hidden(true).location(hallway).name("UP").build();
		s.addExit(secretExit2);
		String roomDescrip = DescriptionUtil.showRoom(s);
		//no non-hidden exits, should not show
		assertFalse(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		 roomDescrip = DescriptionUtil.showRoom(hallway);
		assertFalse(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		assertEquals(1, s.getExits().size());
		
		
		//non-hidden with hidden exits
		
		Location l = new Location("Testing Room 1", "The first testing room");
		Location p = new Location("Testing room a", "The primary testing room");
		hallway.addExit("NORTHISH", l);
		hallway.addExit("SOUTHERNLY", p);
		
		 roomDescrip = DescriptionUtil.showRoom(hallway);
		 assertTrue(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		 assertTrue(roomDescrip, roomDescrip.contains("NORTHISH"));
		 assertTrue(roomDescrip, roomDescrip.contains("SOUTHERNLY"));
		 assertFalse(roomDescrip, roomDescrip.contains("DOWN"));
	}
	@Test
	public void showRoomMobTest(){
		IMob mob = new Mob.Builder().name("Mecha-Goblin").hidden(true).description("It never wondered at all, just made itself Awesome").roomDescription("A globin, It's chrome body shines in the torchlight, not the most stealthy then. ").validateFields(false)
				.build();
//		imob = mob;
		IMob mob2 = new Mob.Builder().name("Flower").validateFields(false).roomDescription("A giant stands, at least partially, in the room. It's kind of hard to miss.")
				.description("A giant, able to crush mountains....he got his name when he was smaller.").build();
		
		Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		hallway.addMob(mob);
		hallway.addMob(mob2);
		
		String roomDescrip = DescriptionUtil.showRoom(hallway);
		assertFalse(roomDescrip, roomDescrip.contains("A globin, It's chrome body shines in the torchlight, not the most stealthy then. "));
		assertTrue(roomDescrip, roomDescrip.contains("A giant stands, at least partially, in the room. It's kind of hard to miss."));
		
	}
	@Test
	public void showRoomItemsTest(){
		Item item = new Item.Builder().name("basic item").info("it shows off the true potential of an item...which isn't much").roomDescription("an item on the floor, it's purpose...none.").build();
		IItem item2 = new Item.Builder().name("Even smaller item").roomDescription("Now free from the basic item, it still does nothing").hidden(true).build();
		
		Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		hallway.addItem(item);
		hallway.addItem(item2);
		
		String roomDescrip = DescriptionUtil.showRoom(hallway);
		
		assertFalse(roomDescrip, roomDescrip.contains(item2.getRoomDescription()));
		assertTrue(roomDescrip, roomDescrip.contains(item.getRoomDescription()));
	}

}
