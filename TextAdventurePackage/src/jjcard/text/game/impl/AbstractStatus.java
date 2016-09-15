package jjcard.text.game.impl;

import jjcard.text.game.IStatus;
import jjcard.text.game.util.Experimental;
@Experimental
public abstract class AbstractStatus<T> implements IStatus {
	/**
	 * true if the status should be done before the Mob acts, or after it in the turn. 
	 */
	private boolean before = true;
	private boolean isDone = false;
	private T target;
	
	
	protected AbstractStatus(T target){
		this.target = target;
	}
	protected AbstractStatus(T target, boolean isBefore){
		this(target);
		setIsBefore(isBefore);
	}	
	public boolean isBeforeTurn(){
		return before;
	}
	public void setIsBefore(boolean isBfore){
		before = isBfore;
	}
	public T getTarget(){
		return target;
	}
	public boolean isAfterTurn(){
		return !before;
	}
	protected void setTarget(T mob){
		this.target = mob;
	}
	protected void setIsDone(boolean done){
		this.isDone = done;
	}
	public boolean isDone(){
		return isDone;
	}
	
	
}

