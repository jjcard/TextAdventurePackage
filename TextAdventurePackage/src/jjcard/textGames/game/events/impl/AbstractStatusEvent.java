package jjcard.textGames.game.events.impl;

import jjcard.textGames.game.events.ITextEvent;

public abstract class AbstractStatusEvent implements ITextEvent {

	private final boolean MobTurn;
	
	public AbstractStatusEvent(boolean mobTurn){
		this.MobTurn = mobTurn;
	}
	
	public boolean isMobTurn(){
		return MobTurn;
	}

}
