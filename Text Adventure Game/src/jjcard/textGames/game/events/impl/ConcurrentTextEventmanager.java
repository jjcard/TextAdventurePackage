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
public class ConcurrentTextEventmanager implements ITextEventManager {

	private static ConcurrentTextEventmanager instance = null;
	private final Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	
	private ConcurrentTextEventmanager(){
		listenerMap = new ConcurrentHashMap<Class<? extends ITextEvent>, ITextEventListener>();
	}
	/**
	 * Returns the instance of the TextEventmanager
	 * @return
	 */
	public static synchronized ConcurrentTextEventmanager getInstance(){
		if (instance == null){
			instance = new ConcurrentTextEventmanager();
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
