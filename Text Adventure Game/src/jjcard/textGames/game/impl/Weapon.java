package jjcard.textGames.game.impl;


public class Weapon extends Item {
	private int attack;
	private int critChance; //out of 100
	private int durability;
	
	public static class WeaponBuilder extends ItemBuilder{
		private int attack;
		private int critChance; //out of 100
		private int durability;
		
		public WeaponBuilder(){
			super();
			
		}
		public WeaponBuilder(Weapon w){
			super(w);
			this.attack = w.attack;
			this.critChance = w.critChance;
			this.durability = w.durability;	
		}
		public WeaponBuilder(GameElement g){
			super(g);
			use(ItemUse.Weapon);
		}
		public WeaponBuilder cost(int cost){
			super.cost(cost);
			return this;
		}
		public WeaponBuilder attack(int attack){
			this.attack = attack;
			return this;
		}
		public WeaponBuilder critChance (int critChance){
			this.critChance = critChance;
			return this;
		}
		public WeaponBuilder durability(int durability){
			this.durability = durability;
			return this;
		}
		public WeaponBuilder info(String info){
			super.info(info);
			return this;
		}
		public WeaponBuilder level(int level){
			super.level(level);
			return this;
		}
		public WeaponBuilder hidden(boolean hidden){
			super.hidden(hidden);
			return this;
		}
		public WeaponBuilder movable(boolean movable){
			super.movable(movable);
			return this;
		}
		public WeaponBuilder use(ItemUse use){
			super.use(use);
			return this;
		}
		public WeaponBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public WeaponBuilder altNames(String[] altNamesArray){
			super.altNames(altNamesArray);
			return this;
		}
		public WeaponBuilder addAltName(String altName){
			super.addAltName(altName);
			return this;
		}
		public WeaponBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public WeaponBuilder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Weapon build(){
			return new Weapon(this);
		}
	}
	protected Weapon(WeaponBuilder b){
		super(b);
		setAttack(b.attack);
		setCritChance(b.critChance);
		setDurability(b.durability);
	}
	public int getAttack() {
		return attack;
	}
	public int getCritChance() {
		return critChance;
	}
	public int getDurability(){
		return durability;
	}
	public void setDurability(int durN){
		durability = durN;
	}
	public void changeDurability(int change){
		setDurability(durability + change);
	}
	public void setAttack(int attack){
		this.attack = attack;
		if (doValidateFields() && this.attack < 0){
			this.attack = 0;
		}
	}
	public void changeAttack(int change){
		setAttack(attack + change);
	}
	public void changeCritChance(int change) {
		setCritChance(change + critChance);
	}
	public void setCritChance(int critN){
		critChance = critN;
		if (doValidateFields()){
			if (critChance < 0){
				critChance = 0;
			} else if (critChance > 100){
				critChance = 100;
			}			
		}

	}
}
