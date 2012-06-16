package textGames;

import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Map;
//import java.util.HashMap;
import java.util.Scanner;

public class World {
	Location current;
	Player player;

	private boolean quit = false;
	
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

	public void setQuit(boolean b){
		quit = b;
	}
	public boolean Quit(){
		return quit;
	}
	

	public Location getCurrent(String key){
		return current;
	}
	
	public String getCurrentExits(){
		return current.getExits();
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
	public Location goDirection(Location l, int dir){
		if (l.containsExit(dir)){
			return l.getExitLocation(dir);
		}
		return l;
	}
	public Item roomGetItem(String key){
		
		if (current.containsItem(key)){
			return current.getItem(key);
		}
		return null;
		
	}
	public boolean roomContainsItem(String key){
		return current.containsItem(key);
	}
	public Item playerGetItem(String key){
		Item re = current.getItem(key);
		if (re != null && re.movable()){
			return player.addItem(key, current.getItem(key));
		}
		return null;
				 
	}
	public String showCurrentRoom(){
		return current.showRoom();
	}
	public void setPlayerArmor(Armour armorN){
		Armour add = player.setArmour(armorN);
		if (add != null){
			//taking away the defense from old armour
			player.changeDefense(- add.defense());
			//adding defense from now armour
			player.changeDefense(player.armor().defense());
			//add old armour back to inventory
			player.addItem(add.name(), add);
			
		}
	}
	public void setPlayerWeapon(Weapon weaponN){
		Weapon add = player.setWeapon(weaponN);
		if (add != null){
			player.addItem(add.name(), add);
		}
	}
	public boolean currentContainsExit(String dir){
		return current.containsExit(dir);
	}
	public String basicOperations(String input){
		
		switch(input.toUpperCase()){
		case "HEALTH": return player.health() + "";
		case "NAME": return player.name() + "";
		case "NORTH":
		case "N":
			return movePlayer("NORTH");
		case "SOUTH":
		case "S": 
			return movePlayer("SOUTH");
		case "EAST":
		case "E":
			return movePlayer("EAST");
		case "WEST":
		case "W": 
			return movePlayer("WEST");
		case "INVENTORY": 
			if (player.inventory().isEmpty()){
				return "You have nothing in your inventory";
			}
			return player.inventoryToString();
		case "QUIT": quit = true;
			return "Goodbye";
		case "LEVEL": return player.level() + "";
		
		}
		 if (input.startsWith("go ")){
			 return movePlayer(input.substring(3));
		 }
		 if (input.startsWith("get ")){
			 return getItemFromRoom(input.substring(4));
		 }
		 if (input.startsWith("pick up ")){
			 return getItemFromRoom(input.substring(8));
		 }
		 if (input.startsWith("attack ")){
			 return attackMob(input.substring(7));
		 }if (input.startsWith("loot all ")){
			 return lootAllMob(input.substring(9));
		 }
		 
			 
		
		return "command not understood";
		
	}
	public String movePlayer(String key){
		if (goDirection(key)){
			return current.showRoom();
		}
		return "You can't go that direction.";
	}
	public String getItemFromRoom(String key){
		 if(current.containsItem(key)){
			 if (playerGetItem(key) !=null){
				return key + " was added to your inventory"; 
			 } else {
				 return key + " could not be moved";
			 }
			 
		 } else {
			 return key + " was not found";
		 }
	 }
	public String lootAllMob(String key){
		if (current.containsMob(key)){
			Mob lootM = current.getMob(key);
			if (lootM.isDead()){
				player.addAllItems(current.getMob(key).inventory());
				current.getMob(key).removeInventory();
				return key +"'s items added";
			} else {
				return key + " is still alive!";
			}

		} 
			return "Mob not found";
		
	}
	public String attackMob(String key){
		if (current.containsMob(key)){
			current.getMob(key).changeHealth(-player.attack());
			String re = " you have attacked " + key + " for " + player.attack() + " damage.";
			if (current.getMob(key).isDead()){
				return re + " You have slain " + key + "!";
			} 
			return re;
		}
		return "cannot find "+ key;
	}
	
	private void getStrings(){
		try {
			Scanner scanner = new Scanner(new File("Strings.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
