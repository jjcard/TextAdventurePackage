package jjcard.textGames.game;

import jjcard.textGames.game.impl.Player;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;

public interface IWorld<T extends ITextTokenType, K> {

	public Player getPlayer();
	public void setPlayer(Player player);
	public ILocation getCurrent();
	
	public void setCurrent(ILocation location);		
	
	
	public ITextTokenStream<T> parseInput(String input);
	
	public K executeCommands(ITextTokenStream<T> stream);
	
	public boolean goDirection(String dir);
	
	public void setTextParser(ITextParser<T> parser);
	
	
}
