package jjcard.textGames.game.util;

import jjcard.textGames.game.IArmour;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.IWeapon;
import jjcard.textGames.game.battle.IBattleSystem;
import jjcard.textGames.game.battle.impl.BasicBattleSystem;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;

/**
 * basic world Util that contains locations and player and operations for commands
 * 
 * @author jjcard
 * 
 */
public class WorldUtil<P extends IMob>{
	private ILocation current;
	private P player;
	private IBattleSystem battleSystem = new BasicBattleSystem();
	
	public static enum ReturnStatus{
		SUCCESS, FAILURE, NOT_FOUND
	}
	/**
	 * 
	 * @param current the current location. Must be non-null
	 * @param player
	 * @throws NullPointerException if the <code>current</code> argument or <code>player</code> argument is <code>null</code>
	 */
	public WorldUtil(ILocation current, P player) throws NullPointerException{
		if (current == null){
			throw new NullPointerException("current");
		}
		if (player == null){
			throw new NullPointerException("player");
		}
		this.current = current;

		this.player = player;
	}
	/**
	 * Returns the battle System for {@link #attackMob(String, TextToken)}
	 * @return
	 */
	public IBattleSystem getBattleSystem(){
		return battleSystem;
	}
	public void setBattleSystem(IBattleSystem battleSystem){
		this.battleSystem = battleSystem;
	}
	public P getPlayer() {
		return player;
	}

	public ILocation getCurrent() {
		return current;
	}

	public String getCurrentExitsDescrip() {
		return current.getExitsDescriptions();
	}
	/**
	 * Sets the player.
	 * @param player
	 * @throws NullPointerException if the <code>player</code> argument is <code>null</code>
	 */
	public void setPlayer(P player) throws NullPointerException{
		if (player == null){
			throw new NullPointerException("player");
		}
		this.player = player;
	}
	/**
	 * Sets the current location. Must be non-null.
	 * @throws NullPointerException if the <code>current</code> argument is <code>null</code>
	 */
	public void setCurrent(ILocation current) throws NullPointerException{
		if (current == null){
			throw new NullPointerException("current");
		}
		this.current = current;
	}

	public boolean goDirection(String dir) {
		if (current.containsExit(dir)) {
			current = current.getExitLocation(dir);
			return true;
		}
		return false;
	}

	public IItem roomGetItem(String key) {
		return current.getItem(key);
	}

	public boolean roomContainsItem(String key) {
		return current.containsItem(key);
	}
	/**
	 * Retrieves Item from room with <code>key</code>. If found, adds Item to player.
	 * @param key
	 * @return true if item found in current room, false otherwise
	 */
	public boolean playerGetItem(String key) {
		IItem re = current.getItem(key);
		if (re != null && re.canGet()) {
			player.addItem(re);
			return true;
		}
		return false;
	}

