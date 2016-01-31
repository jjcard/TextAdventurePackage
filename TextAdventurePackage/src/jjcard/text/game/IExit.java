package jjcard.text.game;
/**
 * An IExit is an interface using the IGameElement names as directions.
 * It then uses that to return an ILocation.
 *
 */
public interface IExit extends ConcealableGameElement {

	public ILocation getLocation();
	
	public void setHidden(boolean hidden);
}
