package jjcard.text.game.impl;

import static jjcard.text.game.util.WorldUtil.isKeyForItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.text.game.IArmour;
import jjcard.text.game.IItem;
import jjcard.text.game.IMob;
import jjcard.text.game.IStatus;
import jjcard.text.game.IWeapon;
import jjcard.text.game.util.MapUtil;
import jjcard.text.game.util.ObjectsUtil;

/**
 * a class to represent creatures and people.
 *
 */
@JsonDeserialize(builder = Mob.Builder.class)
public class Mob extends AbstractGameElement implements IMob{
	@JsonProperty("maxHealth")
	private int maxHealth;
	@JsonProperty("health")
	private int curHealth;
	@JsonProperty("money")
	private int money = 0;
	@JsonProperty("inven")
	private Map<String, IItem> inventory;
	@JsonProperty("def")
	private int defense = 0;
	@JsonProperty("att")
	private int attack = 0;
	@JsonProperty("hostile")
	private boolean hostile = true;
	@JsonProperty("statuses")
	private List<IStatus> statusList;
	@JsonProperty("armr")
	private IArmour armour;
	@JsonProperty("weapon")
	private IWeapon weapon;
	@JsonProperty("chHealth")
	private final boolean checkHealth;
	@JsonProperty("hid")
	private boolean hidden = false;
	@JsonIgnore
	private static final MapUtil MAP_UTIL = MapUtil.getInstance();
	
	public static class Builder extends AbstractGameElement.Builder{
		private int maxHealth;
		private int curHealth;
		private int money = 0;
		private Map<String, IItem> inventory = MapUtil.newHashMap();
		private int defense = 0;
		private int attack = 0;
		private boolean hostile = true;
		private List<IStatus> statusList = new LinkedList<>();
		private IArmour armour;
		private IWeapon weapon;
		private boolean checkHealth = true;
		private boolean hidden = false;
		
		public Builder(){
			super();
		}
		public Builder(Mob b){
			super(b);
			  this.maxHealth = b.maxHealth;
			  this.curHealth = b.curHealth;
			  this.money = b.money;
			  this.inventory = b.inventory;
			  this.defense = b.defense;
			  this.attack = b.attack;
			  this.hostile = b.hostile;
			  this.statusList = b.statusList;
			  this.armour = b.armour;
			  this.weapon = b.weapon;
		}
		public Builder(AbstractGameElement g){
			super(g);
		}
		/**
		 * Indicator for the class doing validation on the health and maxHealth fields.
		 * Default is true.
		 * @param checkHealth
		 * @return
		 */
		@JsonProperty("chHealth")
		public Builder checkHealth(boolean checkHealth){
			this.checkHealth = checkHealth;
			return this;
		}
		/**
		 * The Maximum health of the mob. If not set during build time, defaults to value of health.
		 * @param maxHealth
		 * @return this
		 */
		@JsonProperty("maxHealth")
		public Builder maxHealth(int maxHealth){
			this.maxHealth = maxHealth;
			return this;
		}
		@JsonProperty("health")
		public Builder health(int curHealth){
			this.curHealth = curHealth;
			return this;
		}
		@JsonProperty("money")
		public Builder money(int money){
			this.money = money;
			return this;
		}
		@JsonProperty("inven")
		public Builder inventory(Map<String, IItem> inventory){
			this.inventory = MapUtil.getMapOrNew(inventory);
			return this;
		}
		public Builder addItem(IItem item){
			MAP_UTIL.addItemToMap(inventory, item);
			return this;
		}
		@JsonProperty("def")
		public Builder defense(int defense){
			this.defense = defense;
			return this;
		}
		@JsonProperty("att")
		public Builder attack(int attack){
			this.attack = attack;
			return this;
		}
		@JsonProperty("hid")
		public Builder hidden(boolean hidden){
			this.hidden = hidden;
			return this;
		}
		/**
		 * boolean flag for weather the mob is hostile. Default is true.
		 * @param hostile
		 * @return this
		 */
		@JsonProperty("hostile")
		public Builder hostile(boolean hostile){
			this.hostile = hostile;
			return this;
		}
		@JsonProperty("statuses")
		public Builder statusList(List<IStatus> statusList){
			if (statusList == null){
				this.statusList = new LinkedList<>();
			} else {
				this.statusList = statusList;	
			}
			
			return this;
		}
		@JsonProperty("armr")
		public Builder armour(IArmour armour){
			this.armour = armour;
			return this;
		}
		@JsonProperty("weapon")
		public Builder weapon(IWeapon weapon){
			this.weapon = weapon;
			return this;
		}
		public Builder addStatus(IStatus status){
			statusList.add(status);
			return this;
		}
		public Builder name(String name){
			super.name(name);
			return this;
		}
		public Builder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public Builder viewDescription(String viewDescription){
			super.viewDescription(viewDescription);
			return this;
		}
		public Builder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Mob build(){
			return new Mob(this);
		}
	}
	
