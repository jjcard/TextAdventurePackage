package jjcard.textGames.game.impl;

import java.io.PrintStream;

import jjcard.textGames.game.IArmour;
import jjcard.textGames.game.IItem;
import jjcard.textGames.game.ILocation;
import jjcard.textGames.game.IMob;
import jjcard.textGames.game.IWeapon;
import jjcard.textGames.game.IWorld;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.TextToken;
import jjcard.textGames.game.parser.TextTokenStream;
import jjcard.textGames.game.parser.impl.BasicTextTokenType;

/**
 * basic world that contains locations and player and operations for commands
 * 
 * @author jjcard
 * 
 */
public class World implements IWorld<BasicTextTokenType, ReturnCom> {
	private ILocation current;
	private Player player;
	private PrintStream output = System.out;
	private ITextParser<BasicTextTokenType> parser;

	public World() {
		current = new Location();
	}

	public World(ILocation newCur) {
		current = newCur;
	}

	public World(ILocation newCur, Player playerN) {
		current = newCur;
		player = playerN;
	}

	public void setTextParser(ITextParser<BasicTextTokenType> parser) {
		this.parser = parser;
	}

	public ITextParser<BasicTextTokenType> getTextParser() {
		return parser;
	}

	public Player getPlayer() {
		return player;
	}

	public ILocation getCurrent() {
		return current;
	}

	public PrintStream getOuput() {
		return output;
	}

	public void setOutput(PrintStream out) {
		output = out;
	}

	public String getCurrentExitsDescrip() {
		return current.getExitsDescriptions();
	}

	public void setPlayer(Player playerN) {
		player = playerN;
	}

