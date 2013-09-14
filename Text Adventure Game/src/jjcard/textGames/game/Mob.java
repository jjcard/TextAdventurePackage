package jjcard.textGames.game;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Mob extends GameElement{
	public static int DEFAULT_HEALTH = 10;
	private String description;
	private int maxHealth;
	private int curHealth;
	private int money = 0;
	private Map<String, Item> inventory = new HashMap<String, Item>();
	private int defense = 0;
	private int attack = 0;
	private boolean Hostile = true;
	private LinkedList<Status> statusList = new LinkedList<Status>();
	private Armour armor;
	private String armorKey;
	private Weapon weapon;
	private String weaponKey;
	
	public Mob() {
		super();
		description = new String();
		maxHealth = DEFAULT_HEALTH;
		curHealth = maxHealth;

	}
	public Mob(String name) {
		super(name);
		description = "";
		maxHealth = DEFAULT_HEALTH;
		curHealth = maxHealth;

	}
	public Mob(String name, int healthNew){
		super(name);
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		
	}
	public Mob(String name, int healthNew, int defenseNew) {
		super(name);
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		defense = defenseNew;

	}
	public Mob(String name, int healthNew, int defenseNew, int attackNew){
		super(name);
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		defense = defenseNew;
		attack = attackNew;
	}

	public String getDescription() {
		return description;
	}
	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth(){
		return curHealth;
	}
	public int getMoney(){
		return money;
	}
	public Map<String, Item> getInventory() {
		return inventory;
	}
	public Item getItem(String key){
		return inventory.get(key);
	}
	public Armour getArmor(){
		return armor;
	}
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * returns defense only. Does not add armour bonus.
	 * @return
	 */
	public int getBasicDefense() {
		return defense;
	}
	/**
	 * returns attack only. Does not add weapon bonus.
	 * @return
	 */
	public int getBasicAttack() {
		return attack;
	}
	public boolean isHostile() {
		return Hostile;
	}
	public LinkedList<Status> getstatusList() {
		return statusList;
	}
	public boolean containsStatus(Status s){
		return statusList.contains(s);
	}
	public boolean removeStatus(Status s){
		return statusList.remove(s);
	}
	public void setName(String name){
		setElementName(new ElementName(name));
	}

	public void setDescription(String newDes){
		description = newDes;
	}
	public void changeMaxHealth(int change){
		maxHealth += change;
		if(maxHealth <= 0){
			maxHealth = 0;
		}
		if (maxHealth < curHealth){
			changeHealth(maxHealth);
		}
	}
	public void changeHealth(int change){
		curHealth += change;
		if (curHealth > maxHealth){
			curHealth = maxHealth;
		}
		if (curHealth <= 0){
			curHealth = 0;
		} 
	}
	public void addStatus(Status s){
			statusList.add(s);

	}
	public void changeMoney(int change){
		money += change;
	}
	public void setMoney(int moneyN){
		money = moneyN;
	}
	public void changeDefense(int change){
		defense += change;
		if (defense < 0){
			defense = 0;
		}
	}
	public void setDefense(int defenseN){
		defense = defenseN;
	}
	public void changeAttack(int change){
		attack += change;
		if (attack < 0){
			attack = 0;
		}
	}
	/**
	 * Sets armour with given armorKey and returns previous armour
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
	public void setAttack(int attackN){
		attack = attackN;
	}
	/**
	 * gets attack plus weapon attack bonus
	 * @return
	 */
	public int getFullAttack(){
		return attack + (weapon == null? 0: weapon.getAttack());
	}
	public int getFullDefense(){
		return defense + (armor == null? 0: armor.getDefense());
	}
	public void setHostile(boolean change){
		Hostile = change;
	}
	public void setstatusList(LinkedList<Status> s){
		statusList = s;
	}
	public Item addItem(String key, Item add){
		
		return inventory.put(key, add);
		
	}
	public Armour removeArmour() {
		Armour re = armor;
		armor = null;
		armorKey = null;
		return re;
	}
	public void addAllItems(Map<String, Item> addMap){
		inventory.putAll(addMap);
		
		}
	public Item removeItem(String key){
		return inventory.remove(key);
	}
	public void removeInventory(){
		inventory = null;
	}
	/**
	 * attack this mob for this amount of damage minus the mobs defense. 
	 * Returns new health
	 * @param attack
	 * @return
	 */
	public int attackMob(int damage){
		changeHealth(-(damage - defense) );
		return curHealth;
	}
	public boolean containsItem(String key){
		return inventory.containsKey(key);
	}
	public int inventorySize() {
		return inventory.size();
	}
	public boolean isDead(){
		return  getHealth() <= 0;
	}
	public String toString() {
		return getStandardName();
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
	public boolean equals(Object o){
		if (o instanceof Mob){
			Mob m = (Mob) o;
			return this.getStandardName().equals(m.getStandardName()) && this.description.equalsIgnoreCase(m.description);
		} else {
			return false;
		}
	}
	public String inventoryToString(){
		String re = inventory.keySet().toString();
		return re.substring(1, re.length()-1);
		
	}
	public Armour setArmour(String a){
		Item ar = getItem(a);
		if (ar != null && ar instanceof Armour){
			return setArmour(a, (Armour) ar);
		}
		return null;
	}
	public Weapon setWeapon(String a){
		Item ar = getItem(a);
		if (ar != null && ar instanceof Weapon){
			return setWeapon(a, (Weapon) ar);
		}
		return null;
	}
}
