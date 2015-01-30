package jjcard.textGames.game.parser.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleTextDefinitionTest {

	@Test
	public void JsonTest() throws JsonParseException, JsonMappingException, IOException{
		
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		SimpleTextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<BasicTextTokenType>(b);

		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, def);
		
		SimpleTextDefinition<BasicTextTokenType> in = m.readValue(
				new ByteArrayInputStream(out.toByteArray()),
				new TypeReference<SimpleTextDefinition<BasicTextTokenType>>() {});
		
		assertEquals(def, in);
		assertEquals(b, in.getType());
		
	}

}
