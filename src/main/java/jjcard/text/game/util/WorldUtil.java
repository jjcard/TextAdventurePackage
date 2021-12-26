package jjcard.text.game.util;

import static jjcard.text.game.util.ObjectsUtil.checkArg;

import jjcard.text.game.IArmour;
import jjcard.text.game.IGameElement;
import jjcard.text.game.IItem;
import jjcard.text.game.ILocation;
import jjcard.text.game.IMob;
import jjcard.text.game.IWeapon;
import jjcard.text.game.battle.IBattleSystem;
import jjcard.text.game.parser.TextToken;
import jjcard.text.game.parser.impl.BasicTextTokenType;

/**
 * basic world Util that contains locations and player and operations for commands
 * 
 *  @param <P> the player class
 */
public class WorldUtil<P extends IMob>{
	private ILocation current;
	private P player;
//	private IBattleSystem battleSystem = new BasicBattleSystem();
	/**
	 * Generic enum response used in place of a boolean.
	 * If just success/failure wanted, call {@link #isSuccess()} on the ReturnStatus.
	 *
	 */
	public static enum ReturnStatus{
		SUCCESS(true), FAILURE(false), NOT_FOUND(false);
		private final boolean success;
		private ReturnStatus(boolean success){
			this.success = success;
		}
		/**
		 * call when simple boolean response is needed.
		 * @return true if is {@link #SUCCESS}, false otherwise
		 */
		public boolean isSuccess(){
			return success;
		}
	}
	/**
	 * 
	 * @param current the current location. Must be non-null
	 * @param player  Must be non-null
	 * @throws IllegalArgumentException if the <code>current</code> argument or <code>player</code> argument is <code>null</code>
	 */
	public WorldUtil(ILocation current, P player) throws IllegalArgumentException{
		checkArg(current, "current");
		checkArg(player, "player");
		this.current = current;

		this.player = player;
	}
//	/**
//	 * Returns the battle System for {@link #attackMob(String, TextToken)}
//	 * @return battleSystem
//	 */
//	public IBattleSystem getBattleSystem(){
//		return battleSystem;
//	}
//	/**
//	 * Sets to the given battleSystem, or a BasicBattleSystem if null passed in
//	 * @param battleSystem
//	 */
//	public void setBattleSystem(IBattleSystem battleSystem){
//		if (battleSystem == null){
//			battleSystem = new BasicBattleSystem();
//		} else {
//			this.battleSystem = battleSystem;
//		}
//	}
	public P getPlayer() {
		return player;
	}

	public ILocation getCurrent() {
		return current;
	}

//	public String getCurrentExitsDescrip() {
//		return current.getExitsDescriptions();
//	}
	/**
	 * Sets the player.
	 * @param player
	 * @throws IllegalArgumentException if the <code>player</code> argument is <code>null</code>
	 */
	public void setPlayer(P player) throws IllegalArgumentException{
		checkArg(player, "player");
		this.player = player;
	}
	/**
	 * Sets the current location. Must be non-null.
	 * @throws IllegalArgumentException if the <code>current</code> argument is <code>null</code>
	 */
	public void setCurrent(ILocation current) throws IllegalArgumentException{
		checkArg(current, "current");
		this.current = current;
	}
	/**
	 * Sets the current location to the location from the exit with given key
	 * @param key
	 * @return if current contains exit and current changed
	 */
	public boolean goDirection(String key) {
		if (current.containsExit(key)) {
			current = current.getExitLocation(key);
			return true;
		}
		return false;
	}
	/**
	 * Gets item with given key from current room
	 * @param key
	 * @return
	 */
	public IItem roomGetItem(String key) {
		return current.getItem(key);
	}
	/**
	 * Checks to see if the current room contains item with given key
	 * @param key
	 * @return
	 */
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

