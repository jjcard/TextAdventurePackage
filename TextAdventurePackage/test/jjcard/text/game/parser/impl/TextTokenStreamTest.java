package jjcard.text.game.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import jjcard.text.game.parser.TextToken;

public class TextTokenStreamTest {

	@Test
	public void buildTest() {
		TextTokenStream<BasicTextTokenType> stream = new TextTokenStream.Builder<BasicTextTokenType>().build();
		assertNotNull(stream);
	}
	@Test
	public void setVerbTest(){
		TextToken<BasicTextTokenType> verb = new TextToken<BasicTextTokenType>("verbTest", "vertTest2", BasicTextTokenType.MOVE);
		TextTokenStream<BasicTextTokenType> stream = new TextTokenStream.Builder<BasicTextTokenType>().verb(verb).build();
		
		assertNotNull(stream.getVerb());
		assertEquals(verb, stream.getVerb());
	}

}
