package crawlingdungeon;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jjcard.textGames.Item;
import jjcard.textGames.Location;
import jjcard.textGames.Mob;
import jjcard.textGames.Player;
import jjcard.textGames.Weapon;
import jjcard.textGames.World;

import jjcard.textGames.*;

/**
 * Test game to test out features and bugs
 * @author jjcard
 *
 */
public class CrawlingDungeon {
	
	public static void main(String[] args){
		Location starting = setLocations();
		Map<String, Mob> mobs = getMobs();
		Map<String, Item> items = getItems();
		Player player = new Player("adventurer", 100, 6, 5);
		World world = new World(starting, player);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Crawling Dungeon");
		
		
		while(!world.Quit()){
			System.out.print("> ");
			CommandAndKey ck = world.praseInput(scanner.nextLine());
			world.basicOperations(ck);
		}
		
		System.out.print("The program is now ending");
	}
	
	public static Location setLocations(){
		Location starting = new Location("Starting room", "A mostly empty room");
		Location secondRoom = new Location("second room", " a long hallway");
		starting.addExit("north", secondRoom);
		
		
		return starting;
	}
	public static Map<String, Item> getItems(){
		Map<String, Item> items = new HashMap<String, Item>();
		Weapon sword = new Weapon("wooden sword", "it isn't the best sword, but it will do", 3);
		items.put("wooden sword", sword);
		
		return items;
	}
	public static Map<String, Mob> getMobs(){
		Map<String, Mob> mobs = new HashMap<String, Mob>();
		
		return mobs;
	}

}
