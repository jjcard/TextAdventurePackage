package jjcard.text.game.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jjcard.text.game.parser.TextToken;
import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.SpaceTextTokenType;

public class TextTokenTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String word = "glass-mail";
		String standardWord = "glass mail";
		TextToken<BasicTextTokenType> j = new TextToken<>(word, standardWord, BasicTextTokenType.ARMOR);
		
		BasicTextTokenType t = j.getType();
		
		assertEquals(BasicTextTokenType.ARMOR, t);
		assertEquals(word, j.getToken());
		assertEquals(standardWord, j.getStandardToken());
		assertTrue(t.isObject());
		assertFalse(t.isVerb());
	}
	@Test
	public void otherEnumTest(){
		String word = "firefly";
		String standardWord = "Serenity";
		TextToken<SpaceTextTokenType> j = new TextToken<>(word, standardWord, SpaceTextTokenType.SHIP);
		
		SpaceTextTokenType t = j.getType();
		
		assertEquals(SpaceTextTokenType.SHIP, t);
		assertEquals(word, j.getToken());
		assertEquals(standardWord, j.getStandardToken());
		assertTrue(t.isObject());
		assertFalse(t.isVerb());
	}
	@Test
	public void toStringTest() {
		TextToken<BasicTextTokenType> verb = new TextToken<>("verbTest", "vertTest2", BasicTextTokenType.MOVE);
		assertEquals("TextToken=[type=MOVE, token=verbTest, standardToken=vertTest2]", verb.toString());
	}

}
