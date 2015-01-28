package jjcard.textGames.game.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import jjcard.textGames.game.parser.ITextDefinition;
import jjcard.textGames.game.parser.ITextDictionary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicTextParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("rawtypes")
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
		String[] text1Expected = new String[]{"Sally", "sells", "Sea", "Shells", "by", "the", "sea", "shore"};
		String text2 = "And then I said \" Poker? I hardly knew her!\"";
		String[] text2Expected = new String[]{"And", "then", "I", "said", "\" Poker? I hardly knew her!\""};
		
		String[] result1 = BasicTextParser.SPLIT_PATTERN.split(text1);
		
		Assert.assertArrayEquals(text1Expected, result1);
		
		String[] result2 = BasicTextParser.SPLIT_PATTERN.split(text2);
		
		Assert.assertArrayEquals(text2Expected, result2);
	}
	@Test
	public void testParsing1(){
		
		ITextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		dictionary.put("go", SimpleTextDefinition.getInstance(BasicTextTokenType.MOVE));
		dictionary.put("bread", SimpleTextDefinition.getInstance(BasicTextTokenType.ITEM));
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
		assertEquals(1, stream.getObjects().size());
	}
	@Test
	public void testPatternParsing(){
		ITextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<BasicTextTokenType>(BasicTextTokenType.TALK);
		dictionary.put("say", def);
		String text = "And then I say \"I really hope this works\"";
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);
		parser.addTextTokenTypePattern("\".*\"", SimpleTextDefinition.getInstance(BasicTextTokenType.WORDS));
		
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(text);
		

		assertFalse(stream.hasErrors());
		
		assertNotNull(stream.getFirstObject());
		assertEquals( "\"I really hope this works\"", stream.getFirstObject().getToken());
		assertEquals( BasicTextTokenType.WORDS, stream.getFirstObject().getType());
		assertNotNull(stream.getVerb());
		assertEquals("say", stream.getVerb().getToken());
		assertEquals(BasicTextTokenType.TALK, stream.getVerb().getType());
		
	}
	@Test
	public void testListPatternParsing(){
		TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<BasicTextTokenType>(BasicTextTokenType.GET);
		ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<BasicTextTokenType>(BasicTextTokenType.ITEM);
		dictionary.put("want", def);
		dictionary.putAll(item, "cyberpunk", "parser", "monitor");
		String text = "For christmas I want a parser, monitor, and cyberpunk";
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);
		
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(text);
		

		assertFalse(stream.hasErrors());
		assertNotNull(stream.getFirstObject());
		assertEquals(3, stream.getObjects().size());
		
		assertEquals("parser", stream.getObjects().get(0).getToken());
		assertEquals("parser", stream.getObjects().get(0).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(0).getType());
		
		
		assertEquals("monitor", stream.getObjects().get(1).getToken());
		assertEquals("monitor", stream.getObjects().get(1).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(1).getType());
		
		assertEquals("cyberpunk", stream.getObjects().get(2).getToken());
		assertEquals("cyberpunk", stream.getObjects().get(2).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(2).getType());
	}

}
