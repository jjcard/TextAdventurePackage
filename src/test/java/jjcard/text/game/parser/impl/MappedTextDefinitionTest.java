package jjcard.text.game.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MappedTextDefinitionTest {

	@Test
	public void JsonTest() throws IOException{
		
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		Map<String, String> standardizeMap = new HashMap<>();
		String keyIn = "keyIn";
		String keyOut = "keyOut";
		standardizeMap.put(keyIn, keyOut);
		MappedTextDefinition<BasicTextTokenType> def = new MappedTextDefinition<>(b, standardizeMap);

		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, def);
		
		MappedTextDefinition<BasicTextTokenType> in = m.readValue(
				new ByteArrayInputStream(out.toByteArray()),
				new TypeReference<>() {
				});
		
		assertEquals(def, in);
		assertEquals(b, in.getType());
		assertEquals(keyOut, in.getStandardToken(keyIn));
		
	}
	@Test
	public void equalsAndHashCodeTest(){
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		Map<String, String> standardizeMap = new HashMap<>();
		String keyIn = "keyIn";
		String keyOut = "keyOut";
		standardizeMap.put(keyIn, keyOut);
		MappedTextDefinition<BasicTextTokenType> def = new MappedTextDefinition<>(b, standardizeMap);
		
		assertFalse(def.equals(null));
		assertFalse(def.equals("Mecha-Goblin"));
		assertTrue(def.equals(def));
        assertEquals(def.hashCode(), def.hashCode());
		
		
		BasicTextTokenType b2 = BasicTextTokenType.ARMOR;
		Map<String, String> standardizeMap2 = new HashMap<>();
		String keyIn2 = "keyIn";
		String keyOut2 = "keyOut";
		standardizeMap2.put(keyIn2, keyOut2);
		MappedTextDefinition<BasicTextTokenType> def2 = new MappedTextDefinition<>(b2, standardizeMap2);
		
		assertTrue(def.equals(def2));
        assertEquals(def.hashCode(), def2.hashCode());
		
		
		SpaceTextTokenType b3 = SpaceTextTokenType.ARMOR;
		Map<String, String> standardizeMap3 = new HashMap<>();
		String keyIn3 = "keyIn";
		String keyOut3 = "keyOut";
		standardizeMap3.put(keyIn3, keyOut3);
		MappedTextDefinition<SpaceTextTokenType> def3 = new MappedTextDefinition<>(b3, standardizeMap3);
		
		assertFalse(def.equals(def3));
        assertNotEquals(def.hashCode(), def3.hashCode());
	}

}
