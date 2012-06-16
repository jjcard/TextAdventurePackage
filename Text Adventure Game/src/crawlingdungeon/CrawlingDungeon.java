package crawlingdungeon;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import textGames.*;


public class CrawlingDungeon {
	
	public static void main(String[] args){
		Location starting = setLocations();
		Map<String, Mob> mobs = getMobs();
		Map<String, Item> items = getItems();
		Player player = new Player("adventurer", 100, 6, 5);
		World world = new World(starting, player);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcom to Crawling Dungeon");
		
		
		while(!world.Quit()){
			System.out.print("> ");
			System.out.println(world.basicOperations(scanner.nextLine()));
		}
		
		System.out.print("The program is now ending");
	}
	
	public static Location setLocations(){
		Location starting = new Location("Starting room");
		
		
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
