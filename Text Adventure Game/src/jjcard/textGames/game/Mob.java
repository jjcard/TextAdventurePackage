package jjcard.textGames.game;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Mob {
	public static int DEFAULT_HEALTH = 10;
	private String name;
	private String description;
	private String roomDescrip;
	private int maxHealth;
	private int curHealth;
	private int money = 0;
	private Map<String, Item> inventory = new HashMap<String, Item>();
	private int defense = 0;
	private int attack = 0;
	private boolean Hostile = true;
	private LinkedList<Status> statusList = new LinkedList<Status>();
	
	public Mob() {
		name = new String();
		description = new String();
		maxHealth = DEFAULT_HEALTH;
		curHealth = maxHealth;

	}
	public Mob(String nameNew) {
		name = nameNew;
		description = "";
		maxHealth = DEFAULT_HEALTH;
		curHealth = maxHealth;

	}
	public Mob(String nameNew, int healthNew){
		name = nameNew;
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		
	}
	public Mob(String nameNew, int healthNew, int defenseNew) {
		name = nameNew;
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		defense = defenseNew;

	}
	public Mob(String nameNew, int healthNew, int defenseNew, int attackNew){
		name = nameNew;
		description = "";
		maxHealth = healthNew;
		curHealth = maxHealth;
		defense = defenseNew;
		attack = attackNew;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public String getRoomDescrip(){
		return roomDescrip;
	}
	public int getHealth(){
		return curHealth;
	}
	public int getMoney(){
		return money;
	}
	public Map<String, Item> getInventory() {
		return inventory;
	}
	public Item getItem(String key){
		return inventory.get(key);
	}


	public int getDefense() {
		return defense;
	}
	public int getAttack() {
		return attack;
	}
	public boolean isHostile() {
		return Hostile;
	}
	public LinkedList<Status> getstatusList() {
		return statusList;
	}
	public boolean containsStatus(Status s){
		return statusList.contains(s);
	}
	public boolean removeStatus(Status s){
		return statusList.remove(s);
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
			addStatus(Status.DEAD);
		} 
	}
	public void addStatus(Status s){
		if (Status.DEAD.equals(s)){
			statusList.addFirst(s);
		} else {
			statusList.add(s);
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
	public void setstatusList(LinkedList<Status> s){
		statusList = s;
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
	/**
	 * attack this mob for this amount of damage minus the mobs defense. 
	 * Returns new health
	 * @param attack
	 * @return
	 */
	public int attackMob(int damage){
		changeHealth(-(damage - defense) );
		return curHealth;
	}
	public boolean containsItem(String key){
		return inventory.containsKey(key);
	}
	public int inventorySize() {
		return inventory.size();
	}
	public boolean isDead(){
		return  Status.DEAD.equals(getFirstStatus());
	}
	public String toString() {
		return name;
	}
	private Status getFirstStatus(){
		return statusList.isEmpty() ? null: statusList.getFirst();
	}
	public boolean equals(Object o){
		if (o instanceof Mob){
			Mob m = (Mob) o;
			return this.name.equals(m.getName()) && this.description.equalsIgnoreCase(m.description);
		} else {
			return false;
		}
	}
	public String inventoryToString(){
		String re = inventory.keySet().toString();
		return re.substring(1, re.length()-1);
		
	}
}
