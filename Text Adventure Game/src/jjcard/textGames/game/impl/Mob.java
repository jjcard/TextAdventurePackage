package jjcard.textGames.game.impl;
import java.util.LinkedList;
import java.util.List;

import jjcard.textGames.game.IGameElementMap;

/**
 * a class to represent creatures and people.
 * @author jjcard
 *
 */
public class Mob extends GameElement{
	public static int DEFAULT_HEALTH = 10;
	private String description;
	private int maxHealth;
	private int curHealth;
	private int money = 0;
	private IGameElementMap<Item> inventory = new GameElementMap<Item>();
	private int defense = 0;
	private int attack = 0;
	private boolean hostile = true;
	private List<Status> statusList = new LinkedList<Status>();
	private Armour armor;
	private Weapon weapon;
	
	public static class MobBuilder extends GameElementBuilder{
		private String description;
		private int maxHealth;
		private int curHealth;
		private int money = 0;
		private IGameElementMap<Item> inventory = new GameElementMap<Item>();
		private int defense = 0;
		private int attack = 0;
		private boolean hostile = true;
		private List<Status> statusList = new LinkedList<Status>();
		private Armour armor;
		private Weapon weapon;	
		
		public MobBuilder maxHealth(int maxHealth){
			this.maxHealth = maxHealth;
			return this;
		}
		public MobBuilder curHelath(int curHealth){
			this.curHealth = curHealth;
			return this;
		}
		public MobBuilder description(String description){
			this.description = description;
			return this;
		}
		public MobBuilder money(int money){
			this.money = money;
			return this;
		}
		public MobBuilder inventory(IGameElementMap<Item> inventory){
			if (inventory == null){
				this.inventory = new GameElementMap<Item>();
			} else {
				this.inventory = inventory;	
			}
			
			return this;
		}
		public MobBuilder defense(int defense){
			this.defense = defense;
			return this;
		}
		public MobBuilder attack(int attack){
			this.attack = attack;
			return this;
		}
		public MobBuilder hostile(boolean hostile){
			this.hostile = hostile;
			return this;
		}
		public MobBuilder statusList(List<Status> statusList){
			if (statusList == null){
				this.statusList = new LinkedList<Status>();
			} else {
				this.statusList = statusList;	
			}
			
			return this;
		}
		public MobBuilder armor(Armour armour){
			this.armor = armour;
			return this;
		}
		public MobBuilder weapon(Weapon weapon){
			this.weapon = weapon;
			return this;
		}
		public MobBuilder addStatus(Status status){
			statusList.add(status);
			return this;
		}
		public MobBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public MobBuilder altNames(String[] altNames){
			super.altNames(altNames);
			return this;
		}
		public MobBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public MobBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public Mob build(){
			return new Mob(this);
		}
		
		
	}
	
	protected Mob( MobBuilder b){
		  super(b);
		  description = b.description;
		  maxHealth = b.maxHealth;
		  curHealth = b.curHealth;
		  if (maxHealth < curHealth){
			  maxHealth = curHealth;
		  }
		  money = b.money;
		  inventory = b.inventory;
		  defense = b.defense;
		  attack = b.attack;
		  hostile = b.hostile;
		  statusList = b.statusList;
		  armor = b.armor;
		  weapon = b.weapon;
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
	public IGameElementMap<Item> getInventory() {
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
		return hostile;
	}
	public List<Status> getStatusList() {
		return statusList;
	}
	public boolean containsStatus(Status s){
		return statusList.contains(s);
	}
	public boolean removeStatus(Status s){
		return statusList.remove(s);
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
	public Armour setArmour( Armour a){
		Armour re = armor;
		armor = a;
		return re;
	}
	/**
	 * 
	 * @param w
	 * @return previous Weapon
	 */
	public Weapon setWeapon( Weapon w){
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
	public void setHostile(boolean hostile){
		this.hostile = hostile;
	}
	public void setstatusList(LinkedList<Status> s){
		statusList = s;
	}
	public Item addItem(Item add){
		
		return inventory.put(add);
		
	}
	public Armour removeArmour() {
		Armour re = armor;
		armor = null;
		return re;
	}
	public void addAllItems(IGameElementMap<Item> addMap){
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
		return inventory.containsName(key);
	}
	public int inventorySize() {
		return inventory.getElementCount();
	}
	public boolean isDead(){
		return  getHealth() <= 0;
	}
	public String toString() {
		return getStandardName();
	}

	public Weapon removeWeapon() {
		Weapon re = weapon;
		weapon = null;
		return re;
	}
	public String getStandardArmorKey(){
		return armor == null? null: armor.getStandardName();
	}
	public boolean isKeyforArmor(String key){
		return isKeyForItem(key, armor);
	}
	private boolean isKeyForItem(String key, Item item){
		if (item == null){
			return false;
		}
		if (key.equalsIgnoreCase(item.getStandardName())){
			return true;
		} else {
			if (item.getAltNames() != null){
				for (String altName: item.getAltNames()){
					if (key.equalsIgnoreCase(altName)){
						return true;
					}
				}
			}
			return false;
		}
	}
	public boolean isKeyForWeapon(String key){
		return isKeyForItem(key, weapon);
	}
	public String getStandardWeaponKey(){
		return weapon == null? null: weapon.getStandardName();
	}
	public boolean equals(Object o){
		
		if (this == o){
			return true;
		}
		if (o == null){
			return false;
		}
		if (o instanceof Mob){
			Mob m = (Mob) o;
			if (this.getStandardName() == null){
				if (m.getStandardName() != null){
					return false;
				}
			} else{
				if (!this.getStandardName().equals(m.getStandardName())){
					return false;
				}
			}
			if (this.description == null){
				if (m.description != null){
					return false;
				}
			} else {
				if (!this.description.equals(m.description)){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	public String inventoryToString(){
		String re = inventory.getAllStandardNamesAsString();
		return re.substring(1, re.length()-1);
		
	}
	public Armour setArmour(String a){
		Item ar = getItem(a);
		if (ar != null && ar instanceof Armour){
			return setArmour((Armour) ar);
		}
		return null;
	}
	public Weapon setWeapon(String a){
		Item ar = getItem(a);
		if (ar != null && ar instanceof Weapon){
			return setWeapon( (Weapon) ar);
		}
		return null;
	}
}
