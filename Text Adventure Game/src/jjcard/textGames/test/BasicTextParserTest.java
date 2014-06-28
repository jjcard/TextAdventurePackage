package jjcard.textGames.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jjcard.textGames.game.parser.ITextDictionary;
import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.impl.BasicTextParser;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;
import jjcard.textGames.game.parser.impl.TextDictionary;

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
	@Test
	public void testParsing1(){
		
		ITextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		dictionary.put("go", BasicTextTokenType.MOVE);
		dictionary.put("bread", BasicTextTokenType.ITEM);
		String text = "Go to brEaD";
		
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(text);
		
		assertNotNull(stream);
		
		assertNotNull(stream.getVerb());
		assertEquals("Go", stream.getVerb().getToken());
		assertEquals(BasicTextTokenType.MOVE, stream.getVerb().getType());
		assertNotNull(stream.getFirstObject());
		assertEquals("brEaD", stream.getFirstObject().getToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getFirstObject().getType());
	}

}
