package jjcard.textGames.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
//import java.util.Map;
//import java.util.HashMap;
import java.util.HashMap;
import java.util.Scanner;



/**
 * basic world that contains locations and player and operations for commands
 * @author jjcard
 *
 */
public class World {
	Location current;
	 Player player;
	PrintStream output = System.out;

	//private boolean quit = false;
	
	public World(){
		current = new Location();
		//Locations = new HashMap<String, Location>();
//		Mobs = new HashMap<String, Mob>();
//		Items = new HashMap<String, Item>();
	}

	public World( Location newCur){
		//Locations = newLoc;
		current = newCur;
	}
	public World(Location newCur, Player playerN){
		current = newCur;
		player = playerN;
	}

//	public void setQuit(boolean b){
//		quit = b;
//	}
//	public boolean Quit(){
//		return quit;
//	}
	
	public Player getPlayer(){
		return player;
	}
	public Location getCurrent(){
		return current;
	}
	public PrintStream getOuput(){
		return output;
	}
	public void setOutput(PrintStream out){
		output = out;
	}
	
	public String getCurrentExitsDescrip(){
		return current.getExitsDescrip();
	}
	public void setPlayer(Player playerN){
		player = playerN;
	}
	public void setCurrent(Location locationN){
		current = locationN;
	}
	public boolean goDirection(String dir){
		if (current.containsExit(dir)){
			current = current.getExitLocation(dir);
			return true;
		}
		return false;
	}
	public boolean goDirection(int dir){
		if (current.containsExit(dir)){
			//System.out.println("contains exit");
			//System.out.println(current.getName());
			current = current.getExitLocation(dir);
			return true;
		}
		return false;
	}
	public Location goDirection(Location l, String dir){
		if (l.containsExit(dir)){
			return l.getExitLocation(dir);
		}
			return l;
	}
	/**
	 * using direction int from Location and returns new Location
	 * @param l
	 * @param dir 
	 * @return
	 */
	public Location goDirection(Location l, int dir){
		if (l.containsExit(dir)){
			return l.getExitLocation(dir);
		}
		return l;
	}
	public Item roomGetItem(String key){
		
		//if (current.containsItem(key)){
			return current.getItem(key);
		//}
		//return null;
		
	}
	public boolean roomContainsItem(String key){
		return current.containsItem(key);
	}
	public boolean playerGetItem(String key){
		Item re = current.getItem(key);
		if (re != null && re.getMovable()){
			 player.addItem(key, re);
			 return true;
		}
		return false;
				 
	}
	public String showCurrentRoom(){
		return current.showRoom();
	}
	public void setPlayerArmor(Armour armorN){
		Armour add = player.setArmour(armorN);
		if (add != null){
			//taking away the defense from old armour
			player.changeDefense(- add.getDefense());
			//adding defense from now armour
			player.changeDefense(player.getArmor().getDefense());
			//add old armour back to inventory
			player.addItem(add.getName(), add);
			
		}
	}
	public void setPlayerWeapon(Weapon weaponN){
		Weapon add = player.setWeapon(weaponN);
		if (add != null){
			player.addItem(add.getName(), add);
			player.changeAttack(- add.getAttack());
			player.changeAttack(player.getWeapon().getAttack());
			
		}
	}
	public boolean currentContainsExit(String dir){
		return current.containsExit(dir);
	}
	/**
	 * does basic operations with givin CommandsAndKey givin and returns ReturnCom
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
		 default:
			 return ReturnCom.COMMAND_NOT_FOUND;
			
		
		}
	}
	
	
	/**
	 * does basic parsing for input Strings and returns the Command and and key attached to it
	 * @param input String
	 * @return CommandAndKey 
	 */
	public CommandAndKey praseInput(String input){
		
		switch(input.toUpperCase()){
		case "HEALTH": return new CommandAndKey(Commands.PLAYER_INFO, "health");
				//player.getHealth() + "";
		case "NAME": return new CommandAndKey(Commands.PLAYER_INFO, "name");
			//return player.getName() + "";
		case "NORTH":
		case "N":
			 return new CommandAndKey(Commands.MOVE, "NORTH");
					//movePlayer("NORTH");
		case "SOUTH":
		case "S": 
			return new CommandAndKey(Commands.MOVE, "SOUTH");
					//movePlayer("SOUTH");
		case "EAST":
		case "E":
			return new CommandAndKey(Commands.MOVE, "EAST");
					//movePlayer("EAST");
		case "WEST":
		case "W": 
			return new CommandAndKey(Commands.MOVE, "WEST");
					//movePlayer("WEST");
		case "INVENTORY": 
//			if (player.getInventory().isEmpty()){
//				return "You have nothing in your inventory";
//			}
			return new CommandAndKey(Commands.PLAYER_INFO, "inventory");
					//player.inventoryToString();
		case "QUIT": ///quit = true;
			return new CommandAndKey(Commands.QUIT);
					//"Goodbye";
		case "LEVEL": return new CommandAndKey(Commands.PLAYER_INFO, "level");
				//player.getLevel() + "";
		
		}
		 if (input.startsWith("go ")){
			 return new CommandAndKey(Commands.MOVE, input.substring(3));
					 //movePlayer(input.substring(3));
		 }
		 if (input.startsWith("get ")){
			 return new CommandAndKey(Commands.GET, input.substring(4));
					 //getItemFromRoom(input.substring(4));
		 }
		 if (input.startsWith("pick up ")){
			 return new CommandAndKey(Commands.GET, input.substring(8));
					 //getItemFromRoom(input.substring(8));
		 }
		 if (input.startsWith("attack ")){
			 
			 return new CommandAndKey(Commands.ATTACK, input.substring(7));
					 //attackMob(input.substring(7));
		 }if (input.startsWith("loot all ")){
			 return new CommandAndKey(Commands.LOOT_ALL, input.substring(9));
					 //lootAllMob(input.substring(9));
		 } if (input.startsWith("look ")){
			 return new CommandAndKey(Commands.LOOK, input.substring(5));
					 //lookAt(input.substring(5));
		 } if (input.startsWith("drop ")){
			return new CommandAndKey(Commands.DROP, input.substring(5));
		 }
		 
			 
		
		return null;
				//"I don't understand";
		
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
			current.addItem(key, drop);
			System.out.println(key + " dropped");
			return ReturnCom.ITEM_DROPPED;
		} else {
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
			current.getMob(key).changeHealth(-player.getAttack());
			String re = " you have attacked " + key + " for " + player.getAttack() + " damage.\n";
			if (current.getMob(key).isDead()){
				output.println( re + " You have slain " + key + "!");
				return ReturnCom.ATTACK_MOB_KILLED;
			} 
			output.println( re);
			return ReturnCom.ATTACK_MOB;
		}
		 output.println("cannot find "+ key);
		 return ReturnCom.ATTACK_MOB_NOT_FOUND;
	}
	/**
	 * no use yet
	 * @return 
	 */
	public HashMap<String, String> getStringsFromFile(String address){
		try {
			
			Scanner scanner = new Scanner(new File(address));
			scanner.useDelimiter("==\\n*");
			
			HashMap<String, String> strings = new HashMap<String, String>();
			while (scanner.hasNext()){
				String key = scanner.next();
				if (scanner.hasNext()){
					strings.put(key, scanner.next());
				}
				//strings.put(key, scanner.next());
				//System.out.println(scanner.next());
				
				
			}
			return strings;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
