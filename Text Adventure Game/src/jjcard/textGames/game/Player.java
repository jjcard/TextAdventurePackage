package jjcard.textGames.game;


public class Player extends Mob {
	private int level;
	private int xp;
	
	public Player(){
		super();
		level = 0;
		xp = 0;
	}
	public Player(String name){
		super(name);
		level = 0;
		xp = 0;
	}
	public Player(String name, int health){
		super(name, health);
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense){
		super(name, health, defense);
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack){
		super(name, health, defense, attack);
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack, int levelNew){
		super(name, health, defense, attack);
		level = levelNew;
		xp = 0;
	}

	public int getLevel(){
		return level;
	}
	public int getxp() {
		return xp;
	}

	public void changelevel(int change){
		level += change;
		if (level < 0){
			level = 0;
		}
	}
	public void setLevel(int levelN){
		level = levelN;
		if (level < 0){
			level = 0;
		}
	}
	public void changeXp(int change){
		xp += change;
		if (xp < 0){
			xp = 0;
		}
	}
	public void setXp(int xpN){
		xp = xpN;
		if (xp < 0){
			xp = 0;
		}
	}





	
	
}
