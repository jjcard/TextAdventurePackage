package crawlingdungeon;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import jjcard.textGames.game.IWorld;
import jjcard.textGames.game.impl.*;

/**
 * Test game to test out features and bugs
 * @author jjcard
 *
 */
public class CrawlingDungeon {
	static boolean quit = false;
	
	public static void main(String[] args){
		Location starting = setLocations();
		Map<String, Mob> mobs = getMobs();
		Map<String, Item> items = getItems();
		Player player = new Player.PlayerBuilder().standardName("adventurer").curHelath(100).defense(6).attack(5).build();
		IWorld world = new World(starting, player);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Crawling Dungeon");
		
		
		
		while(!quit){
			

			System.out.print("> ");
			CommandAndKey ck = world.parseInput(scanner.nextLine());
			if (ck.getCommand().equals(Commands.QUIT)){
				quit = true;
			}
			world.executeCommands(ck);
		}
		
		scanner.close();
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
		Weapon sword = new Weapon.WeaponBuilder().standardName("wooden sword").info("it isn't the best sword, but it will do").attack(3).build();
		items.put("wooden sword", sword);
		
		return items;
	}
	public static Map<String, Mob> getMobs(){
		Map<String, Mob> mobs = new HashMap<String, Mob>();
		
		return mobs;
	}

}
