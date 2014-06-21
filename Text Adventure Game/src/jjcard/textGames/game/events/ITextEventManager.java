package jjcard.textGames.game.events;

public interface ITextEventManager {
	
	
	public ITextEventListener registerEventListener(ITextEventListener listener, Class<? extends ITextEvent> c);
	
	public boolean generateEvent(ITextEvent event);

}
