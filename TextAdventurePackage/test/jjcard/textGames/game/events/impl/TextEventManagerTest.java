package jjcard.textGames.game.events.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.events.ITextEventListener;
import jjcard.textGames.game.events.ITextEventManager;

import org.junit.Before;
import org.junit.Test;

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
	
	public void hasEventListenerTest(){
		ITextEventManager evntMgr = TextEventManager.getInstance();
		ITextEventListener listener = new ITextEventListener() {
			
			@Override
			public boolean handleEvent(ITextEvent event) {
				SimpleTextEvent e = (SimpleTextEvent) event;
				
				assertEquals("get", e.getKey());
				assertEquals("Win", e.getCommandKey());
				return true;
			}
		};
		
		evntMgr.registerEventListener(listener, SimpleTextEvent.class);
		
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent("get", "Win"));
		assertTrue(succeded);
	}

}
