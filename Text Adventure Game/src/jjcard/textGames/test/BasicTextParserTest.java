package jjcard.textGames.test;

import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.impl.BasicTextParser;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;
import junit.framework.Assert;

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

}