	public String showCurrentRoom() {
		return current.showRoom();
	}

//	public ReturnCom equipItem(String i) {
//		IItem toE = player.getItem(i);
//
//		if (toE == null) {
//			return ReturnCom.EQUIPPED_NOT_FOUND;
//		}
//
//		if (toE.getUse().equals(ItemUse.Armour)) {
//			setPlayerArmour((IArmour) toE);
//			player.removeItem(i);
//			return ReturnCom.EQUIPPED_ARMOUR;
//		}
//		if (toE.getUse().equals(ItemUse.Weapon)) {
//			setPlayerWeapon((IWeapon) toE);
//			player.removeItem(i);
//			return ReturnCom.EQUIPPED_WEAPON;
//		}
//		return ReturnCom.EQUIPPED_NOT_FOUND;
//	}
	/**
	 * Equips armour for key to player, calling {@link #setPlayerArmour(IArmour)}
	 * and removing the equiped armour from inventory
	 * @param key
	 */
	public void equipArmour(String key){
		IArmour armour = (IArmour) player.getItem(key);
		setPlayerArmour( armour);
		player.removeItem(key);
	}
	/**
	 * Equips weapon for key to player, calling {@link #setPlayerWeapon(IWeapon)}
	 * and removing the equiped weapon from inventory
	 * @param key
	 */
	public void equipWeapon(String key){
		IWeapon weapon = (IWeapon) player.getItem(key);
		setPlayerWeapon( weapon);
		player.removeItem(key);
	}
//	public ReturnCom unequipItem(String key) {
//		if (key.equalsIgnoreCase("armour") || key.equalsIgnoreCase("armor")
//				|| player.isKeyforArmour(key)) {
//			return unequipArmour();
//		}
//		if (key.equalsIgnoreCase("weapon") || player.isKeyForWeapon(key)) {
//			return unequipWeapon();
//		}
//		return ReturnCom.UNEQUIPPED_ITEM_NOT_FOUND;
//	}
	/**
	 * Removes weapon from Player and adds it to his/her items.
	 * @return true if weapon unequiped, false otherwise
	 */
	public boolean unequipWeapon(){
		return unequipWeapon(player);
	}
	/**
	 * Removes weapon from mob and adds it to his/her items.
	 * @return true if weapon unequiped, false otherwise
	 */
	public boolean unequipWeapon(IMob mob){
		IWeapon i = mob.removeWeapon();
		if (i != null) {
			mob.addItem(i);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Removes armour from Player and adds it to his/her items.
	 * @return true if armour unequiped, false otherwise
	 */
	public boolean unequipArmour(){
		return unequipArmour(player);
	}
	/**
	 * Removes armour from mob and adds it to his/her items.
	 * @return true if armour unequiped, false otherwise
	 */
	public boolean unequipArmour(IMob mob){
		IArmour it = mob.removeArmour();
		if (it != null) {
			// add it back to the inventory
			mob.addItem(it);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Equips given armour to player. adds old armour back to inventory.
	 * @param armourN
	 */
	public void setPlayerArmour(IArmour armourN) {
		IArmour add = player.setArmour(armourN);
		if (add != null) {
			// add old armour back to inventory
			player.addItem(add);
		}
	}

	/**
	 * equips given weapon to player. adds old weapon back to inventory.
	 * 
	 * @param weaponN
	 */
	public void setPlayerWeapon(IWeapon weaponN) {
		IWeapon add = player.setWeapon(weaponN);
		if (add != null) {
			// add old weapon to inventory
			player.addItem(add);
		}
	}

	public boolean currentContainsExit(String dir) {
		return current.containsExit(dir);
	}

	public String lookAt(String key, TextToken<BasicTextTokenType> object) {
		if (object.getType().equals(BasicTextTokenType.ROOM)) {
			return showCurrentRoom();
		}
		if (object.getType().equals(BasicTextTokenType.PLAYER)) {
			return player.getDescription();
		}
		if (roomContainsItem(key)) {
			return current.getItem(key).getInfo();
		}
		if (roomContainsMob(key)) {
			return current.getMob(key).getDescription();
		}
		if (player.containsItem(key)) {
			return player.getItem(key).getInfo();
		}
		return null;
	}

//	public String info(String key, TextToken<BasicTextTokenType> token) {
//		
//		switch (token.getType()){
//		case INVENTORY:
//			if (player.getInventory().isEmpty()) {
//				return ReturnCom.INFO_INVENTORY_EMPTY;
//			} else {
//				return player.inventoryToString();
//			}
//		case MONEY:
//			return String.valueOf(player.getMoney());
//		case HEALTH:
//			return String.valueOf(player.getHealth());
//		case MAX_HEALTH:
//			return String.valueOf(player.getMaxHealth());
//		default:
//			return ReturnCom.INFO_NOT_FOUND;
//		}
//	}
	/**
	 * Remove item with key from player and add it to the current location.
	 * @param key
	 * @return returns true if item removed from player, false otherwise
	 */
	public boolean dropItem(String key) {
		if (player.containsItem(key)) {
			IItem drop = player.removeItem(key);
			current.addItem(drop);
			return true;
		} else {
			return false;
		}
	}

	public boolean roomContainsMob(String key) {
		return current.containsMob(key);
	}

	public ReturnStatus getItemFromRoom(String key) {
		if (current.containsItem(key)) {
			if (playerGetItem(key)) {
				current.removeItem(key);

				return ReturnStatus.SUCCESS;
			} else {
				return ReturnStatus.FAILURE;
			}

		} else {
			return ReturnStatus.NOT_FOUND;
		}
	}

	public ReturnStatus lootAllMob(String key) {
		if (current.containsMob(key)) {
			IMob lootM = current.getMob(key);
			if (lootM.isDead()) {
				player.addAllItems(current.getMob(key).getInventory());
				current.getMob(key).removeInventory();
				return ReturnStatus.SUCCESS;
			} else {
				return ReturnStatus.FAILURE;
			}

		}
		return ReturnStatus.NOT_FOUND;
	}
	/**
	 * Returns number of damage done to mob, or -1 if mob not found
	 * @param key
	 * @return
	 */
	public int attackMob(String key) {
		if (current.containsMob(key)) {
			IMob mob = current.getMob(key);
			return battleSystem.attackMob(player, mob);
		}
		return -1;
	}
}
