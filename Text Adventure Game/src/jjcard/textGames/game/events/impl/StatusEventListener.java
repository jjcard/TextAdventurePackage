package jjcard.textGames.game.events.impl;

import jjcard.textGames.game.IStatus;
import jjcard.textGames.game.events.ITextEvent;
import jjcard.textGames.game.events.ITextEventListener;
/**
 * Abstract class that has basic pieces needed for a status that only performs its effect for an event
 * @author jjcard
 *
 */
public abstract class StatusEventListener implements ITextEventListener, IStatus {

	private boolean isDone;

	@Override
	public boolean isBeforeTurn() {
		return false;
	}

	@Override
	public boolean isAfterTurn() {
		return false;
	}
	@Override
	public boolean isDone() {
		return isDone;
	}

	public abstract void effect(boolean mobTurn);
	protected void setDone(boolean done){
		this.isDone = done;
	}
	@Override
	public boolean handleEvent(ITextEvent event) {
		StatusEvent statusEvent = (StatusEvent) event;
		effect(statusEvent.isMobTurn());
		return true;
	}

}
