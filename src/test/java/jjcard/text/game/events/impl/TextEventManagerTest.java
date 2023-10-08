package jjcard.text.game.events.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jjcard.text.game.events.ITextEventListener;
import jjcard.text.game.events.ITextEventManager;

public class TextEventManagerTest {

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

            assertEquals("get", e.key());
            assertEquals("Win", e.commandKey());
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
			assertEquals("len", e.key());
			assertEquals("Vim", e.commandKey());
			return true;
		};
		
		evntMgr.registerEventListener(listener, SimpleTextEvent.class);
		
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent("len", "Vim"));
		assertTrue(succeded);
	}

}