	public void setCurrent(ILocation locationN) {
		current = locationN;
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

	public ReturnCom equipItem(String i) {
		IItem toE = player.getItem(i);

		if (toE == null) {
			output.println(i + " not found");
			return ReturnCom.EQUIPPED_NOT_FOUND;
		}

		if (toE.getUse().equals(ItemUse.Armour)) {
			setPlayerArmour((IArmour) toE);
			player.removeItem(i);

			output.println(i + " equipped as armour. ");
			return ReturnCom.EQUIPPED_ARMOUR;
		}
		if (toE.getUse().equals(ItemUse.Weapon)) {
			setPlayerWeapon((IWeapon) toE);
			player.removeItem(i);
			output.println(i + " equipped as weapon. ");
			return ReturnCom.EQUIPPED_WEAPON;
		}
		output.println(i + " not found");
		return ReturnCom.EQUIPPED_NOT_FOUND;
	}

	public ReturnCom unequipItem(String key) {
		if (key.equalsIgnoreCase("armour") || key.equalsIgnoreCase("armour")
				|| player.isKeyforArmour(key)) {
			IArmour it = player.removeArmour();
			if (it != null) {

				// add it back to the inventory
				player.addItem(it);
				output.println(key + " has been unequipped from armour. ");
				return ReturnCom.UNEQUIPPED_ARMOUR;
			} else {
				output.println("No armour equipped. ");
				return ReturnCom.UNEQUIPPED_NO_ARMOUR;
			}

		}
		if (key.equalsIgnoreCase("weapon") || player.isKeyForWeapon(key)) {
			IWeapon i = player.removeWeapon();
			if (i != null) {

				player.addItem(i);
				output.println(key + " has been unequipped from weapon. ");
				return ReturnCom.UNEQUIPPED_WEAPON;
			} else {
				output.println("No weapon equipped. ");
				return ReturnCom.UNEQUIPPED_NO_WEAPON;
			}
		}
		output.println("Nothing like that to unequip. ");
		return ReturnCom.UNEQUIPPED_ITEM_NOT_FOUND;
	}

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
	 * @param key
	 *            for weaponN
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

	/**
	 * does basic operations with given CommandsAndKey given and returns
	 * ReturnCom
	 * 
	 * @param comkey
	 * @return ReturnCom detailing what happened
	 */
	public ReturnCom executeCommands(CommandAndKey comkey) {
		String key = comkey.getKey();
		switch (comkey.getCommand()) {
		case ATTACK:
			return attackMob(key);
		case LOOK:
			return lookAt(key);

		case MOVE:
			return movePlayer(key);
		case GET:
			return getItemFromRoom(key);
		case LOOT_ALL:
			return lootAllMob(key);
		case DROP:
			return dropItem(key);
		case EQUIP:
			return equipItem(key);
		case UNEQUIP:
			return unequipItem(key);
		default:
			return ReturnCom.COMMAND_NOT_FOUND;

		}
	}

	/**
	 * does basic parsing for input Strings and returns the Command and and key
	 * attached to it
	 * 
	 * @param input
	 *            String
	 * @return CommandAndKey
	 */
	public TextTokenStream<BasicTextTokenType> parseInput(String input) {
		return parser.parseText(input);
	}

	public ReturnCom lookAt(String key) {
		if (key.equals("room")) {
			output.println(showCurrentRoom());
			return ReturnCom.LOOK_ROOM;
		}
		if (key.equals("player")) {
			output.println(player.getDescription());
			return ReturnCom.LOOK_PLAYER;
		}
		if (roomContainsItem(key)) {
			output.println(current.getItem(key).getInfo());
			return ReturnCom.LOOK_ITEM;
		}
		if (roomContainsMob(key)) {
			output.println(current.getMob(key).getDescription());
			return ReturnCom.LOOK_MOB;

		}
		if (player.containsItem(key)) {
			output.println(player.getItem(key).getInfo());
			return ReturnCom.INFO_PLAYER_ITEM;
		}
		output.println("You don't see anything like that");
		return ReturnCom.INFO_NOT_FOUND;
	}

	public ReturnCom info(String key) {
		if (key.equalsIgnoreCase("inventory")) {
			if (player.getInventory().isEmpty()) {
				output.println("You have nothing in your inventory");
				return ReturnCom.INFO_INVENTORY;
			} else {
				output.println(player.inventoryToString());
				return ReturnCom.INFO_INVENTORY_EMPTY;
			}

		}
		if (key.equalsIgnoreCase("health")) {
			output.println(player.getHealth());
			return ReturnCom.INFO_HEALTH;
		}
		if (key.equalsIgnoreCase("max health")) {
			output.println(player.getMaxHealth());
			return ReturnCom.INFO_MAX_HEALTH;
		}
		if (key.equalsIgnoreCase("money")) {
			output.println(player.getMoney());
			return ReturnCom.INFO_MONEY;
		}
		return ReturnCom.INFO_NOT_FOUND;
	}

	public ReturnCom dropItem(String key) {
		if (player.containsItem(key)) {
			IItem drop = player.removeItem(key);
			current.addItem(drop);
			output.println(key + " dropped from your inventory");
			return ReturnCom.ITEM_DROPPED;
		} else {
			output.println("Item not found");
			return ReturnCom.DROP_ITEM_NOT_FOUND;
		}
	}

	public boolean roomContainsMob(String key) {

		return current.containsMob(key);
	}

	public ReturnCom movePlayer(String key) {
		if (goDirection(key)) {
			output.println(current.showRoom());
			return ReturnCom.MOVED_DIRECTION;

		}
		output.println("You can't go that direction.");
		return ReturnCom.MOVED_NOT_FOUND;
	}

	public ReturnCom getItemFromRoom(String key) {
		if (current.containsItem(key)) {
			if (playerGetItem(key)) {
				output.println(key + " was added to your inventory");
				current.removeItem(key);

				return ReturnCom.GOT_ITEM;
			} else {
				output.println(key + " could not be moved");
				return ReturnCom.GOT_ITEM_UNMOVED;
			}

		} else {
			output.println(key + " was not found");
			return ReturnCom.GOT_ITEM_NOT_FOUND;
		}
	}

	public ReturnCom lootAllMob(String key) {
		if (current.containsMob(key)) {
			IMob lootM = current.getMob(key);
			if (lootM.isDead()) {
				player.addAllItems(current.getMob(key).getInventory());
				current.getMob(key).removeInventory();
				output.println(key + "'s items added");
				return ReturnCom.LOOT_MOB;
			} else {
				output.println(key + " is still alive!");
				return ReturnCom.LOOT_MOB_ALIVE;
			}

		}
		output.println("Mob not found");
		return ReturnCom.LOOT_MOB_NOT_FOUND;
	}

	public ReturnCom attackMob(String key) {
		if (current.containsMob(key)) {
			IMob mob = current.getMob(key);
			mob.attackMob(player.getFullAttack());
			String re = " you have attacked " + key + " for "
					+ player.getFullAttack() + " damage.\n";
			if (mob.isDead()) {
				output.println(re + " You have slain " + key + "!");
				return ReturnCom.ATTACK_MOB_KILLED;
			}
			output.println(re);
			return ReturnCom.ATTACK_MOB;
		}
		output.println("cannot find " + key);
		return ReturnCom.ATTACK_MOB_NOT_FOUND;
	}

	@Override
	public ReturnCom executeCommands(TextTokenStream<BasicTextTokenType> stream) {
		TextToken<BasicTextTokenType> object = stream.getFirstObject();
		String token = object == null ? null : object.getToken();
		if (token != null) {
			switch (stream.getVerb().getType()) {
			case ATTACK:
				return attackMob(token);
			case LOOK:
				return lookAt(token);
			case MOVE:
				return movePlayer(token);
			case GET:
				return getItemFromRoom(token);
			case LOOT:
				return lootAllMob(token);
			case DROP:
				return dropItem(token);
			case EQUIP:
				return equipItem(token);
			case UNEQUIP:
				return unequipItem(token);
			case INFO:
				return info(token);
			default:
				// return ReturnCom.COMMAND_NOT_FOUND;
				break;
			}

			// For objects that can also be used to infer their verbs
			switch (object.getType()) {
			case DIRECTION:
				movePlayer(token);
			case INVENTORY:
				info(token);
			default:
				return ReturnCom.COMMAND_NOT_FOUND;
			}

		} else {
			return ReturnCom.COMMAND_NOT_FOUND;
		}

		// TODO Auto-generated method stub
		/*
		 * String key = comkey.getKey(); switch(comkey.getCommand()){ case
		 * ATTACK: return attackMob(key); case LOOK: return lookAt(key);
		 * 
		 * case MOVE: return movePlayer(key); case GET: return
		 * getItemFromRoom(key); case LOOT_ALL: return lootAllMob(key); case
		 * DROP: return dropItem(key); case EQUIP: return equipItem(key); case
		 * UNEQUIP: return unequipItem(key); default: return
		 * ReturnCom.COMMAND_NOT_FOUND;
		 * 
		 * 
		 * }
		 */
	}

}
