package textGames;

public class Player extends Mob {
	private Armour armor;
	private Weapon weapon;
	private int level;
	private int xp;
	
	public Player(){
		super();
		armor = new Armour();
		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name){
		super(name);
		armor = new Armour();
		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health){
		super(name, health);
		armor = new Armour();
		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense){
		super(name, health, defense);
		armor = new Armour();
		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack){
		super(name, health, defense, attack);
		armor = new Armour();
		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack, int levelNew){
		super(name, health, defense, attack);
		armor = new Armour();
		weapon = new Weapon();
		level = levelNew;
		xp = 0;
	}
	public Armour armor(){
		return armor;
	}
	public Weapon weapon() {
		return weapon;
	}
	public int level(){
		return level;
	}
	public int xp() {
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
	public Armour setArmour(Armour a){
		Armour re = armor;
		armor = a;
		return re;
	}
	public Weapon setWeapon(Weapon w){
		Weapon re = weapon;
		weapon = w;
		return re;
	}
	public Armour removeArmour() {
		Armour re = armor;
		armor = null;
		return re;
	}
	public Weapon removeWeapon() {
		Weapon re = weapon;
		weapon = null;
		return re;
	}
	
	
}
