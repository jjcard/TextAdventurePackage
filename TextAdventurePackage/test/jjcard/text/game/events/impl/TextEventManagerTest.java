package jjcard.text.game.events.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;
import jjcard.text.game.events.ITextEventManager;
import jjcard.text.game.events.impl.SimpleTextEvent;
import jjcard.text.game.events.impl.TextEventManager;

public class TextEventManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void noEventListenerTest() {
		ITextEventManager evntMgr = TextEventManager.getInstance();
		evntMgr.registerEventListener(null, SimpleTextEvent.class);
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent("attack", "Nothing"));
		assertFalse(succeded);
	}
	@Test
	public void hasEventListenerTest(){
		ITextEventManager evntMgr = TextEventManager.getInstance();
		ITextEventListener listener = event -> {
            SimpleTextEvent e = (SimpleTextEvent) event;

            assertEquals("get", e.getKey());
            assertEquals("Win", e.getCommandKey());
            return true;
        };
		
		evntMgr.registerEventListener(listener, SimpleTextEvent.class);
		
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent("get", "Win"));
		assertTrue(succeded);
	}
	@Test
	public void functionalInterfaceTest(){
		ITextEventManager evntMgr = TextEventManager.getInstance();
		ITextEventListener listener = (event) -> {
			SimpleTextEvent e = (SimpleTextEvent) event;
			assertEquals("len", e.getKey());
			assertEquals("Vim", e.getCommandKey());
			return true;
		};
		
		evntMgr.registerEventListener(listener, SimpleTextEvent.class);
		
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent("len", "Vim"));
		assertTrue(succeded);
	}

}