	/**
	 * Equips armour for key to player, calling {@link #setPlayerArmour(IArmour)}
	 * and removing the equipped armour from inventory
	 * @param key
	 * @return SUCCESS if player contains item of that armour, NOT_FOUND if player does not,
	 * and FAILURE if item is not a armour
	 */
	public ReturnStatus equipArmour(String key){
		IItem item =  player.getItem(key);
		if (item == null){
			return ReturnStatus.NOT_FOUND;
		} else if (!(item instanceof IArmour)){
			return ReturnStatus.FAILURE;
		}
		IArmour armour = (IArmour) item;
		setPlayerArmour( armour);
		player.removeItem(key);
		return ReturnStatus.SUCCESS;
	}
	/**
	 * Equips weapon for key to player, calling {@link #setPlayerWeapon(IWeapon)}
	 * and removing the equipped weapon from inventory
	 * @param key
	 * @return SUCCESS if player contains item of that weapon, NOT_FOUND if player does not,
	 * and FAILURE if item is not a weapon
	 */
	public ReturnStatus equipWeapon(String key){
		IItem item =  player.getItem(key);
		if (item == null){
			return ReturnStatus.NOT_FOUND;
		} else if (!(item instanceof IWeapon)){
			return ReturnStatus.FAILURE;
		}
		
		IWeapon weapon = (IWeapon) item;
		setPlayerWeapon( weapon);
		player.removeItem(key);
		return ReturnStatus.SUCCESS;
	}

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
	public static boolean unequipWeapon(IMob mob){
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
	public static boolean unequipArmour(IMob mob){
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

	public boolean roomContainsExit(String dir) {
		return current.containsExit(dir);
	}
	/**
	 * 
	 * Gets the view description String depending on key and TokenType.
	 * First checks if TokenType is ROOM or PLAYER, then checks if
	 * room's item, room's mob, player's inventory, or room's exits contain standard token, in that order. 
	 * @param object
	 * @return view description of matching object
	 */
	//TODO only one that still has to be BasicTextTokenType
	public String lookAt(TextToken<BasicTextTokenType> object) {
		
		if (object.getType().equals(BasicTextTokenType.ROOM)) {
			return showCurrentRoom();
		}
		if (object.getType().equals(BasicTextTokenType.PLAYER)) {
			return player.getViewDescription();
		}
		return lookAt(object.getStandardToken());
	}
	/** 
	 * Checks if room's item, room's mob, player's inventory or exits contain key, in that order. 
	 * Returns the view description String if found, <code>null</code> otherwise.
	 *
	 * @param key
	 * @return view description of matching object
	 */
	public String lookAt(String key){
		IGameElement element = getLookedAtElement(key);
		return element == null? null: element.getViewDescription();
	}
	/**
	 * Checks if room's item, room's mob, player's inventory or exits contain key, in that order and returns appropriate IGameElement.
	 * Returns null if not found.
	 * @param key
	 * @return IGameElement for matching object
	 */
	public IGameElement getLookedAtElement(String key){
		if (roomContainsItem(key)) {
			return current.getItem(key);
		}
		if (roomContainsMob(key)) {
			return current.getMob(key);
		}
		if (player.containsItem(key)) {
			return player.getItem(key);
		}
		if (roomContainsExit(key)){
			return current.getExit(key);
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
	/**
	 * Gets the Mob from the current room, checks if it is head, and adds all it's items to the player.
	 * @param key
	 * @return SUCCESS if added, FAILURE if mob is still alive, and NOT_FOUND if no mob found
	 */
	public ReturnStatus lootAllMob(String key) {
		if (current.containsMob(key)) {
			IMob lootM = current.getMob(key);
			if (lootM.isDead()) {
				player.addAllItems(lootM.removeInventory());
				return ReturnStatus.SUCCESS;
			} else {
				return ReturnStatus.FAILURE;
			}
		}
		return ReturnStatus.NOT_FOUND;
	}
	
	public <R> R attackMob(String key, IBattleSystem<R> battleSystem) throws NotFoundException{
		if (current.containsMob(key)){
			return battleSystem.attackMob(player, current.getMob(key));
		} else {
			throw new NotFoundException(key);
		}
	}
	/**
	 * Returns true if items name matches given key
	 * @param key
	 * @param item
	 * @return if matches
	 */
	public static boolean isKeyForItem(String key, IGameElement item){
		if (item == null){
			return false;
		}
		return key.equalsIgnoreCase(item.getName());
	}
}
