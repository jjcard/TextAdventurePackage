package jjcard.textGames.game.events.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.events.ITextEventListener;
import jjcard.textGames.game.events.ITextEventManager;
/**
 *A version of ITextEventManager that uses a synchronized getInstance and has a CuncurrentHashMap as its underlying holder of ITextEventListeners.
 *
 */
public final class ConcurrentTextEventManager implements ITextEventManager {

	private static ConcurrentTextEventManager instance = null;
	private final Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	
	private ConcurrentTextEventManager(){
		listenerMap = new ConcurrentHashMap<Class<? extends ITextEvent>, ITextEventListener>();
	}
	/**
	 * Returns the instance of the TextEventmanager
	 * @return
	 */
	public static synchronized ConcurrentTextEventManager getInstance(){
		if (instance == null){
			instance = new ConcurrentTextEventManager();
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
