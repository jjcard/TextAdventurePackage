package jjcard.textGames.game.impl;

import jjcard.textGames.game.IMob;
import jjcard.textGames.game.IStatus;

public abstract class AbstractStatus implements IStatus {
	/**
	 * true if the status should be done before the Mob acts, or after it in the turn. 
	 */
	private boolean before = true;
	private boolean isDone = false;
	private IMob target;
	
	
	protected AbstractStatus(IMob target){
		this.target = target;
	}
	protected AbstractStatus(IMob target, boolean isBefore){
		this(target);
		setIsBefore(isBefore);
	}	
	public boolean isBeforeTurn(){
		return before;
	}
	public void setIsBefore(boolean b){
		before = b;
	}
	public IMob getTarget(){
		return target;
	}
	public boolean isAfterTurn(){
		return !before;
	}
	protected void setTarget(IMob mob){
		this.target = mob;
	}
	protected void setIsDone(boolean done){
		this.isDone = done;
	}
	public boolean isDone(){
		return isDone;
	}
	
	
}

