package jjcard.text.game.parser.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextDictionaryTest {

	@Test
	public void jsonTest() throws IOException {
		
		TextDictionary<BasicTextTokenType> dict = new TextDictionary<>();
		dict.put("Blarg", SimpleTextDefinition.getInstance(BasicTextTokenType.TALK));
		MappedTextDefinition<BasicTextTokenType> mappedDef =  MappedTextDefinition.getInstance(BasicTextTokenType.GET);
		mappedDef.putEntry("Hot", "Cold");
		mappedDef.putEntry("Yes", "No");
		dict.put("Contradiction", mappedDef);
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectMapper m = new ObjectMapper();
		m.writeValue(out, dict);
		
		TextDictionary<BasicTextTokenType> in = m.readValue(new ByteArrayInputStream(out.toByteArray()), new TypeReference<>() {
		});
		assertEquals(dict, in);
		assertEquals(dict.get("Blarg"), in.get("Blarg"));

	}

}