	protected Mob( Builder b){
		  super(b);
		  if (b.maxHealth > 0){
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
		  hidden = b.hidden;
	}
	/**
	 * Constructor for only name. <code>checkHealth</code> is set to false.
	 * @param name
	 */
	public Mob(String name){
		super(name);
		checkHealth = false;
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
	@JsonProperty("def")
	public int getBasicDefense() {
		return defense;
	}
	/**
	 * returns attack only. Does not add weapon bonus.
	 * @return
	 */
	@JsonProperty("att")
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

	public void changeMaxHealth(int change){
		setMaxHealth(this.maxHealth + change);
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
		setHealth(this.curHealth + change);
 
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
	public void addStatus(IStatus s){
			statusList.add(s);

	}
	public void changeMoney(int change){
		setMoney(this.money + change);
	}
	public void setMoney(int money){
		this.money = money;
		if (doValidateFields() && this.money < 0){
			this.money = 0;
		}
	}
	public void changeDefense(int change){
		setDefense(this.defense + change);
	}
	public void setDefense(int defense){
		this.defense = defense;
		if (doValidateFields() && this.defense < 0){
			this.defense = 0;
		}
	}
	public void changeAttack(int change){
		setAttack(this.attack + change);
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
	public void setAttack(int attack){
		this.attack = attack;
		if (doValidateFields() && this.attack < 0){
			this.attack = 0;
		}
	}
	/**
	 * gets attack plus any bonuses
	 * @return
	 */
	
	public int getFullAttack(){
		return attack + getWeaponBonus();
	}
	/**
	 * returns the weapon bonus or 0 if no weapon equipped
	 * @return
	 */
	@JsonIgnore
	public int getWeaponBonus(){
		return weapon == null? 0: weapon.getAttack();
	}
	/**
	 * Returns the armour bonus or 0 if no armour equipped
	 * @return
	 */
	@JsonIgnore
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
	public void setStatusList(List<IStatus> s){
		if (s == null){
			statusList = new LinkedList<>();
		} else {
			statusList = s;
		}
	}
	public IItem addItem(IItem add){
		return MAP_UTIL.addItemToMap(inventory, add);
	}
	public IArmour removeArmour() {
		IArmour re = armour;
		armour = null;
		return re;
	}
	public void addAllItems(Map<String, IItem> addMap) {
		inventory.putAll(addMap);
	}
	public IItem removeItem(String key){
		return MAP_UTIL.removeItemFromMap(inventory, key);
	}
	public void setInventory(Map<String, IItem> inventoryNew){
		inventory = MapUtil.getMapOrNew(inventoryNew);
	}
	public Map<String, IItem> removeInventory(){
		Map<String, IItem> returnIn = inventory;
		inventory = MapUtil.newHashMap();
		return returnIn;
	}
	public boolean containsItem(String key){
		return MAP_UTIL.containsKey(inventory, key);
	}
	public int inventorySize() {
		return inventory.size();
	}
	@JsonIgnore
	public boolean isAlive(){
		return !isDead();
	}
	public String toString() {
		return getName();
	}
	public IWeapon removeWeapon() {
		IWeapon re = weapon;
		weapon = null;
		return re;
	}
	@JsonIgnore
	public String getArmourKey(){
		return armour == null? null: armour.getName();
	}
	@JsonIgnore
	public boolean isKeyforArmour(String key){
		return isKeyForItem(key, armour);
	}
	@JsonIgnore
	public boolean isKeyForWeapon(String key){
		return isKeyForItem(key, weapon);
	}
	@JsonIgnore
	public String getWeaponKey(){
		return weapon == null? null: weapon.getName();
	}
	public boolean equals(Object o){
		
		if (this == o){
			return true;
		}
		if (o instanceof Mob){
			if (!super.equals(o)){
				return false;
			}
			Mob m = (Mob) o;

			if (attack != m.attack){
				return false;
			}
			if (defense != m.defense){
				return false;
			}
			if (curHealth != m.curHealth){
				return false;
			}
			if (maxHealth != m.maxHealth){
				return false;
			}
			if (money != m.money){
				return false;
			}
			if (hostile != m.hostile){
				return false;
			}
			if (ObjectsUtil.notEqualKeys(this.inventory, m.inventory)){
				return false;
			}
			if (ObjectsUtil.notEqual(this.armour, m.armour)){
				return false;
			}
			if (ObjectsUtil.notEqual(this.weapon, m.weapon)){
				return false;
			}
			if (ObjectsUtil.notEqual(this.statusList, m.statusList)){
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	public int hashCode(){
		int start = super.hashCode();
		start = start * ObjectsUtil.DEFAULT_PRIME + ObjectsUtil.getKeysHash(inventory);
		return ObjectsUtil.getHashWithStart(start,
				ObjectsUtil.DEFAULT_PRIME, attack, defense,
				curHealth, maxHealth, money, hostile, armour,
				weapon, statusList);
	}
	public String inventoryOverview(){
		return MapUtil.getKeysAsString(inventory);
	}
	public boolean isHidden(){
		return hidden;
	}
	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}
}
