package jjcard.text.game.events.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;
import jjcard.text.game.events.ITextEventManager;
import jjcard.text.game.events.impl.SimpleTextEvent;
import jjcard.text.game.events.impl.TextEventManager;

public class TextEventManagerTest {

	@BeforeEach
	public void setUp() {
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
