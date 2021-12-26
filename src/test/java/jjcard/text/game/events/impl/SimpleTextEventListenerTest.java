package jjcard.text.game.events.impl;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@Disabled
public class SimpleTextEventListenerTest {
	private PrintStream stream;
	@BeforeEach
	public void setUp(){
		 stream = new PrintStream(new ByteArrayOutputStream());
	}
	
	@AfterEach
	public void tearDown(){
		stream.close();
	}


	@Test
	public void test() {
		try {
			SimpleTextEventListener listener = new SimpleTextEventListener(stream);
			assertNotNull(listener);
		} catch (IOException e) {
			Assertions.fail("Could not find String.txt");
		}
	}

}
