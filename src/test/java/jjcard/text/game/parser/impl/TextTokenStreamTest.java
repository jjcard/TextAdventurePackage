package jjcard.text.game.parser.impl;

import org.junit.jupiter.api.Test;

import jjcard.text.game.parser.TextToken;

import static org.junit.jupiter.api.Assertions.*;

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

        assertNotEquals(null, stream);
        assertNotEquals("none stream type", stream);
        assertEquals(stream, stream);
		
		TextTokenStream<BasicTextTokenType> stream2 = new TextTokenStream.Builder<BasicTextTokenType>().build();
        assertEquals(stream, stream2);
        assertEquals(stream2, stream);
	}

}
