package jjcard.textGames.game.impl;


public class Armour extends Item {
	private int defense;
	
	public static class ArmourBuilder extends ItemBuilder{
		private int defense;
		
		public ArmourBuilder(){
			use(ItemUse.Armour);
		}
		public ArmourBuilder cost(int cost){
			super.cost(cost);
			return this;
		}
		public ArmourBuilder defense(int defense){
			this.defense = defense;
			return this;
		}
		public ArmourBuilder info(String info){
			super.info(info);
			return this;
		}
		public ArmourBuilder level(int level){
			super.level(level);
			return this;
		}
		public ArmourBuilder hidden(boolean hidden){
			super.hidden(hidden);
			return this;
		}
		public ArmourBuilder movable(boolean movable){
			super.movable(movable);
			return this;
		}
		public ArmourBuilder use(ItemUse use){
			super.use(use);
			return this;
		}
		public ArmourBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public ArmourBuilder altNames(String[] altNamesArray){
			super.altNames(altNamesArray);
			return this;
		}
		public ArmourBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public ArmourBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public Armour build(){
			return new Armour(this);
		}
	}
	protected Armour(ArmourBuilder b){
		super(b);
		this.defense = b.defense;
	}
//	public Armour(){
//		super();
//		defense = 0;
//		super.setUse(ItemUse.Armour);
//	}
//	public Armour(String name){
//		super(name);
//		defense = 0;
//		super.setUse(ItemUse.Armour);
//	}
//	public Armour(String name, String info){
//		super(name, info);
//		defense = 0;
//	}
//	public Armour(String name, String info, int level){
//		super(name, info, level);
//		defense = 0;
//		super.setUse(ItemUse.Armour);
//	}
//	public Armour(String name, String info, int level, int defenseNew){
//		super(name, info, level);
//		defense = defenseNew;
//		super.setUse(ItemUse.Armour);
//	}
	public int getDefense(){
		return defense;
	}
	public void setDefense(int change){
		defense += change;
		if (defense < 0){
			defense = 0;
		}
	}
}
