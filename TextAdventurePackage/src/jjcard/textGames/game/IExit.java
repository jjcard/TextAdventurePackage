package jjcard.textGames.game;
/**
 * An IExit is an interface using the IGameElement names as directions.
 * It then uses that to return an ILocation.
 *
 */
public interface IExit extends ConcealableGameElement {

	public ILocation getLocation();
	
	
	public boolean isHidden();
	
	public void setHidden(boolean hidden);
}
