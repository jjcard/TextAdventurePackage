package jjcard.text.game.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SingleTextDefinitionTest {

	@Test
	public void JsonTest() throws JsonParseException, JsonMappingException, IOException{
		
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		SingleTextDefinition<BasicTextTokenType> def = new SingleTextDefinition<>(b, "armor");
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, def);
		
		SingleTextDefinition<BasicTextTokenType> in = m.readValue(
				new ByteArrayInputStream(out.toByteArray()),
				new TypeReference<SingleTextDefinition<BasicTextTokenType>>() {});
		
		assertEquals(def, in);
		assertEquals(b, in.getType());
		
	}
	@Test
	public void equalsAndHashCodeTest(){
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		SingleTextDefinition<BasicTextTokenType> def = new SingleTextDefinition<>(b, "armor");
		
		assertFalse(def.equals(null));
		assertFalse(def.equals("Mecha-Goblin"));
		assertTrue(def.equals(def));
		assertTrue(def.hashCode() == def.hashCode());
		
		
		BasicTextTokenType b2 = BasicTextTokenType.ARMOR;
		SingleTextDefinition<BasicTextTokenType> def2 = new SingleTextDefinition<>(b2, "armor");
		
		assertTrue(def.equals(def2));
		assertTrue(def.hashCode() == def2.hashCode());
		
		
		SpaceTextTokenType b3 = SpaceTextTokenType.ARMOR;
		SingleTextDefinition<SpaceTextTokenType> def3 = new SingleTextDefinition<>(b3, "armor");
		
		assertFalse(def.equals(def3));
		assertFalse(def.hashCode() == def3.hashCode());
	}
}
