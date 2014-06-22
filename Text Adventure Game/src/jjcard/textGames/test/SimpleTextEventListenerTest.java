package jjcard.textGames.test;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import jjcard.textGames.game.events.SimpleTextEventListener;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleTextEventListenerTest {
	PrintStream stream;
	@Before
	public void setUp(){
		 stream = new PrintStream(new ByteArrayOutputStream());
	}
	
	@After
	public void tearDown(){
		stream.close();
	}


	@Test
	public void test() {
		try {
			SimpleTextEventListener listener = new SimpleTextEventListener(stream);
			assertNotNull(listener);
		} catch (IOException e) {
			Assert.fail("Could not find String.txt");
		}
	}

}
