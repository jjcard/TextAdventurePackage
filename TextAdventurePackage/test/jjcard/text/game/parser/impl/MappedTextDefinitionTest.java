package jjcard.text.game.parser.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.MappedTextDefinition;

public class MappedTextDefinitionTest {

	@Test
	public void JsonTest() throws JsonParseException, JsonMappingException, IOException{
		
		BasicTextTokenType b = BasicTextTokenType.ARMOR;
		Map<String, String> standardizeMap = new HashMap<String, String>();		
		String keyIn = "keyIn";
		String keyOut = "keyOut";
		standardizeMap.put(keyIn, keyOut);
		MappedTextDefinition<BasicTextTokenType> def = new MappedTextDefinition<BasicTextTokenType>(b, standardizeMap);

		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, def);
		
		MappedTextDefinition<BasicTextTokenType> in = m.readValue(
				new ByteArrayInputStream(out.toByteArray()),
				new TypeReference<MappedTextDefinition<BasicTextTokenType>>() {});
		
		assertEquals(def, in);
		assertEquals(b, in.getType());
		assertEquals(keyOut, in.getStandardToken(keyIn));
		
	}

}
