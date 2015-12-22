package jjcard.textGames.game;

import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;
/**
 * Interface for containing the Player, Location, and executing commands on them.
 *
 * @param <T> extends ITextTokenType
 * @param <K>
 * @param <P> extends IMob. The Player character's type
 */
public interface IWorld<T extends ITextTokenType, K, P extends IMob> {

	public P getPlayer();
	public void setPlayer(P player);
	/**
	 * Returns the current Location the player is in
	 * @return
	 */
	public ILocation getCurrent();
	
	public void setCurrent(ILocation location);		
	
	
	public ITextTokenStream<T> parseInput(String input);
	
	public K executeCommands(ITextTokenStream<T> stream);
	
	public boolean goDirection(String dir);
	
	public void setTextParser(ITextParser<T> parser);
	
	
}
