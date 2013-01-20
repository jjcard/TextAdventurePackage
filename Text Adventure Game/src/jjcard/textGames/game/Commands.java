package jjcard.textGames.game;

public enum Commands {
	ATTACK, LOOK, MOVE, GET, LOOT_ALL, PLAYER_INFO, QUIT, DROP, EQUIP, UNEQUIP;
	
	
	public String toString() {
		return super.toString().toLowerCase();
		//return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}
