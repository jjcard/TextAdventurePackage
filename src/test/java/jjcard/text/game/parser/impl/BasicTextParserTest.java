package jjcard.text.game.parser.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jjcard.text.game.parser.ITextDefinition;
import jjcard.text.game.parser.ITextDictionary;

public class BasicTextParserTest {


	@SuppressWarnings("rawtypes")
	@Test
	public void test() {
		BasicTextParser parser = new BasicTextParser();
		assertNotNull(parser);
	}
	
	@Test
	public void basicParseTest(){
		String input = "Shally sells Sea Shore";
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>();
		
		assertNotNull(parser);
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(input);
		assertNotNull(stream);
	}
	@Test
	public void testSplitPattern(){
		String text1 = "Sally sells Sea Shells by the sea shore.";
		String[] text1Expected = new String[]{"Sally", "sells", "Sea", "Shells", "by", "the", "sea", "shore"};
		String text2 = "And then I said \" Poker? I hardly knew her!\"";
		String[] text2Expected = new String[]{"And", "then", "I", "said", "\" Poker? I hardly knew her!\""};
		
		String[] result1 = BasicTextParser.DEFAULT_SPLIT_PATTERN.split(text1);
		
		assertArrayEquals(text1Expected, result1);
		
		String[] result2 = BasicTextParser.DEFAULT_SPLIT_PATTERN.split(text2);
		
		assertArrayEquals(text2Expected, result2);
	}
	@Test
	public void testSplitPattern2(){
		String text1 = "Sally sells Sea Shells by the sea shore.";
		String[] text1Expected = new String[]{"Sally", "sells", "Sea", "Shells", "by", "the", "sea", "shore"};
		String text2 = "And then I said \" Poker? I hardly knew her!\"";
		String[] text2Expected = new String[]{"And", "then", "I", "said", "\" Poker? I hardly knew her!\""};
		
		String[] result1 = new BasicTextParser<>(BasicTextParser.getDeliminators(" ", ",", ".")).splitText(text1);
		
		assertArrayEquals(text1Expected, result1);
		
		String[] result2 =  new BasicTextParser<>(BasicTextParser.getDeliminators(" ", ",", ".")).splitText(text2);
		
		assertArrayEquals(text2Expected, result2);
	}
	@Test
	public void testSplitPattern3(){
		String text1 = "Sally sells Sea Shells by the sea shore.";
		String[] text1Expected = new String[]{"Sally", "sells", "Sea", "Shells", "by", "the", "sea", "shore"};
		String text2 = "And then I said 'Poker? I hardly knew her!'";
		String[] text2Expected = new String[]{"And", "then", "I", "said", "'Poker", "I",  "hardly",  "knew", "her", "'"};
		
		String[] result1 = new BasicTextParser<>(BasicTextParser.getDeliminators(" ", ",", ".", "!", "?")).splitText(text1);
		
		assertArrayEquals(text1Expected, result1);
		
		String[] result2 =  new BasicTextParser<>(BasicTextParser.getDeliminators(" ", ",", ".", "!", "?")).splitText(text2);
		
		assertArrayEquals(text2Expected, result2, Arrays.toString(result2));
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
		assertEquals(2, stream.getOrderedStream().size());
//		System.out.println(stream.getOrderedStream());
		assertEquals("Go", stream.getOrderedStream().get(0).getToken());
		assertEquals("brEaD", stream.getOrderedStream().get(1).getToken());
	}
	@Test
	public void testPatternParsing(){
		ITextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.TALK);
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
		assertEquals("say", stream.getOrderedStream().get(0).getToken());
		assertEquals( "\"I really hope this works\"", stream.getOrderedStream().get(1).getToken());
		
	}
	@Test
	public void testListPatternParsing(){
		TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.GET);
		ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<>(BasicTextTokenType.ITEM);
		dictionary.put("want", def);
		dictionary.putAll(item, "cyberpunk", "parser", "monitor");
		String text = "For christmas I want a parser, monitor, and cyberpunk.";
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
	/**
	 * Test with using custom deliminators that do not include commas
	 */
	@Test
	public void testListPatternParsing_2(){
		TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.GET);
		ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<>(BasicTextTokenType.ITEM);
		dictionary.put("want", def);
		dictionary.putAll(item, "cyberpunk", "parser,", "monitor,");
		String text = "For christmas I want a parser, monitor, and cyberpunk.";
		String deliminators = " .";
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary, deliminators);
		
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(text);
		

		assertFalse(stream.hasErrors());
		assertNotNull(stream.getFirstObject());
		assertEquals(3, stream.getObjects().size(), stream.getObjects().toString());
		
		assertEquals("parser,", stream.getObjects().get(0).getToken());
		assertEquals("parser,", stream.getObjects().get(0).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(0).getType());
		
		
		assertEquals("monitor,", stream.getObjects().get(1).getToken());
		assertEquals("monitor,", stream.getObjects().get(1).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(1).getType());
		
		assertEquals("cyberpunk", stream.getObjects().get(2).getToken());
		assertEquals("cyberpunk", stream.getObjects().get(2).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(2).getType());
	}

	@Test
	public void multiWordTest(){
		TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
		ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.GET);
		ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<>(BasicTextTokenType.ITEM);
		dictionary.put("want", def);
		dictionary.putAll(item, "cyberpunk", "parser", "monitor", "multiword support", "even three words");
		BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);

		String text = "For christmas I want a parser, cyberpunk, multiword support, even three words and much more.";
		
		TextTokenStream<BasicTextTokenType> stream = parser.parseText(text);
		
		assertFalse(stream.hasErrors());
		assertNotNull(stream.getFirstObject());
		assertEquals(4, stream.getObjects().size(), stream.getObjects().toString());
		
		assertEquals("parser", stream.getObjects().get(0).getToken());
		assertEquals("parser", stream.getObjects().get(0).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(0).getType());

		assertEquals("cyberpunk", stream.getObjects().get(1).getToken());
		assertEquals("cyberpunk", stream.getObjects().get(1).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(1).getType());

		assertEquals("multiword support", stream.getObjects().get(2).getToken());
		assertEquals("multiword support", stream.getObjects().get(2).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(2).getType());

		assertEquals("even three words", stream.getObjects().get(3).getToken());
		assertEquals("even three words", stream.getObjects().get(3).getStandardToken());
		assertEquals(BasicTextTokenType.ITEM, stream.getObjects().get(3).getType());
	}

	@Test
	public void testMultiWordSplit() {
        TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
        ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.GET);
        ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<>(BasicTextTokenType.ITEM);
        dictionary.put("want", def);
        dictionary.putAll(item, "cyberpunk", "parser", "monitor", "multiword support", "even three words");
        BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);

        String text = "For christmas I want a parser, cyberpunk, multiword support, even three words and much more.";

        String[] expectedToken = { "For", "christmas", "I", "want", "a", "parser", "cyberpunk", "multiword support",
                "even three words", "and", "much", "more" };
        String[] token = parser.splitText(text);

        assertArrayEquals(expectedToken, token);
	}
	
	@Test
	public void testMultiWordSplitSSameToken() {
	    //should only add one token per text, never double-up
        TextDictionary<BasicTextTokenType> dictionary = new TextDictionary<>();
        ITextDefinition<BasicTextTokenType> def = new SimpleTextDefinition<>(BasicTextTokenType.GET);
        ITextDefinition<BasicTextTokenType> item = new SimpleTextDefinition<>(BasicTextTokenType.ITEM);
        dictionary.put("want", def);
        //TODO figure out how to keep the dictionary from having values that are super-strings of others
        dictionary.putAll(item, "cyberpunk", "parser", "monitor", "even three", "multiword", "multiword support",
                "even three words");
        BasicTextParser<BasicTextTokenType> parser = new BasicTextParser<>(dictionary);

        String text = "For christmas I want a parser, cyberpunk, multiword support, even three words and much more.";

        String[] expectedToken = { "For", "christmas", "I", "want", "a", "parser", "cyberpunk", "multiword", "support",
                "even three", "words", "and", "much", "more" };
        String[] token = parser.splitText(text);

        assertArrayEquals(expectedToken, token);
	}
}
