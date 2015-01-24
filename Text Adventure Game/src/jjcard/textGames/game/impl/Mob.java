package jjcard.textGames.game.impl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jjcard.textGames.game.IArmour;
import jjcard.textGames.game.IGameElement;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.IStatus;
import jjcard.textGames.game.IWeapon;
import jjcard.textGames.game.util.EqualsUtil;
import jjcard.textGames.game.util.MapUtil;

/**
 * a class to represent creatures and people.
 * @author jjcard
 *
 */
public class Mob extends AbstractGameElement implements IMob{
	public static final int DEFAULT_HEALTH = 10;
	private String description;
	private int maxHealth;
	private int curHealth;
	private int money = 0;
	private Map<String, IItem> inventory;
	private int defense = 0;
	private int attack = 0;
	private boolean hostile = true;
	private List<IStatus> statusList;
	private IArmour armour;
	private IWeapon weapon;
	private final boolean checkHealth;
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
	
	public static class MobBuilder extends GameElementBuilder{
		private String description;
		private int maxHealth;
		private int curHealth;
		private int money = 0;
		private Map<String, IItem> inventory = new HashMap<String, IItem>();
		private int defense = 0;
		private int attack = 0;
		private boolean hostile = true;
		private List<IStatus> statusList = new LinkedList<IStatus>();
		private IArmour armour;
		private IWeapon weapon;
		private boolean checkHealth = true;
		
