package jjcard.textGames.game;

import jjcard.textGames.game.impl.Player;
import jjcard.textGames.game.impl.ReturnCom;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenType;
import jjcard.textGames.game.parser.TextTokenStream;

public interface IWorld<T extends ITextTokenType> {

	public Player getPlayer();
	public void setPlayer(Player playerN);
	public ILocation getCurrent();
	
	public void setCurrent(ILocation locationN);		
	
	
	public TextTokenStream<T> parseInput(String input);
	
	public ReturnCom executeCommands(TextTokenStream<T> stream);
	
	public boolean goDirection(String dir);
	
	public void setTextParser(ITextParser<T> parser);
	
	
}
