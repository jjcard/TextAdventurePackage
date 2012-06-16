package textGames;

public class Armour extends Item {
	private int defense;
	
	public Armour(){
		super();
		defense = 0;
		super.setUse(ItemUse.Armour);
	}
	public Armour(String name){
		super(name);
		defense = 0;
		super.setUse(ItemUse.Armour);
	}
	public Armour(String name, String info){
		super(name, info);
		defense = 0;
	}
	public Armour(String name, String info, int level){
		super(name, info, level);
		defense = 0;
		super.setUse(ItemUse.Armour);
	}
	public Armour(String name, String info, int level, int defenseNew){
		super(name, info, level);
		defense = defenseNew;
		super.setUse(ItemUse.Armour);
	}
	public int defense(){
		return defense;
	}
	public void setDefense(int change){
		defense += change;
		if (defense < 0){
			defense = 0;
		}
	}
}
