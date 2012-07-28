package jjcard.textGames.game;

public enum Status {
	ALIVE, DEAD, SICK, POISONED, CONFUSED, BEWITCHED;
	
	public String toString() {
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}

