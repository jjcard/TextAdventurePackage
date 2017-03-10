package jjcard.text.game.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IListMapTest {

	@Test
	public void addTest_listHashMap() {
		IListMap<String, String> map = IListMap.newListHashMap();
		assertNull(map.get("5252"));
		
		List<String> out = map.add("5252", "testValue");
		assertNotNull(out);
		assertEquals(1, out.size());
		assertEquals("testValue", out.get(0));
		
		assertSame(out, map.get("5252"));
	}
	
	@Test
	public void addAllTest_listHashMap() {
		IListMap<String, String> map = IListMap.newListHashMap();
		assertNull(map.get("5252"));
		
		
		List<String> inList = new ArrayList<String>();
		inList.add("testValue");
		inList.add("testValue2");
		List<String> out = map.addAll("5252", inList);
		assertNotNull(out);
		assertEquals(2, out.size());
		assertEquals(inList, out);
		assertNotSame(inList, out);
	}
	@Test
	public void addAllTest_alreadyExists_listHashMap() {
		IListMap<String, String> map = IListMap.newListHashMap();
		assertNull(map.get("5252"));
		
		List<String> alreadyList = new ArrayList<String>();
		alreadyList.add("testValue3");
		alreadyList.add("testValue4");
		map.put("5252", alreadyList);
		
		List<String> inList = new ArrayList<String>();
		inList.add("testValue");
		inList.add("testValue2");
		List<String> out = map.addAll("5252", inList);
		assertNotNull(out);
		assertEquals(4, out.size());
		assertTrue(out.containsAll(inList));
		assertTrue(out.containsAll(alreadyList));
	}
	@Test
	public void addTest_listTreeMap() {
		IListMap<String, String> map = IListMap.newListTreeMap();
		assertNull(map.get("5252"));
		
		List<String> out = map.add("5252", "testValue");
		assertNotNull(out);
		assertEquals(1, out.size());
		assertEquals("testValue", out.get(0));
		
		assertSame(out, map.get("5252"));
	}
}
