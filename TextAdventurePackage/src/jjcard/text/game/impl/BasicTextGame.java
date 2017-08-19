package jjcard.text.game.impl;

import static jjcard.text.game.util.ObjectsUtil.checkArg;

import java.io.PrintStream;
import java.util.Scanner;

import jjcard.text.game.IItem;
import jjcard.text.game.ILocation;
import jjcard.text.game.battle.IBattleSystem;
import jjcard.text.game.battle.impl.BasicBattleSystem;
import jjcard.text.game.parser.ITextParser;
import jjcard.text.game.parser.ITextTokenStream;
import jjcard.text.game.parser.TextToken;
import jjcard.text.game.parser.impl.BasicTextParser;
import jjcard.text.game.parser.impl.BasicTextTokenType;
import jjcard.text.game.util.NotFoundException;
import jjcard.text.game.util.WorldUtil;
import jjcard.text.game.util.WorldUtil.ReturnStatus;

public class BasicTextGame extends TextGame<BasicTextTokenType, Player>{
	private WorldUtil<Player> worldUtil;
	private PrintStream output = System.out;
	private Scanner inputScanner;
	//TODO how to handle when mob is dead, can't remove it, since still has items...
	private IBattleSystem<Integer> battleSystem = new BasicBattleSystem(
			(a, d, damage) -> {output.println(" you have attacked " + d.getName() + " for " + damage + " damage.\n");output.println("You have slain " + d.getName() + "!");}, 
			(a, d, damage) -> output.println(" you have attacked " + d.getName() + " for " + damage + " damage.\n")
	);
	
	/**
	 * 
	 * @param current the current location. Must be non-null
	 * @param player
	 * @throws IllegalArgumentException if the <code>current</code> argument or <code>player</code> argument is <code>null</code>
	 */
	public BasicTextGame(ILocation current, Player player) throws IllegalArgumentException {
		checkArg(current, "current");
		checkArg(player, "player");
		this.player = player;
		worldUtil = new WorldUtil<>(current, player);
		parser = new BasicTextParser<>();
		inputScanner = new Scanner(System.in);
	}
	
	public ILocation getCurrent() {
		return worldUtil.getCurrent();
	}
	public void setTextParser(ITextParser<BasicTextTokenType> parser) {
		this.parser = parser;
	}
	public void setScanner(Scanner inputScanner){
		this.inputScanner = inputScanner;
	}
	public ITextParser<BasicTextTokenType> getTextParser() {
		return parser;
	}
	public PrintStream getOuput() {
		return output;
	}

	public void setOutput(PrintStream out) {
		output = out;
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}
	public Player getPlayer(){
		return player;
	}
	@Override
	protected String getInput() {
		return inputScanner.nextLine();
	}

	@Override
	protected void executeCommands(ITextTokenStream<BasicTextTokenType> stream) {
		TextToken<BasicTextTokenType> object = stream.getFirstObject();
		String token = object == null ? null : object.getStandardToken();
		if (token != null) {
			if (stream.getVerb() != null){
				switch (stream.getVerb().getType()) {
				case ATTACK:
					 attackMob(token, object);
					 break;
				case LOOK:
					 lookAt(token, object);
					 break;
				case MOVE:
					movePlayer(token, object);
					break;
				case GET:
					 getItemFromRoom(token, object);
					 break;
				case LOOT:
					lootAllMob(token, object);
					break;
				case DROP:
					dropItem(token, object);
					break;
				case EQUIP:
					equipItem(token, object);
					break;
				case UNEQUIP:
					 unequipItem(token, object);
					 break;
				case INFO:
					 info(token, object);
					 break;
				case QUIT:
					quitGame();
					break;
				default:
					return;
				}				
			}
			// For objects that can also be used to infer their verbs
			switch (object.getType()) {
			case DIRECTION:
				movePlayer(token, object);
				break;
			case INVENTORY:
				info(token, object);
				break;
			default:
				return;
			}

		} else {
			return;
		}
		
	}
	/**
	 * Sets gameOver to true
	 */
	protected void quitGame() {
		gameOver = true;
	}

