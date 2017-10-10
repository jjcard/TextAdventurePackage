package jjcard.text.game.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import jjcard.text.game.ConcealableGameElement;
import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.impl.Exit;
import jjcard.text.game.impl.Item;
import jjcard.text.game.impl.Location;
import jjcard.text.game.impl.Mob;

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
		// no non-hidden exits, should not show
		assertFalse(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		roomDescrip = DescriptionUtil.showRoom(hallway);
		assertFalse(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		assertEquals(1, s.getExits().size());

		// non-hidden with hidden exits

		Location l = new Location("Testing Room 1", "The first testing room");
		Location p = new Location("Testing room a", "The primary testing room");
		hallway.addExit("NORTHISH", l);
		hallway.addExit("SOUTHERNLY", p);

		roomDescrip = DescriptionUtil.showRoom(hallway);
		assertTrue(roomDescrip, roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		assertTrue(roomDescrip, roomDescrip.contains("NORTHISH"));
		assertTrue(roomDescrip, roomDescrip.contains("SOUTHERNLY"));
		assertFalse(roomDescrip, roomDescrip.contains("DOWN"));
		
		
		roomDescrip = DescriptionUtil.showRoom(hallway, null);
		assertFalse(roomDescrip.contains(DescriptionUtil.DEFAULT_EXIT_START));
		assertTrue(roomDescrip, roomDescrip.contains("NORTHISH"));
		assertTrue(roomDescrip, roomDescrip.contains("SOUTHERNLY"));
		assertFalse(roomDescrip, roomDescrip.contains("DOWN"));
		assertFalse(roomDescrip.contains("null"));
	}

	@Test
	public void showRoomMobTest() {
		IMob mob = new Mob.Builder().name("Mecha-Goblin").hidden(true)
				.viewDescription("It never wondered at all, just made itself Awesome")
				.roomDescription("A globin, It's chrome body shines in the torchlight, not the most stealthy then. ")
				.validateFields(false).build();
		IMob mob2 = new Mob.Builder().name("Flower").validateFields(false)
				.roomDescription("A giant stands, at least partially, in the room. It's kind of hard to miss.")
				.viewDescription("A giant, able to crush mountains....he got his name when he was smaller.").build();

		Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		hallway.addMob(mob);
		hallway.addMob(mob2);

		String roomDescrip = DescriptionUtil.showRoom(hallway);
		assertFalse(roomDescrip, roomDescrip
				.contains("A globin, It's chrome body shines in the torchlight, not the most stealthy then. "));
		assertTrue(roomDescrip,
				roomDescrip.contains("A giant stands, at least partially, in the room. It's kind of hard to miss."));

	}

	@Test
	public void showRoomItemsTest() {
		Item item = new Item.Builder().name("basic item")
				.viewDescription("it shows off the true potential of an item...which isn't much")
				.roomDescription("an item on the floor, it's purpose...none.").build();
		IItem item2 = new Item.Builder().name("Even smaller item")
				.roomDescription("Now free from the basic item, it still does nothing").hidden(true).build();

		Location hallway = new Location("Hallway", "It's a hallway. What more do you want.");
		hallway.addItem(item);
		hallway.addItem(item2);

		String roomDescrip = DescriptionUtil.showRoom(hallway);

		assertFalse(roomDescrip, roomDescrip.contains(item2.getRoomDescription()));
		assertTrue(roomDescrip, roomDescrip.contains(item.getRoomDescription()));
	}
	@Test
	public void getConcealableNamesCollectionTest(){
		List<ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new LinkedList<>();//empty
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertTrue(result.isEmpty());
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		
		elements.clear();
		elements.add(new Item.Builder().hidden(true).name("TestItem2").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem2", result);
		
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem2, TestItem1", result);
	}
	@Test
	public void getConcealableNamesMapTest(){
		Map<String, ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new TreeMap<>();//empty
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertTrue(result.isEmpty());
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		
		elements.clear();
		elements.put("TestItem2", new Item.Builder().hidden(true).name("TestItem2").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem2", result);
		
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNames(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1", result);
		result = DescriptionUtil.getConcealableNames(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1, TestItem2", result);
	}
	@Test
	public void getConcealableNamesListCollectionTest(){
		List<ConcealableGameElement> elements = null;//null check
		List<String> result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new LinkedList<>();//empty
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertTrue(result.isEmpty());
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		
		elements.clear();
		elements.add(new Item.Builder().hidden(true).name("TestItem2").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem2", result.get(0));
		
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
		assertEquals("TestItem2", result.get(0));
		assertEquals("TestItem1", result.get(1));
	}
	@Test
	public void getConcealableNamesListMapTest(){
		Map<String, ConcealableGameElement> elements = null;//null check
		List<String> result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new TreeMap<>();//empty
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertTrue(result.isEmpty());
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		
		elements.clear();
		elements.put("TestItem2", new Item.Builder().hidden(true).name("TestItem2").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem2", result.get(0));
		
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").build());
		result = DescriptionUtil.getConcealableNamesList(elements, true);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("TestItem1", result.get(0));
		result = DescriptionUtil.getConcealableNamesList(elements, false);
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
		assertEquals("TestItem1", result.get(0));
		assertEquals("TestItem2", result.get(1));
	}
	@Test
	public void getGameElementDescriptionsCollectionTest(){
		List<ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertTrue(result.isEmpty());
		
		elements = new LinkedList<>();//empty
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertTrue(result.isEmpty());
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestItem1Descrip1").build());
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1Descrip1", result);
		
		elements.add(new Item.Builder().hidden(true).name("TestItem2").roomDescription("TestItem1Descrip2").build());
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1Descrip1, TestItem1Descrip2", result);
	}
	@Test
	public void getGameElementRoomDescriptionsMapTest(){
		Map<String, ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertTrue(result.isEmpty());
		
		elements = new TreeMap<>();//empty
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertTrue(result.isEmpty());
		
		IItem item = new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestItem1Descrip1").build();
		elements.put(item.getName(), item);
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1Descrip1", result);
		IItem item2 = new Item.Builder().hidden(true).name("TestItem2").roomDescription("TestItem1Descrip2").build();
		elements.put(item2.getName(), item2);
		result = DescriptionUtil.getGameElementRoomDescriptions(elements);
		assertFalse(result.isEmpty());
		assertEquals("TestItem1Descrip1, TestItem1Descrip2", result);
	}

	@Test
	public void getConcealableRoomDescriptionCollectionTest(){
		List<ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new LinkedList<>();//empty
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertTrue(result.isEmpty());
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestDescrip1").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		
		elements.clear();
		elements.add(new Item.Builder().hidden(true).name("TestItem2").roomDescription("TestDescrip2").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip2", result);
		
		
		elements.add(new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestDescrip1").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip2, TestDescrip1", result);
	}
	@Test
	public void getConcealableDescriptionMapTest(){
		Map<String, ConcealableGameElement> elements = null;//null check
		String result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertTrue(result.isEmpty());
		
		elements = new TreeMap<>();//empty
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertTrue(result.isEmpty());
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestDescrip1").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		
		elements.clear();
		elements.put("TestItem2", new Item.Builder().hidden(true).name("TestItem2").roomDescription("TestDescrip2").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertTrue(result.isEmpty());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip2", result);
		
		
		elements.put("TestItem1", new Item.Builder().hidden(false).name("TestItem1").roomDescription("TestDescrip1").build());
		result = DescriptionUtil.getConceableRoomDescriptions(elements, true);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1", result);
		result = DescriptionUtil.getConceableRoomDescriptions(elements, false);
		assertFalse(result.isEmpty());
		assertEquals("TestDescrip1, TestDescrip2", result);
	}
}
