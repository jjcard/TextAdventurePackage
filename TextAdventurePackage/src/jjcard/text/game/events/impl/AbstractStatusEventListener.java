package jjcard.text.game.events.impl;

import jjcard.text.game.IStatus;
import jjcard.text.game.events.ITextEvent;
import jjcard.text.game.events.ITextEventListener;
/**
 * Abstract class that has basic pieces needed for a status that only performs its effect for an event
 *
 */
public abstract class AbstractStatusEventListener implements ITextEventListener, IStatus {

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
		AbstractStatusEvent statusEvent = (AbstractStatusEvent) event;
		effect(statusEvent.isMobTurn());
		return true;
	}

}
