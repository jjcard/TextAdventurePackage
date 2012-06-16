package textGames;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Mob {
	private String name;
	private String description;
	private String roomDescrip;
	private int maxHealth;
	private int curHealth;
	private int money;
	private Map<String, Item> inventory;
	private int defense;
	private int attack;
	private boolean Hostile = true;
	private Status status = Status.ALIVE;
	
	public Mob() {
		name = new String();
		description = new String();
		maxHealth = 1;
		curHealth = maxHealth;
		inventory = new HashMap<String, Item>();
		defense = 0;
		attack = 0;
		money = 0;
	}
	public Mob(String nameNew) {
		name = nameNew;
		description = new String();
		maxHealth = 1;
		curHealth = maxHealth;
		inventory = new HashMap<String, Item>();
		defense = 0;
		attack = 0;
		money = 0;
	}
	public Mob(String nameNew, int healthNew){
		name = nameNew;
		description = new String();
		maxHealth = healthNew;
		curHealth = maxHealth;
		inventory = new HashMap<String, Item>();
		defense = 0;
		attack = 0;
		money = 0;
		
	}
	public Mob(String nameNew, int healthNew, int defenseNew) {
		name = nameNew;
		description = new String();
		maxHealth = healthNew;
		curHealth = maxHealth;
		inventory = new HashMap<String, Item>();
		defense = defenseNew;
		attack = 0;
		money = 0;
	}
	public Mob(String nameNew, int healthNew, int defenseNew, int attackNew){
		name = nameNew;
		description = new String();
		maxHealth = healthNew;
		curHealth = maxHealth;
		inventory = new HashMap<String, Item>();
		defense = defenseNew;
		attack = attackNew;
		money = 0;
	}
	public String name() {
		return name;
	}
	public String description() {
		return description;
	}
	public int maxHealth() {
		return maxHealth;
	}
	public String roomDescrip(){
		return roomDescrip;
	}
	public int health(){
		return curHealth;
	}
	public int money(){
		return money;
	}
	public Map<String, Item> inventory() {
		return inventory;
	}
	public int defense() {
		return defense;
	}
	public int attack() {
		return attack;
	}
	public boolean Hostile() {
		return Hostile;
	}
	public Status status() {
		return status;
	}
	public void setName(String nameNew){
		name = nameNew;
	}
	public void setRoomDescrip(String roomDescripN){
		roomDescrip = roomDescripN;
	}
	public void setDescription(String newDes){
		description = newDes;
	}
	public void changeMaxHealth(int change){
		maxHealth += change;
		if(maxHealth <= 0){
			maxHealth = 0;
		}
		if (maxHealth < curHealth){
			changeHealth(maxHealth);
		}
	}
	public void changeHealth(int change){
		curHealth += change;
		if (curHealth > maxHealth){
			curHealth = maxHealth;
		}
		if (curHealth <= 0){
			curHealth = 0;
			status = Status.DEAD;
		} 
	}
	public void changeMoney(int change){
		money += change;
	}
	public void setMoney(int moneyN){
		money = moneyN;
	}
	public void changeDefense(int change){
		defense += change;
		if (defense < 0){
			defense = 0;
		}
	}
	public void setDefense(int defenseN){
		defense = defenseN;
	}
	public void changeAttack(int change){
		attack += change;
		if (attack < 0){
			attack = 0;
		}
	}
	public void setAttack(int attackN){
		attack = attackN;
	}
	public void setHostile(boolean change){
		Hostile = change;
	}
	public void setStatus(Status change){
		status = change;
	}
	public Item addItem(String key, Item add){
		
		return inventory.put(key, add);
		
	}
	public void addAllItems(Map<String, Item> addMap){
		inventory.putAll(addMap);
		
		}
	public Item removeItem(String key){
		return inventory.remove(key);
	}
	public void removeInventory(){
		inventory = null;
	}
	
	public boolean containsItem(String key){
		return inventory.containsKey(key);
	}
	public int inventorySize() {
		return inventory.size();
	}
	public boolean isDead(){
		return status == Status.DEAD;
	}
	public String toString() {
		return name;
	}
	public boolean equals(Object o){
		if (o instanceof Mob){
			Mob m = (Mob) o;
			return this.name.equals(m.name()) && this.description.equalsIgnoreCase(m.description);
		} else {
			return false;
		}
	}
	public String inventoryToString(){
		String re = inventory.keySet().toString();
		return re.substring(1, re.length()-1);
		
	}
}
