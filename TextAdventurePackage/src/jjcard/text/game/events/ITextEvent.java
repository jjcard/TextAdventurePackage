package jjcard.text.game.events;

public interface ITextEvent {

	/**
	 * Should return a key uniquely representing the event. Can be use in the EventListeners
	 * @return
	 */
	public String getKey();
	
}
