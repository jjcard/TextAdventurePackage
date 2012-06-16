package textGames;

public class Item {
	private String name;
	private int cost;
	private String info;
	private int level;
	private boolean hidden = false;
	private boolean movable = true;
	private ItemUse use = ItemUse.Item;
	private String roomDescrip;
 	public Item(){
		name = new String();
		info = new String();
		cost = 0;
		level = 0;
	}
	public Item(String nameNew){
		name = nameNew;
		info = new String();
		cost = 0;
		level = 0;
	}
	public Item(String nameNew, String infoNew){
		name = nameNew;
		info = infoNew;
		cost = 0;
		level = 0;
	}
	public Item(String nameNew, String infoNew, int levelNew){
		name = nameNew;
		info = infoNew;
		cost = 0;
		level = levelNew;
	}
	public Item(String nameNew, String infoNew, int levelNew, int costNew){
		name = nameNew;
		info = infoNew;
		level = levelNew;
		cost = costNew;
	}
	public String name() {
		return name;
	}
	public int cost() {
		return cost;
	}
	public String info() {
		return info;
	}
	public int level(){
		return level;
	}
	public boolean hidden(){
		return hidden;
	}
	public boolean movable(){
		return movable;
	}
	public ItemUse use() {
		return use;
	}
	public String roomDescrip(){
		return roomDescrip;
	}
 	public void setName(String change){
		name = change;
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
	public void setMovable(boolean change){
		movable = change;
	}
	public void setUse(ItemUse change){
		use = change;
	}
	public void setRoomDescrip(String descripN){
		roomDescrip = descripN;
	}
	public String toString() {
		return name;
	}
	public boolean equals(Object o){
		if (o instanceof Item){
			Item m = (Item) o;
			return this.name.equals(m.name()) && this.info.equalsIgnoreCase(m.info) && this.use == m.use;
		} else {
			return false;
		}
	}
}
