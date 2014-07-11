package jjcard.textGames.game;

import jjcard.textGames.game.impl.Player;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.TextTokenStream;

public interface IWorld<T extends ITextTokenType, K> {

	public Player getPlayer();
	public void setPlayer(Player player);
	public ILocation getCurrent();
	
	public void setCurrent(ILocation location);		
	
	
	public TextTokenStream<T> parseInput(String input);
	
	public K executeCommands(TextTokenStream<T> stream);
	
	public boolean goDirection(String dir);
	
	public void setTextParser(ITextParser<T> parser);
	
	
}
