package jjcard.text.game.impl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.text.game.IArmour;
import jjcard.text.game.IItem;
import jjcard.text.game.IStatus;
import jjcard.text.game.IWeapon;
import jjcard.text.game.leveling.HasLeveling;
import jjcard.text.game.util.ObjectsUtil;

@JsonDeserialize(builder = Player.Builder.class)
public class Player extends Mob implements HasLeveling{
	@JsonProperty("lvl")
	private int level;
	@JsonProperty("xp")
	private int xp;
	
	public static class Builder extends Mob.Builder{
		private int level;
		private int xp;
		
		public Builder(){
			super();
		}
		public Builder(Player p){
			super(p);
			this.xp = p.xp;
			this.level = p.level;
		}
		public Builder(AbstractGameElement g){
			super(g);
		}
		@Override
        public Builder name(String name){
			super.name(name);
			return this;
		}
		@JsonProperty("lvl")
		public Builder level(int level){
			this.level = level;
			return this;
		}
		@JsonProperty("xp")
		public Builder xp(int xp){
			this.xp = xp;
			return this;
		}
		@Override
        public Builder checkHealth(boolean checkHealth){
			super.checkHealth(checkHealth);
			return this;
		}
		@Override
        public Builder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		@Override
        public Builder maxHealth(int maxHealth){
			super.maxHealth(maxHealth);
			return this;
		}
		@Override
        public Builder health(int curHealth){
			super.health(curHealth);
			return this;
		}
		@Override
        public Builder viewDescription(String description){
			super.viewDescription(description);
			return this;
		}
		@Override
        public Builder money(int money){
			super.money(money);
			return this;
		}
		@Override
        public Builder inventory(Map<String, IItem> inventory){
			super.inventory(inventory);
			return this;
		}
		@Override
        public Builder addItem(IItem item){
			super.addItem(item);
			return this;
		}
		@Override
        public Builder defense(int defense){
			super.defense(defense);
			return this;
		}
		@Override
        public Builder attack(int attack){
			super.attack(attack);
			return this;
		}
		@Override
        public Builder hostile(boolean hostile){
			super.hostile(hostile);
			return this;
		}
		@Override
        public Builder statusList(List<IStatus> statusList){
			super.statusList(statusList);
			
			return this;
		}
		@Override
        public Builder armour(IArmour armour){
			super.armour(armour);
			return this;
		}
		@Override
        public Builder weapon(IWeapon weapon){
			super.weapon(weapon);
			return this;
		}
		@Override
        public Builder addStatus(IStatus status){
			super.addStatus(status);
			return this;
		}
		@Override
        public Builder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		@Override
        public Builder hidden(boolean hidden){
			super.hidden(hidden);
			return this;
		}
		@Override
        public Player build(){
			return new Player(this);
		}
	}
	
	protected Player(Builder b){
		super(b);
		setXp(b.xp);
		setLevel(b.level);
	}
	public Player(String name){
		super(name);
	}

	/**
	 * Returns level
	 */
	@Override
    public int getLevel(){
		return level;
	}
	/**
	 * Returns xp
	 */
	@Override
    public int getXp() {
		return xp;
	}

	public void changelevel(int change){
		setLevel(level + change);
	}
	@Override
    public void setLevel(int levelN){
		level = levelN;
		if (doValidateFields() && level < 0){
			level = 0;
		}
	}
	public void changeXp(int change){
		setXp(this.xp + change);
	}
	public void setXp(int xpN){
		xp = xpN;
		if (doValidateFields() && xp < 0){
			xp = 0;
		}
	}
	@Override
    public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o instanceof Player){
			if (!super.equals(o)){
				return false;
			}
			Player p = (Player) o;
			if (p.xp != xp){
				return false;
			}
			if (level != p.level){
				return false;
			}
			return true;
		} else {
			return false;
		}

	}
	@Override
    public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(),
				ObjectsUtil.DEFAULT_PRIME, xp, level);
	}
}
