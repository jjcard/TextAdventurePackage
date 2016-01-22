package jjcard.text.game.events.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;
import jjcard.text.game.events.ITextEventManager;
/**
 *A version of ITextEventManager that uses a enum to synchronize and has a CuncurrentHashMap as its underlying holder of ITextEventListeners.
 *
 */
public enum ConcurrentTextEventManager implements ITextEventManager {
	INSTANCE;

	private final Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	
	private ConcurrentTextEventManager(){
		listenerMap = new ConcurrentHashMap<Class<? extends ITextEvent>, ITextEventListener>();
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
