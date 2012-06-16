package textGames;

public class Blaa {

	
	public static void main(String[] args) {
		Location room1 = new Location("Test room", "it has stuff it in.");
		Location room2 = new Location("room 2", "the second room.");
		room1.addExit("north", room2);
		Location room3 = new Location("room 3", "south of room1.");
		room1.addExit("south", room3);
//		System.out.println(room1.getExits());
//		System.out.println(room1.showRoom());
		Item item = new Item("item");
		Item sword = new Weapon("sword");
		Item leather = new Armour("leather shield");
//		item.setRoomDescrip("the stuff is on the floor");
		room1.addItem("item", item);
		room1.addItem("Sword", sword);
		room2.addItem("leather shield", leather);
		Mob mob = new Mob("giant rat");
		mob.setRoomDescrip("A rat of unusual size stiters across the floor");
		room2.addMob(mob.name(), mob);
//		System.out.println(room1.showRoom());
//		room1.removeItem(item);
//		System.out.println(room1.showRoom());
		Player player = new Player("adventurer", 102);
		
		World world = new World(room1, player);
		world.playerGetItem("item");
		world.playerGetItem("Sword");
		System.out.println(world.showCurrentRoom());
		if (world.currentContainsExit("north")){
			world.goDirection("north");
			System.out.println(world.showCurrentRoom());
			world.playerGetItem("leather shield");
		} else {
			System.out.println("can't find place");
		}
;
		
		System.out.println(player.inventoryToString());
		
		
		
		

	}

}		