		public MobBuilder(){
			super();
		}
		public MobBuilder(Mob b){
			super(b);
			  this.description = b.description;
			  this.maxHealth = b.maxHealth;
			  this.curHealth = b.curHealth;
			  if (maxHealth <= 0){
				  this. maxHealth = curHealth;
			  }
			  this.money = b.money;
			  this.inventory = b.inventory;
			  this.defense = b.defense;
			  this.attack = b.attack;
			  this.hostile = b.hostile;
			  this.statusList = b.statusList;
			  this.armour = b.armour;
			  this.weapon = b.weapon;
		}
		public MobBuilder(AbstractGameElement g){
			super(g);
		}
		/**
		 * Indicator for the class doing validation on the health and maxHealth fields.
		 * Default is true.
		 * @param checkHealth
		 * @return
		 */
		public MobBuilder checkHealth(boolean checkHealth){
			this.checkHealth = checkHealth;
			return this;
		}
		public MobBuilder maxHealth(int maxHealth){
			this.maxHealth = maxHealth;
			return this;
		}
		public MobBuilder health(int curHealth){
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
		public MobBuilder inventory(Map<String, IItem> inventory){
			if (inventory == null){
				this.inventory = new HashMap<String, IItem>();
			} else {
				this.inventory = inventory;	
			}
			
			return this;
		}
		public MobBuilder addItem(IItem item){
			this.inventory.put(item.getStandardName(), item);
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
		public MobBuilder statusList(List<IStatus> statusList){
			if (statusList == null){
				this.statusList = new LinkedList<IStatus>();
			} else {
				this.statusList = statusList;	
			}
			
			return this;
		}
		public MobBuilder armour(IArmour armour){
			this.armour = armour;
			return this;
		}
		public MobBuilder weapon(IWeapon weapon){
			this.weapon = weapon;
			return this;
		}
		public MobBuilder addStatus(IStatus status){
			statusList.add(status);
			return this;
		}
		public MobBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public MobBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public MobBuilder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Mob build(){
			return new Mob(this);
		}
		
		
	}
	
	protected Mob( MobBuilder b){
		  super(b);
		  description = b.description;
		  if (b.maxHealth > 0){
			  //valid max health
			setMaxHealth(b.maxHealth);  
		  } else {
			  setMaxHealth(b.curHealth);
		  }
		  
		  setHealth(b.curHealth);
		  setMoney(b.money);
		  inventory = b.inventory;
		  setDefense(b.defense);
		  setAttack(b.attack);
		  hostile = b.hostile;
		  statusList = b.statusList;
		  armour = b.armour;
		  weapon = b.weapon;
		  checkHealth = b.checkHealth;
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
	public Map<String, IItem> getInventory() {
		return inventory;
	}
	public IItem getItem(String key){
		return MAP_UTIL.getItemFromMap(inventory, key);
	}
	public IArmour getArmour(){
		return armour;
	}
	public IWeapon getWeapon() {
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
	public List<IStatus> getStatusList() {
		return statusList;
	}
	public boolean containsStatus(IStatus s){
		return statusList.contains(s);
	}
	public boolean removeStatus(IStatus s){
		return statusList.remove(s);
	}

	public void setDescription(String newDes){
		description = newDes;
	}
	public void changeMaxHealth(int change){
		setMaxHealth(maxHealth + change);
	}
	/**
	 * Sets the max Health to the given value.
	 * If checkHealth is true, also checks if health is greater then maxHealth
	 * @param maxHealth
	 */
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
		if (doValidateFields()){
			if (this.maxHealth < 0){
				this.maxHealth = 0;
			}
			
			if (checkHealth && this.maxHealth < curHealth){
				setHealth(this.maxHealth);
			}			
		}

	}
	public void changeHealth(int change){
		setHealth(curHealth + change);
 
	}
	public void setHealth(int health){
		curHealth = health;
		if (doValidateFields()){
			if (checkHealth && curHealth > maxHealth){
				curHealth = maxHealth;
			}
			if (checkHealth && curHealth < 0){
				curHealth = 0;
			}			
		}

	}
	public void addIStatus(IStatus s){
			statusList.add(s);

	}
	public void changeMoney(int change){
		money += change;
		if (doValidateFields() && money < 0){
			money = 0;
		}
	}
	public void setMoney(int moneyN){
		money = moneyN;
		if (doValidateFields() && money < 0){
			money = 0;
		}
	}
	public void changeDefense(int change){
		defense += change;
		if (doValidateFields() && defense < 0){
			defense = 0;
		}
	}
	public void setDefense(int defenseN){
		defense = defenseN;
	}
	public void changeAttack(int change){
		attack += change;
		if (doValidateFields() && attack < 0){
			attack = 0;
		}
	}
	/**
	 * Sets armour with given armourKey and returns previous armour
	 * @param a
	 * @return previous Armour
	 */
	public IArmour setArmour( IArmour a){
		IArmour re = armour;
		armour = a;
		return re;
	}
	/**
	 * 
	 * @param w
	 * @return previous Weapon
	 */
	public IWeapon setWeapon( IWeapon w){
		IWeapon re = weapon;
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
		return attack + getWeaponBonus();
	}
	/**
	 * returns the weapon bonus or 0 if no weapon equipped
	 * @return
	 */
	public int getWeaponBonus(){
		return weapon == null? 0: weapon.getAttack();
	}
	/**
	 * Returns the armour bonus or 0 if no armour equipped
	 * @return
	 */
	public int getArmourBonus(){
		return armour == null? 0: armour.getDefense();
	}
	/**
	 * gets defense plus any bonus
	 * @return
	 */
	public int getFullDefense(){
		return defense + getArmourBonus();
	}
	public void setHostile(boolean hostile){
		this.hostile = hostile;
	}
	public void setIStatusList(LinkedList<IStatus> s){
		statusList = s;
	}
	public IItem addItem(IItem add){
		return MAP_UTIL.addItemToMap(inventory, add);
	}
	public IArmour removeArmour() {
		IArmour re = armour;
		armour = null;
		return re;
	}
	public void addAllItems(Map<String, IItem> addMap){
		inventory.putAll(addMap);
		
		}
	public IItem removeItem(String key){
		return MAP_UTIL.removeItemFromMap(inventory, key);
	}
	public Map<String, IItem> removeInventory(){
		Map<String, IItem> returnIn = inventory;
		inventory = null;
		return returnIn;
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
		return MAP_UTIL.containsKey(inventory, key);
	}
	public int inventorySize() {
		return inventory.size();
	}
	public boolean isDead(){
		return  getHealth() <= 0;
	}
	public boolean isAlive(){
		return !isDead();
	}
	public String toString() {
		return getStandardName();
	}

	public IWeapon removeWeapon() {
		IWeapon re = weapon;
		weapon = null;
		return re;
	}
	public String getStandardArmourKey(){
		return armour == null? null: armour.getStandardName();
	}
	public boolean isKeyforArmour(String key){
		return isKeyForItem(key, armour);
	}
	private boolean isKeyForItem(String key, IGameElement item){

		if (item == null){
			return false;
		}
		if (key.equalsIgnoreCase(item.getStandardName())){
			return true;
		}
		return false;
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
		if (o instanceof Mob){
			Mob m = (Mob) o;
			
			if (EqualsUtil.notEqual(this.getStandardName(), m.getStandardName())){
				return false;
			}
			if (EqualsUtil.notEqual(this.description, m.description)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	public String inventoryToString(){
		return MAP_UTIL.getKeysAsString(inventory);
		
	}
	public IArmour setArmour(String a){
		IItem ar = getItem(a);
		if (ar != null && ar instanceof IArmour){
			return setArmour((IArmour) ar);
		}
		return null;
	}
	public IWeapon setWeapon(String a){
		IItem ar = getItem(a);
		if (ar != null && ar instanceof Weapon){
			return setWeapon( (Weapon) ar);
		}
		return null;
	}
}
