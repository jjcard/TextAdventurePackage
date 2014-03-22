package jjcard.textGames.game.impl;

public class Item extends GameElement{
	private int cost;
	private String info;
	private int level;
	private boolean hidden = false;
	private boolean movable = true;
	private ItemUse use = ItemUse.Item;
 	public Item(){
		super();
		info = "";
		cost = 0;
		level = 0;
	}
	public Item(String name){
		super(name);
		info = "";
		cost = 0;
		level = 0;
	}
	public Item(String name, String infoNew){
		super(name);
		info = infoNew;
		cost = 0;
		level = 0;
	}
	public Item(String name, String infoNew, int levelNew){
		super(name);
		info = infoNew;
		cost = 0;
		level = levelNew;
	}
	public Item(String name, String infoNew, int levelNew, int costNew){
		super(name);
		info = infoNew;
		level = levelNew;
		cost = costNew;
	}
	public int getCost() {
		return cost;
	}
	public String getInfo() {
		return info;
	}
	public int getLevel(){
		return level;
	}
	public boolean isHidden(){
		return hidden;
	}
	public boolean isMovable(){
		return movable;
	}
	public ItemUse getUse() {
		return use;
	}
 	public void setName(String name){
		setStandardName(name);
	}
	public void setCost(int costN){
		cost = costN;
	}
	public void changeCost(int change){
		cost += change;
	}
	public void setInfo(String change) {
		info = change;
	}
	public void changeLevel(int change){
		level += change;
		if (level < 0){
			level = 0;
		}
	}
	public void setLevel(int levelNew){
		level = levelNew;
	}
	public void setHidden(boolean change){
		hidden = change;
	}
	/**
	 * returns true if item is movable and not hidden
	 * @return
	 */
	public boolean canGet(){
		return movable && !hidden;
	}
	public void setMovable(boolean change){
		movable = change;
	}
	public void setUse(ItemUse change){
		use = change;
	}
	public String toString() {
		return getStandardName();
	}
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o == null){
			return false;
		}
		if (o instanceof Item){
			Item m = (Item) o;
			if (this.standardName == null){
				if (m.standardName != null){
					return false;
				}
				
			} else {
				if (!this.standardName.equals(m.standardName)){
					return false;
				}
			}
			
			if (this.info == null){
				if (m.info != null){
					return false;
				}
			} else {
				if (!this.info.equals(m.info)){
					return false;
				}
			}
			return this.use.equals(m.getUse());
		} else {
			return false;
		}
	}
}
