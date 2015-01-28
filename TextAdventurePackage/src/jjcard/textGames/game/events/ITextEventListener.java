package jjcard.textGames.game.events;

public interface ITextEventListener {

	
	/**
	 * Called by an IEventManager to handle a given event.
	 * @param event
	 * @return
	 */
	public boolean handleEvent(ITextEvent event);
}
