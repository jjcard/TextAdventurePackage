package jjcard.textGames.game.impl;

import java.util.List;

import jjcard.textGames.game.HasLeveling;
import jjcard.textGames.game.IGameElementMap;


public class Player extends Mob implements HasLeveling{
	private int level;
	private int xp;
	
	public static class PlayerBuilder extends MobBuilder{
		private int level;
		private int xp;
		
		public PlayerBuilder(){
			
		}
		public PlayerBuilder(Player p){
			super(p);
			this.xp = p.xp;
			this.level = p.level;
		}
		public PlayerBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public PlayerBuilder level(int level){
			this.level = level;
			return this;
		}
		public PlayerBuilder xp(int xp){
			this.xp = xp;
			return this;
		}
		public PlayerBuilder altNames(String[] altNames){
			super.altNames(altNames);
			return this;
		}
		public PlayerBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public PlayerBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public PlayerBuilder maxHealth(int maxHealth){
			super.maxHealth(maxHealth);
			return this;
		}
		public PlayerBuilder curHelath(int curHealth){
			super.curHelath(curHealth);
			return this;
		}
		public PlayerBuilder description(String description){
			super.description(description);
			return this;
		}
		public PlayerBuilder money(int money){
			super.money(money);
			return this;
		}
		public PlayerBuilder inventory(IGameElementMap<Item> inventory){
			super.inventory(inventory);
			return this;
		}
		public PlayerBuilder defense(int defense){
			super.defense(defense);
			return this;
		}
		public PlayerBuilder attack(int attack){
			super.attack(attack);
			return this;
		}
		public PlayerBuilder hostile(boolean hostile){
			super.hostile(hostile);
			return this;
		}
		public PlayerBuilder statusList(List<Status> statusList){
			super.statusList(statusList);
			
			return this;
		}
		public PlayerBuilder armor(Armour armour){
			super.armor(armour);
			return this;
		}
		public PlayerBuilder weapon(Weapon weapon){
			super.weapon(weapon);
			return this;
		}
		public PlayerBuilder addStatus(Status status){
			super.addStatus(status);
			return this;
		}
		public Player build(){
			return new Player(this);
		}
	}
	
	
	private Player(PlayerBuilder b){
		super(b);
		this.xp = b.xp;
		this.level = b.level;
	}

	public int getLevel(){
		return level;
	}
	public int getXp() {
		return xp;
	}

	public void changelevel(int change){
		level += change;
		if (level < 0){
			level = 0;
		}
	}
	public void setLevel(int levelN){
		level = levelN;
		if (level < 0){
			level = 0;
		}
	}
	public void changeXp(int change){
		xp += change;
		if (xp < 0){
			xp = 0;
		}
	}
	public void setXp(int xpN){
		xp = xpN;
		if (xp < 0){
			xp = 0;
		}
	}







	
	
}
