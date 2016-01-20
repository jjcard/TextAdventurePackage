package jjcard.text.game.parser.impl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.MappedTextDefinition;
import jjcard.text.game.parser.impl.SimpleTextDefinition;
import jjcard.text.game.parser.impl.TextDictionary;

public class TextDictionaryTest {

	@Test
	public void jsonTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		TextDictionary<BasicTextTokenType> dict = new TextDictionary<>();
		dict.put("Blarg", SimpleTextDefinition.getInstance(BasicTextTokenType.TALK));
		MappedTextDefinition<BasicTextTokenType> mappedDef =  MappedTextDefinition.getInstance(BasicTextTokenType.GET);
		mappedDef.putEntry("Hot", "Cold");
		mappedDef.putEntry("Yes", "No");
		dict.put("Contradiction", mappedDef);
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, dict);
		
		TextDictionary<BasicTextTokenType> in = m.readValue(new ByteArrayInputStream(out.toByteArray()), new TypeReference<TextDictionary<BasicTextTokenType>>() {});
		assertEquals(dict, in);
		assertEquals(dict.get("Blarg"), in.get("Blarg"));

	}

}
