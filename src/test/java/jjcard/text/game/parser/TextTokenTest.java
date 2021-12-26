package jjcard.text.game.parser;

import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.parser.impl.SpaceTextTokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextTokenTest {

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
