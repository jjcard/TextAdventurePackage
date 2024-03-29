package jjcard.text.game.events;
@FunctionalInterface
public interface ITextEventListener {
	
	/**
	 * Called by an IEventManager to handle a given event.
	 * @param event
	 * @return
	 */
    boolean handleEvent(ITextEvent event);
}
