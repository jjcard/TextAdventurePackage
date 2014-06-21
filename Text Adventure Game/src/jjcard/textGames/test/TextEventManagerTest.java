package jjcard.textGames.test;

import static org.junit.Assert.*;
import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.events.ITextEventListener;
import jjcard.textGames.game.events.ITextEventManager;
import jjcard.textGames.game.events.SimpleTextEvent;
import jjcard.textGames.game.events.TextEventManager;
import jjcard.textGames.game.impl.Commands;

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
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent(Commands.ATTACK, "Nothing"));
		assertFalse(succeded);
	}
	
	public void hasEventListenerTest(){
		ITextEventManager evntMgr = TextEventManager.getInstance();
		ITextEventListener listener = new ITextEventListener() {
			
			@Override
			public boolean handleEvent(ITextEvent event) {
				SimpleTextEvent e = (SimpleTextEvent) event;
				
				assertEquals(Commands.GET, e.getCommand());
				assertEquals("Win", e.getCommandKey());
				return true;
			}
		};
		
		evntMgr.registerEventListener(listener, SimpleTextEvent.class);
		
		boolean succeded = evntMgr.generateEvent(new SimpleTextEvent(Commands.GET, "Win"));
		assertTrue(succeded);
	}

}
