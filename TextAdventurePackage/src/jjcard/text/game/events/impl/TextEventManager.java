package jjcard.text.game.events.impl;

import java.util.HashMap;
import java.util.Map;

import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;
import jjcard.text.game.events.ITextEventManager;

public final class TextEventManager implements ITextEventManager{
	
	private static TextEventManager instance = null;
	private final Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	private TextEventManager(){
		listenerMap = new HashMap<>();
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
			Class<? extends ITextEvent> event) {
		return listenerMap.put(event, listener);
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
