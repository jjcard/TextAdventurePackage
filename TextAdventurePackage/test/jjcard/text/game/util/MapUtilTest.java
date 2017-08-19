package jjcard.text.game.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapUtilTest {

	@Test
	public void getKeysAsStringCharTest() {
		Map<String, Object> map = null;
		String actual = MapUtil.getKeysAsString(map, ".");
		assertNotNull(actual);
		assertTrue(actual.isEmpty());
		
		map = new TreeMap<>();
		actual = MapUtil.getKeysAsString(map, ".");
		assertNotNull(actual);
		assertTrue(actual.isEmpty());
		
		
		map.put("Key1", "value1");
		actual = MapUtil.getKeysAsString(map, ".");
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(actual, "Key1");
		
		map.put("Key2", "value2");
		actual = MapUtil.getKeysAsString(map, ".");
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(actual, "Key1.Key2");
	}
	@Test
	public void getMapOrNewTest(){
		HashMap<String, Integer> map1 = new HashMap<>();
		
		assertSame(map1, MapUtil.getMapOrNew(map1));
		
		assertNotNull(MapUtil.getMapOrNew(null));
	}
	@Test
	public void getMapOrNewConstructorTest(){
		TreeMap<String, Integer> map1 = new TreeMap<>();
		
		assertSame(map1, MapUtil.getMapOrNew(map1, TreeMap::new));
		
		Map<String, Integer> mapOut = MapUtil.getMapOrNew(null, TreeMap::new);
		assertNotNull(mapOut);
		assertTrue(mapOut instanceof TreeMap);
	}
	@Test
	public void isEmptyTest(){
		assertTrue(MapUtil.isEmpty(null));
		assertTrue(MapUtil.isEmpty(new HashMap<>()));
		assertTrue(MapUtil.isEmpty(new TreeMap<>()));
		
		HashMap<String, Integer> map1 = new HashMap<>();
		assertTrue(MapUtil.isEmpty(map1));
		map1.put("Test", 1);
		assertFalse(MapUtil.isEmpty(map1));
	}
	@Test
	public void isNotEmptyTest(){
		assertFalse(MapUtil.isNotEmpty(null));
		assertFalse(MapUtil.isNotEmpty(new HashMap<>()));
		assertFalse(MapUtil.isNotEmpty(new TreeMap<>()));
		
		HashMap<String, Integer> map1 = new HashMap<>();
		assertFalse(MapUtil.isNotEmpty(map1));
		map1.put("Test", 1);
		assertTrue(MapUtil.isNotEmpty(map1));
	}
}
