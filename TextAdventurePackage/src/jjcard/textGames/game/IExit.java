package jjcard.textGames.game;
/**
 * An IExit is an interface using the IGameElement names as directions.
 * It then uses that to return an ILocation.
 *
 */
public interface IExit extends IGameElement {

	public ILocation getLocation();
}
