package jjcard.textGames.game;

import java.util.List;

import jjcard.textGames.game.impl.Armour;
import jjcard.textGames.game.impl.Status;
import jjcard.textGames.game.impl.Weapon;

public interface IMob extends IGameElement{

	public String getDescription();
	public int getMaxHealth();
	public int getHealth();
	public int getMoney();
	public IGameElementMap<IItem> getInventory();
	public IItem getItem(String key);
	public Armour getArmor();
	public Weapon getWeapon();
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
	public List<Status> getStatusList();
	public boolean containsStatus(Status s);
	public boolean removeStatus(Status s);
	/**
	 * Sets the weapon to Weapon in the inventory with given name
	 * @param a
	 * @return
	 */
	public Weapon setWeapon(String weaponName);
	/**
	 * Sets the armor to armor in the inventory with given name
	 * @param a
	 * @return
	 */
	public Armour setArmour(String armorName);
	
	public String inventoryToString();
	public String getStandardWeaponKey();
	public boolean isKeyForWeapon(String key);
	public boolean isKeyforArmor(String key);
	public String getStandardArmorKey();
	
	/**
	 * Removes the weapon and returns the result
	 * @return
	 */
	public Weapon removeWeapon();
	
	public boolean isDead();
	
	public boolean containsItem(String key);
	/**
	 * attack this mob for this amount of damage minus any defenses of the Mob. 
	 * Returns new health
	 * @param attack
	 * @return
	 */
	public int attackMob(int damage);
	
	public IItem removeItem(String key);
	
	public Armour removeArmour();
	
	public IItem addItem(IItem add);
	/**
	 * gets attack plus any attack bonuses
	 * @return
	 */
	public int getFullAttack();
	
	public Weapon setWeapon(Weapon w);
	public Armour setArmour( Armour a);
	public void addStatus(Status s);
	
	public void removeInventory();
}
