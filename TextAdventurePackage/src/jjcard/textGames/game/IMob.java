package jjcard.textGames.game;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IMob extends ConcealableGameElement{

	public String getDescription();
	public int getMaxHealth();
	public int getHealth();
	public int getMoney();
	public Map<String, IItem> getInventory();
	public IItem getItem(String key);
	public IArmour getArmour();
	public IWeapon getWeapon();
	/**
	 * returns defense only. Does not add Armour bonus.
	 * @return
	 */
	public int getBasicDefense();
	
	/**
	 * returns attack only. Does not add weapon bonus.
	 * @return
	 */
	public int getBasicAttack();
	public boolean isHostile();
	public List<IStatus> getStatusList();
	public boolean containsStatus(IStatus status);
	public boolean removeStatus(IStatus status);
	
	public String inventoryToString();
	@JsonIgnore
	public String getWeaponKey();
	public boolean isKeyForWeapon(String key);
	public boolean isKeyforArmour(String key);
	@JsonIgnore
	public String getArmourKey();
	
	/**
	 * Removes the weapon and returns the result
	 * @return
	 */
	public IWeapon removeWeapon();
	@JsonIgnore
	public boolean isDead();
	
	public boolean containsItem(String key);
	public void setHealth(int health);
	public IItem removeItem(String key);
	
	public IArmour removeArmour();
	
	public IItem addItem(IItem add);
	public void addAllItems(Map<String, IItem> items);
	/**
	 * gets attack plus any attack bonus
	 * @return
	 */
	@JsonIgnore
	public int getFullAttack();
	/**
	 *  gets defense plus any bonus
	 * @return
	 */
	@JsonIgnore
	public int getFullDefense();
	public IWeapon setWeapon(IWeapon weapon);
	public IArmour setArmour( IArmour armour);
	public void addIStatus(IStatus status);
	/**
	 * Removes the Inventory from the mob and returns the result
	 * @return
	 */
	public Map<String, IItem> removeInventory();
}
