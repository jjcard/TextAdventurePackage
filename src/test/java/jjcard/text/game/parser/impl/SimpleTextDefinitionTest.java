package jjcard.text.game.parser.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.SimpleTextDefinition;

public class SimpleTextDefinitionTest {

	@Test
	public void JsonTest() throws IOException{
		
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		SimpleTextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(b);

		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, def);
		
		SimpleTextDefinition<BasicTextTokenType> in = m.readValue(
				new ByteArrayInputStream(out.toByteArray()),
				new TypeReference<SimpleTextDefinition<BasicTextTokenType>>() {});
		
		assertEquals(def, in);
		assertEquals(b, in.getType());
		
	}
	@Test
	public void equalsAndHashCodeTest(){
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		SimpleTextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(b);
		
		assertFalse(def.equals(null));
		assertFalse(def.equals("Mecha-Goblin"));
		assertTrue(def.equals(def));
		assertTrue(def.hashCode() == def.hashCode());
		
		
		BasicTextTokenType b2 = BasicTextTokenType.ARMOR;
		SimpleTextDefinition<BasicTextTokenType> def2 = new SimpleTextDefinition<>(b2);
		
		assertTrue(def.equals(def2));
		assertTrue(def.hashCode() == def2.hashCode());
		
		
		SpaceTextTokenType b3 = SpaceTextTokenType.ARMOR;
		SimpleTextDefinition<SpaceTextTokenType> def3 = new SimpleTextDefinition<>(b3);
		
		assertFalse(def.equals(def3));
		assertFalse(def.hashCode() == def3.hashCode());
	}

}