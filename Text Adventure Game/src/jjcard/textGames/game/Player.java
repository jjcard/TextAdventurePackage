package jjcard.textGames.game;


public class Player extends Mob {
	private Armour armor;
	private String armorKey;
	private Weapon weapon;
	private String weaponKey;
	private int level;
	private int xp;
	
	public Player(){
		super();
//		armor = new Armour();
//		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name){
		super(name);
//		armor = new Armour();
//		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health){
		super(name, health);
		//armor = new Armour();
		///weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense){
		super(name, health, defense);
//		armor = new Armour();
//		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack){
		super(name, health, defense, attack);
//		armor = new Armour();
//		weapon = new Weapon();
		level = 0;
		xp = 0;
	}
	public Player(String name, int health, int defense, int attack, int levelNew){
		super(name, health, defense, attack);
//		armor = new Armour();
//		weapon = new Weapon();
		level = levelNew;
		xp = 0;
	}
	public Armour getArmor(){
		return armor;
	}
	public Weapon getWeapon() {
		return weapon;
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
	/**
	 * Sets armour with givin armorKey and returns previous armour
	 * @param a
	 * @return previous Armour
	 */
	public Armour setArmour(String key, Armour a){
		armorKey = key;
		Armour re = armor;
		armor = a;
		return re;
	}
	/**
	 * 
	 * @param w
	 * @return previous Weapon
	 */
	public Weapon setWeapon(String key, Weapon w){
		weaponKey = key;
		Weapon re = weapon;
		weapon = w;
		return re;
	}
	public Armour setArmour(String a){
		Item ar = super.getItem(a);
		if (ar != null && ar instanceof Armour){
			return setArmour(a, (Armour) ar);
		}
		return null;
	}
	public Weapon setWeapon(String a){
		Item ar = super.getItem(a);
		if (ar != null && ar instanceof Weapon){
			return setWeapon(a, (Weapon) ar);
		}
		return null;
	}
	public Armour removeArmour() {
		Armour re = armor;
		armor = null;
		armorKey = null;
		return re;
	}
	public Weapon removeWeapon() {
		Weapon re = weapon;
		weaponKey = null;
		weapon = null;
		return re;
	}
	public String getArmorKey(){
		return armorKey;
	}
	public boolean armorIsKey(String key){
		if (armorKey == null){
			return false;
		}
		return armorKey.equals(key);
	}
	public boolean weaponIsKey(String key){
		if (weaponKey == null){
			return false;
		}
		return weaponKey.equals(key);
	}
	public String getWeaponKey(){
		return weaponKey;
	}
	
	
}
