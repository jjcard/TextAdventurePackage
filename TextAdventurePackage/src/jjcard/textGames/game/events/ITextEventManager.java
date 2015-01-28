package jjcard.textGames.game.events;
/**
 * Interface for a Event Manager
 * @author jjcard
 *
 */
public interface ITextEventManager {
	
	/**
	 * Registers the listener to handle and events of the given class.
	 * @param listener
	 * @param c
	 * @return
	 */
	public ITextEventListener registerEventListener(ITextEventListener listener, Class<? extends ITextEvent> eventClass);
	
	/**
	 * Generates a event and calls the listener associated with the event class.
	 * If a listener is found, returns the value of the handleEvent() method of the listener, otherwise returns null.
	 * @param event
	 * @return
	 */
	public boolean generateEvent(ITextEvent event);

}
