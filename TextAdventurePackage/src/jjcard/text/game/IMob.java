package jjcard.text.game;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IMob extends ConcealableGameElement{
	/**
	 * Returns max health of Mob
	 * @return max health
	 */
	public int getMaxHealth();
	/**
	 * Returns current health of mob
	 * @return current health
	 */
	public int getHealth();
	public int getMoney();
	public Map<String, IItem> getInventory();
	/**
	 * Returns item matching given key from inventory
	 * @param key
	 * @return
	 */
	public IItem getItem(String key);
	public IArmour getArmour();
	public IWeapon getWeapon();
	/**
	 * returns defense only. Does not include any bonuses.
	 * @return basic defense
	 */
	public int getBasicDefense();
	
	/**
	 * returns attack only. Does not include any bonuses.
	 * @return basic attack
	 */
	public int getBasicAttack();
	public boolean isHostile();
	public List<IStatus> getStatusList();
	public boolean containsStatus(IStatus status);
	/**
	 * 
	 * @param status
	 * @return true if status was found and removed
	 */
	public boolean removeStatus(IStatus status);
	
	public String inventoryOverview();
	public boolean isKeyForWeapon(String key);
	public boolean isKeyforArmour(String key);
	
	/**
	 * Removes the weapon and returns the result
	 * @return
	 */
	public IWeapon removeWeapon();
	@JsonIgnore
	public default boolean isDead(){
		return  getHealth() <= 0;
	}
	
	public boolean containsItem(String key);
	public void setHealth(int health);
	public IItem removeItem(String key);
	/**
	 * Remove armour and return it
	 * @return armour
	 */
	public IArmour removeArmour();
	/**
	 * Add Item to inventory
	 * @param add
	 * @return previous item associated with name
	 */
	public IItem addItem(IItem add);
	public void addAllItems(Map<String, IItem> items);
	/**
	 * gets attack plus any attack bonuses
	 * @return full attack
	 */
	@JsonIgnore
	public int getFullAttack();
	/**
	 *  gets defense plus any bonuses
	 * @return full defense
	 */
	@JsonIgnore
	public int getFullDefense();
	/**
	 * sets weapon
	 * @param weapon
	 * @return previous weapon
	 */
	public IWeapon setWeapon(IWeapon weapon);
	/**
	 * sets armour
	 * @param armour
	 * @return previous armour
	 */
	public IArmour setArmour( IArmour armour);
	public void addStatus(IStatus status);
	/**
	 * Removes the Inventory from the mob and returns the result
	 * @return inventory
	 */
	public Map<String, IItem> removeInventory();
}
