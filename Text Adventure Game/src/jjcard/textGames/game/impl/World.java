package jjcard.textGames.game.impl;

import java.io.PrintStream;

import jjcard.textGames.game.ILocation;



/**
 * basic world that contains locations and player and operations for commands
 * @author jjcard
 *
 */
public class World {
	ILocation current;
	 Player player;
	PrintStream output = System.out;

	
	public World(){
		current = new Location();
	}

	public World( ILocation newCur){
		current = newCur;
	}
	public World(ILocation newCur, Player playerN){
		current = newCur;
		player = playerN;
	}

	public Player getPlayer(){
		return player;
	}
	public ILocation getCurrent(){
		return current;
	}
	public PrintStream getOuput(){
		return output;
	}
	public void setOutput(PrintStream out){
		output = out;
	}
	
	public String getCurrentExitsDescrip(){
		return current.getExitsDescriptions();
	}
	public void setPlayer(Player playerN){
		player = playerN;
	}
	public void setCurrent(ILocation locationN){
		current = locationN;
	}
	public boolean goDirection(String dir){
		if (current.containsExit(dir)){
			current = current.getExitLocation(dir);
			return true;
		}
		return false;
	}


	public Item roomGetItem(String key){
			return current.getItem(key);
		
	}
	public boolean roomContainsItem(String key){
		return current.containsItem(key);
	}
	public boolean playerGetItem(String key){
		Item re = current.getItem(key);
		if (re != null && re.canGet()){
			 player.addItem(re);
			 return true;
		}
		return false;
				 
	}
	public String showCurrentRoom(){
		return current.showRoom();
	}
	public ReturnCom equipItem(String i){
		Item toE = player.getItem(i);
		
		if (toE == null){
			System.out.println(i + " not found");
			return ReturnCom.EQUIPPED_NOT_FOUND;
		}
		
		if (toE.getUse().equals(ItemUse.Armour)){
			setPlayerArmor( (Armour) toE);
			player.removeItem(i);
			
			System.out.println(i + " equipped as armor. ");
			return ReturnCom.EQUIPPED_ARMOUR;
		}
		if (toE.getUse().equals(ItemUse.Weapon)){
			setPlayerWeapon( (Weapon) toE);
			player.removeItem(i);
			System.out.println(i + " equipped as weapon. ");
			return ReturnCom.EQUIPPED_WEAPON;
		}
		System.out.println(i + " not found");
		return ReturnCom.EQUIPPED_NOT_FOUND;
	}
	public ReturnCom unequipItem(String key){
		if (key.equalsIgnoreCase("armor") || key.equalsIgnoreCase("armour") ||player.isKeyforArmor(key)){
			Armour it = player.removeArmour();
			if (it != null){

				//add it back to the inventory
				player.addItem( it);
				System.out.println(key + " has been unequipped from armor. ");
				return ReturnCom.UNEQUIPPED_ARMOUR;				
			} else {
				System.out.println("No armor equipped. ");
				return ReturnCom.UNEQUIPPED_NO_ARMOUR;
			}

		}
		if (key.equalsIgnoreCase("weapon") || player.isKeyForWeapon(key)){
			Weapon i = player.removeWeapon();
			if (i != null){

				player.addItem( i);
				System.out.println(key + " has been unequipped from weapon. ");
				return ReturnCom.UNEQUIPPED_WEAPON;
			} else {
				System.out.println("No weapon equipped. ");
				return ReturnCom.UNEQUIPPED_NO_WEAPON;
			}
		}
		System.out.println("Nothing like that to unequip. ");
		return ReturnCom.UNEQUIPPED_ITEM_NOT_FOUND;
	}
	public void setPlayerArmor( Armour armorN){
		Armour add = player.setArmour( armorN);
		if (add != null){
			//add old armour back to inventory
			player.addItem( add);
			
		}

	}
	/**
	 * equips given weapon to player. adds old weapon back to inventory.
	 * @param key for weaponN
	 * @param weaponN
	 */
	public void setPlayerWeapon( Weapon weaponN){
		Weapon add = player.setWeapon(weaponN);
		if (add != null){
			//add old weapon to inventory
			player.addItem( add);

		}
	}
	public boolean currentContainsExit(String dir){
		return current.containsExit(dir);
	}
	/**
	 * does basic operations with given CommandsAndKey given and returns ReturnCom
	 * @param comkey
	 * @return ReturnCom detailing what happened
	 */
	public ReturnCom basicOperations(CommandAndKey comkey){
		String key = comkey.getKey();
		switch(comkey.getCommand()){
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
	 * does basic parsing for input Strings and returns the Command and and key attached to it
	 * @param input String
	 * @return CommandAndKey 
	 */
	public CommandAndKey parseInput(String input){
		String inputUp = input.toUpperCase();
		
		switch(inputUp){
		case "HEALTH": return new CommandAndKey(Commands.PLAYER_INFO, "health");
		case "NAME": return new CommandAndKey(Commands.PLAYER_INFO, "name");
		case "NORTH":
		case "N":
			 return new CommandAndKey(Commands.MOVE, "NORTH");
		case "SOUTH":
		case "S": 
			return new CommandAndKey(Commands.MOVE, "SOUTH");
		case "EAST":
		case "E":
			return new CommandAndKey(Commands.MOVE, "EAST");
		case "WEST":
		case "W": 
			return new CommandAndKey(Commands.MOVE, "WEST");
		case "NW":
		case "NORTHWEST":
		case "NORTH WEST":
			return new CommandAndKey(Commands.MOVE, "NORTHWEST");
		case "NE":
		case "NORTHEAST":
			return new CommandAndKey(Commands.MOVE, "NORTHEAST");
		case "SW":
		case "SOUTHWEST":
			return new CommandAndKey(Commands.MOVE, "SOUTHWEST");
		case "UP":
			return new CommandAndKey(Commands.MOVE, "UP");
		case "DOWN":
			return new CommandAndKey(Commands.MOVE, "DOWN");
		case "INVENTORY": 
			return new CommandAndKey(Commands.PLAYER_INFO, "inventory");
		case "QUIT": 
			return new CommandAndKey(Commands.QUIT);
		case "LEVEL": 
			return new CommandAndKey(Commands.PLAYER_INFO, "level");
		
		}
		
		 if (inputUp.startsWith("GO ")){
			 return new CommandAndKey(Commands.MOVE, input.substring(3));
		 }
		 if (inputUp.startsWith("GET ")){
			 return new CommandAndKey(Commands.GET, input.substring(4));
		 }
		 if (inputUp.startsWith("MOVE ")){
			 return new CommandAndKey(Commands.MOVE, input.substring(5));
		 }
		 if (inputUp.startsWith("PICK UP ")){
			 return new CommandAndKey(Commands.GET, input.substring(8));
		 }
		 if (inputUp.startsWith("WALK ")){
			 return new CommandAndKey(Commands.MOVE, input.substring(5));
		 }
		 if (inputUp.startsWith("ATTACK ")){
			 return new CommandAndKey(Commands.ATTACK, input.substring(7));
		 }if (inputUp.startsWith("LOOT ALL ")){
			 return new CommandAndKey(Commands.LOOT_ALL, input.substring(9));
		 } if (inputUp.startsWith("LOOK AT ")){
			 return new CommandAndKey(Commands.LOOK, input.substring(8));
		 }if (inputUp.startsWith("LOOK ")){
			 return new CommandAndKey(Commands.LOOK, input.substring(5));
		 } if (inputUp.startsWith("DROP ")){
			return new CommandAndKey(Commands.DROP, input.substring(5));
		 }
		 if (inputUp.startsWith("EQUIP ")){
			 return new CommandAndKey(Commands.EQUIP, input.substring(6));
		 }
		 if (inputUp.startsWith("UNEQUIP ")){
			 return new CommandAndKey(Commands.UNEQUIP, input.substring(8));
		 }		 
			 
		return null;
		
		
	}
	
	public ReturnCom lookAt(String key){
		if (key.equals("room")){
			 output.println(showCurrentRoom());
			 return ReturnCom.LOOK_ROOM;
		}
		if (key.equals("player")){
			 output.println(player.getDescription());
			 return ReturnCom.LOOK_PLAYER;
		}
		if (roomContainsItem(key)){
				output.println(current.getItem(key).getInfo());
				return ReturnCom.LOOK_ITEM;
		} if (roomContainsMob(key)){
			 output.println(current.getMob(key).getDescription());
			 return ReturnCom.LOOK_MOB;
			

		} if (player.containsItem(key)){
			output.println(player.getItem(key).getInfo());
			return ReturnCom.INFO_PLAYER_ITEM;
		}
		  output.println("You don't see anything like that");
		  return ReturnCom.INFO_NOT_FOUND;
	}
	public ReturnCom info(String key){
		if (key.equalsIgnoreCase("inventory")){
			if (player.getInventory().isEmpty()){
				output.println("You have nothing in your inventory");
				return ReturnCom.INFO_INVENTORY;
			}else {
				output.println(player.inventoryToString());
				return ReturnCom.INFO_INVENTORY_EMPTY;
			}
			
		}
		if (key.equalsIgnoreCase("health")){
			output.println( player.getHealth());
			return ReturnCom.INFO_HEALTH;
		}
		if (key.equalsIgnoreCase("max health")){
			output.println(player.getMaxHealth());
			return ReturnCom.INFO_MAX_HEALTH;
		}
		if (key.equalsIgnoreCase("money")){
			output.println(player.getMoney());
			return ReturnCom.INFO_MONEY;
		}
		return ReturnCom.INFO_NOT_FOUND;
	}
	
	public ReturnCom dropItem(String key){
		if (player.containsItem(key)){
			Item drop = player.removeItem(key);
			current.addItem(drop);
			System.out.println(key + " dropped from your inventory");
			return ReturnCom.ITEM_DROPPED;
		} else {
			System.out.println("Item not found");
			return ReturnCom.DROP_ITEM_NOT_FOUND;
		}
	}
	public boolean roomContainsMob(String key){
		
		return current.containsMob(key);
	}
	public ReturnCom movePlayer(String key){
		if (goDirection(key)){
			output.println(current.showRoom());
			return ReturnCom.MOVED_DIRECTION;
			 
		}
		output.println("You can't go that direction.");
		return ReturnCom.MOVED_NOT_FOUND;
	}
	
	public ReturnCom getItemFromRoom(String key){
		 if(current.containsItem(key)){
			 if (playerGetItem(key) ){
				output.println(key + " was added to your inventory"); 
				current.removeItem(key);
				
				return ReturnCom.GOT_ITEM;
			 } else {
				 output.println( key + " could not be moved");
				 return ReturnCom.GOT_ITEM_UNMOVED;
			 }
			 
		 } else {
			 output.println(key + " was not found");
			 return ReturnCom.GOT_ITEM_NOT_FOUND;
		 }
	 }
	public ReturnCom lootAllMob(String key){
		if (current.containsMob(key)){
			Mob lootM = current.getMob(key);
			if (lootM.isDead()){
				player.addAllItems(current.getMob(key).getInventory());
				current.getMob(key).removeInventory();
				output.println( key +"'s items added");
				return ReturnCom.LOOT_MOB;
			} else {
				output.println(key + " is still alive!");
				return ReturnCom.LOOT_MOB_ALIVE;
			}

		} 
			output.println("Mob not found");
			return ReturnCom.LOOT_MOB_NOT_FOUND;
	}
	public ReturnCom attackMob(String key){
		if (current.containsMob(key)){
			Mob mob = current.getMob(key);
			mob.attackMob(player.getFullAttack());
			String re = " you have attacked " + key + " for " + player.getFullAttack() + " damage.\n";
			if (mob.isDead()){
				output.println( re + " You have slain " + key + "!");
				return ReturnCom.ATTACK_MOB_KILLED;
			} 
			output.println( re);
			return ReturnCom.ATTACK_MOB;
		}
		 output.println("cannot find "+ key);
		 return ReturnCom.ATTACK_MOB_NOT_FOUND;
	}


}
