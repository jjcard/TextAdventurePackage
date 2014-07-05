package jjcard.textGames.game.impl;

public abstract class Status {
	/**
	 * true if the status should be done before the Mob acts, or after it in the turn. 
	 */
	private boolean before = true;
	
	
	protected Status(){
		
	}
	protected Status(boolean isBefore){
		setIsBefore(isBefore);
	}
	/**
	 * Each status should override this method. 
	 * It should modify and return the Mob that gets entered.
	 * @param mob
	 * @return
	 */
	public Mob effect(Mob mob){
		return mob;
	}
	
	public boolean isBefore(){
		return before;
	}
	public void setIsBefore(boolean b){
		before = b;
	}
	public boolean isAfter(){
		return !before;
	}
	
	
}