	private void equipItem(String token, TextToken<BasicTextTokenType> object) {
		IItem toE = player.getItem(token);

		if (toE == null) {
			output.println(token + " not found");
			return;
		}

		if (toE.getUse().equals(ItemUse.Armour)) {
			worldUtil.equipArmour(token);

			output.println(token + " equipped as armour. ");
		}
		if (toE.getUse().equals(ItemUse.Weapon)) {
			worldUtil.equipWeapon(token);
			output.println(token + " equipped as weapon. ");
		}
		output.println(token + " not found");
		
	}

	protected void lookAt(String token, TextToken<BasicTextTokenType> object) {
		String info = worldUtil.lookAt(object);
		if (info == null){
			output.println("You don't see anything like that");
		} else {
			output.println(info);
		}
		
	}

	protected void getItemFromRoom(String token, TextToken<BasicTextTokenType> object) {
		ReturnStatus re = worldUtil.getItemFromRoom(token);
		
		if (ReturnStatus.SUCCESS.equals(re)){
			output.println(token + " was added to your inventory");
		} else if (ReturnStatus.FAILURE.equals(re)){
			output.println(token + " could not be moved");
		} else {
			output.println(token + " was not found");
		}
		
	}

	protected void unequipItem(String token, TextToken<BasicTextTokenType> object) {
		if (token.equalsIgnoreCase("armour") || token.equalsIgnoreCase("armor")
				|| player.isKeyforArmour(token)) {
			if (worldUtil.unequipArmour()){
				output.println(token + " has been unequipped from armour. ");
			} else {
				output.println("No armour equipped. ");
			}
		}
		else if (token.equalsIgnoreCase("weapon") || player.isKeyForWeapon(token)) {
			if(worldUtil.unequipWeapon()){
				output.println(token + " has been unequipped from weapon. ");
			} else {
				output.println("No weapon equipped. ");
			}
		}
		output.println("Nothing like that to unequip. ");
		
	}

	protected void info(String token, TextToken<BasicTextTokenType> object) {
		switch (object.getType()){
		case INVENTORY:
			if (player.getInventory().isEmpty()) {
				output.println("You have nothing in your inventory");
			} else {
				output.println(player.inventoryOverview());
			}
		case MONEY:
			output.println(player.getMoney());
		case HEALTH:
			output.println(player.getHealth());
		case MAX_HEALTH:
			output.println(player.getMaxHealth());
		default:
			output.println("Nothing by that name was found");
		}
		
	}

	protected void movePlayer(String token, TextToken<BasicTextTokenType> object) {
		if (worldUtil.goDirection(token)){
			output.println(worldUtil.getCurrent().showRoom());
		} else {
			output.println("You can't go that direction.");
		}
	}

	protected void dropItem(String token, TextToken<BasicTextTokenType> object) {
		if (worldUtil.dropItem(token)){
			output.println(token + " dropped from your inventory");
		} else {
			output.println("Item not found");
		}
		
	}

	protected void lootAllMob(String token, TextToken<BasicTextTokenType> object) {
		ReturnStatus re = worldUtil.lootAllMob(token);
		if (ReturnStatus.SUCCESS.equals(re)){
			output.println(token + "'s items added");
		} else if (ReturnStatus.FAILURE.equals(re)){
			output.println(token + " is still alive!");
		} else {
			output.println("Mob not found");
		}
	}

	protected void attackMob(String token, TextToken<BasicTextTokenType> object) {
		try {
			worldUtil.attackMob(token, battleSystem);
		} catch (NotFoundException e) {
			output.println("cannot find " + token);
		}
	}

	public boolean isGameOver(){
		return super.isGameOver() || player.isDead();
	}
	@Override
	protected void gameUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void endCleanUp() {
		output.print("Goodbye");
		
	}

}
