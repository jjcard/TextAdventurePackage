package jjcard.textGames.test;

import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.impl.BasicTextParser;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicTextParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		BasicTextParser parser = new BasicTextParser();
		Assert.assertNotNull(parser);
	}
	
	@Test
	public void basicParseTest(){
		String input = "Shally sells Sea Shore";
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>();
		
		Assert.assertNotNull(parser);
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(input);
		Assert.assertNotNull(stream);
	}
	@Test
	public void testSplitPattern(){
		String text1 = "Sally sells Sea Shells by the sea shore.";
		String[] text1Expected = new String[]{"Sally", "sells", "Sea", "Shells", "by", "the", "sea", "shore."};
		String text2 = "And then I said \" Poker? I hardly knew her!\"";
		String[] text2Expected = new String[]{"And", "then", "I", "said", "\" Poker? I hardly knew her!\""};
		
		String[] result1 = BasicTextParser.splitPattern.split(text1);
		
		Assert.assertArrayEquals(text1Expected, result1);
		
		String[] result2 = BasicTextParser.splitPattern.split(text2);
		
		Assert.assertArrayEquals(text2Expected, result2);
	}

}
