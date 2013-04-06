package jjcard.textGames.game;

public enum Status {
	 DEAD, POISONED, SICK, CONFUSED, BEWITCHED, ALIVE;
	
	public String toString() {
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}

