package jjcard.textGames.game.events.impl;

import jjcard.textGames.game.events.ITextEvent;

public abstract class StatusEvent implements ITextEvent {

	private boolean isMobTurn;
	
	public StatusEvent(boolean mobTurn){
		this.isMobTurn = mobTurn;
	}
	
	public boolean isMobTurn(){
		return isMobTurn;
	}

}
