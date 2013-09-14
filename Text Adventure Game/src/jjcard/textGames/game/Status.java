package jjcard.textGames.game;

public class Status {
	/**
	 * true if the status should be done before the Mob acts, or after it in the turn. 
	 */
	protected boolean before = true;
	
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
	public void setIsBfore(boolean b){
		before = b;
	}
	public boolean isAfter(){
		return !before;
	}
	
	
}

