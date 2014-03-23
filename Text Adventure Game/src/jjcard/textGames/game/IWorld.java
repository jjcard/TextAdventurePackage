package jjcard.textGames.game;

import jjcard.textGames.game.impl.CommandAndKey;
import jjcard.textGames.game.impl.Player;
import jjcard.textGames.game.impl.ReturnCom;

public interface IWorld {

	public Player getPlayer();
	public void setPlayer(Player playerN);
	public ILocation getCurrent();
	
	public void setCurrent(ILocation locationN);		
	
	
	public CommandAndKey parseInput(String input);
	
	public ReturnCom executeCommands(CommandAndKey comkey);
	
	public boolean goDirection(String dir);
	
	
}
