package jjcard.text.game.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		
		map = new TreeMap<String, Object>();
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

}
