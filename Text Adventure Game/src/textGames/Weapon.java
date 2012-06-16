package textGames;

public class Weapon extends Item {
	private int attack;
	private int crit; //out of 100
	private int durability;
	
	public Weapon() {
		super();
		attack = 0;
		crit = 1;
		durability = 100;
		super.setUse(ItemUse.Weapon);
	}
	public Weapon(String nameNew){
		super(nameNew);
		attack = 0;
		crit = 1;
		durability = 100;
		super.setUse(ItemUse.Weapon);
	}
	public Weapon(String nameNew, String info){
		super(nameNew, info);
		attack = 0;
		crit = 1;
		durability = 100;
		super.setUse(ItemUse.Weapon);
	}
	public Weapon(String nameNew, String info, int attackNew){
		super(nameNew, info);
		attack = attackNew;
		crit = 1;
		durability = 100;
		super.setUse(ItemUse.Weapon);
	}
	public Weapon(String name, String info, int attackNew, int level){
		super(name, info, level);
		attack = attackNew;
		crit = 1;
		durability = 100;
		super.setUse(ItemUse.Weapon);
	}
	public int getAttack() {
		return attack;
	}
	public int getCrit() {
			return crit;
		}
	public int getDurability(){
		return durability;
	}
	public void setDurability(int durN){
		durability = durN;
	}
	public void changeDurability(int change){
		durability += change;
	}
	public void setAttack(int change){
		attack += change;
		if (attack < 0){
			attack = 0;
		}
	}
	public void setCrit(int change) {
		crit += change;
		if (crit < 0){
			crit = 0;
		} else if(crit > 100){
			crit = 100;
		}
	}
}
