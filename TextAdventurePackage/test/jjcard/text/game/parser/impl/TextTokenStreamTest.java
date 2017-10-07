package jjcard.text.game.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		TextToken<BasicTextTokenType> verb = new TextToken<>("verbTest", "vertTest2", BasicTextTokenType.MOVE);
		TextTokenStream<BasicTextTokenType> stream = new TextTokenStream.Builder<BasicTextTokenType>().verb(verb).build();
		
		assertNotNull(stream.getVerb());
		assertEquals(verb, stream.getVerb());
	}
	@Test
	public void toStringTest() {
		
		TextToken<BasicTextTokenType> verb = new TextToken<>("verbTest", "vertTest2", BasicTextTokenType.MOVE);
		TextToken<BasicTextTokenType> object = new TextToken<>("itemTest", "itemTest2", BasicTextTokenType.ITEM);
		TextTokenStream<BasicTextTokenType> stream = new TextTokenStream.Builder<BasicTextTokenType>().verb(verb)
				.addObject(object).build();
		
		assertEquals("verb=TextToken=[type=MOVE, token=verbTest, standardToken=vertTest2], objects=[TextToken=[type=ITEM, token=itemTest, standardToken=itemTest2]], errors=[]", stream.toString());
	}
	@Test
	public void basicEqualsTest() {
		TextTokenStream<BasicTextTokenType> stream = new TextTokenStream.Builder<BasicTextTokenType>().build();
		
		assertFalse(stream.equals(null));
		assertFalse(stream.equals("none stream type"));
		assertTrue(stream.equals(stream));
		
		TextTokenStream<BasicTextTokenType> stream2 = new TextTokenStream.Builder<BasicTextTokenType>().build();
		assertTrue(stream.equals(stream2));
		assertTrue(stream2.equals(stream));
	}

}
