package jjcard.text.game;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IMob extends ConcealableGameElement {
	/**
	 * Returns max health of Mob
	 * @return max health
	 */
	int getMaxHealth();
	/**
	 * Returns current health of mob
	 * @return current health
	 */
	int getHealth();
	int getMoney();
	Map<String, IItem> getInventory();
	/**
	 * Returns item matching given key from inventory
	 * @param key
	 * @return Item
	 */
	IItem getItem(String key);
	IArmour getArmour();
	IWeapon getWeapon();
	/**
	 * returns defense only. Does not include any bonuses.
	 * @return basic defense
	 */
	int getBasicDefense();
	
	/**
	 * returns attack only. Does not include any bonuses.
	 * @return basic attack
	 */
	int getBasicAttack();
	boolean isHostile();
	List<IStatus> getStatusList();
	boolean containsStatus(IStatus status);
	/**
	 * 
	 * @param status
	 * @return true if status was found and removed
	 */
	boolean removeStatus(IStatus status);
	
	String inventoryOverview();
	boolean isKeyForWeapon(String key);
	boolean isKeyforArmour(String key);
	
	/**
	 * Removes the weapon and returns the result
	 * @return
	 */
	IWeapon removeWeapon();
	@JsonIgnore
	default boolean isDead(){
		return  getHealth() <= 0;
	}
	
	boolean containsItem(String key);
	void setHealth(int health);
	IItem removeItem(String key);
	/**
	 * Remove armour and return it
	 * @return armour
	 */
	IArmour removeArmour();
	/**
	 * Add Item to inventory
	 * @param add
	 * @return previous item associated with name
	 */
	IItem addItem(IItem add);
	void addAllItems(Map<String, IItem> items);
	/**
	 * gets attack plus any attack bonuses
	 * @return full attack
	 */
	@JsonIgnore
	int getFullAttack();
	/**
	 *  gets defense plus any bonuses
	 * @return full defense
	 */
	@JsonIgnore
	int getFullDefense();
	/**
	 * sets weapon
	 * @param weapon
	 * @return previous weapon
	 */
	IWeapon setWeapon(IWeapon weapon);
	/**
	 * sets armour
	 * @param armour
	 * @return previous armour
	 */
	IArmour setArmour(IArmour armour);
	void addStatus(IStatus status);
	/**
	 * Removes the Inventory from the mob and returns the result
	 * @return inventory
	 */
	Map<String, IItem> removeInventory();
}
