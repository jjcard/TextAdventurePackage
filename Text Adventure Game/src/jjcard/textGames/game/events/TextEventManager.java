package jjcard.textGames.game.events;

import java.util.HashMap;
import java.util.Map;

public class TextEventManager implements ITextEventManager{
	
	private static TextEventManager instance = null;
	private Map<Class<? extends ITextEvent>, ITextEventListener> listenerMap;
	private TextEventManager(){
		listenerMap = new HashMap<Class<? extends ITextEvent>, ITextEventListener>();
	}
	
	
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
