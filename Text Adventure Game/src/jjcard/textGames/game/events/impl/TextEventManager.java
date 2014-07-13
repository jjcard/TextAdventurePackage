package jjcard.textGames.game.events.impl;

import java.util.HashMap;
import java.util.Map;

import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.events.ITextEventListener;
import jjcard.textGames.game.events.ITextEventManager;

public class TextEventManager implements ITextEventManager{
	
	private static TextEventManager instance = null;
	private final Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	private TextEventManager(){
		listenerMap = new HashMap<Class<? extends ITextEvent>, ITextEventListener>();
	}
	
	/**
	 * Returns the instance of the TextEventmanager
	 * @return
	 */
	public static TextEventManager getInstance(){
		if (instance == null){
			instance = new TextEventManager();
		}
		return instance;
	}


	@Override
	public ITextEventListener registerEventListener(ITextEventListener listener,
			Class<? extends ITextEvent> c) {
		return listenerMap.put(c, listener);
	}


	@Override
	public boolean generateEvent(ITextEvent event) {
		ITextEventListener listener = listenerMap.get(event.getClass());
		if (listener != null){
			return listener.handleEvent(event);
		}
		return false;
	}

}
