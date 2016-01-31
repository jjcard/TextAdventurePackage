package jjcard.text.game.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.text.game.IWeapon;
import jjcard.text.game.util.ObjectsUtil;

@JsonDeserialize(builder = Weapon.Builder.class)
public class Weapon extends Item implements IWeapon{
	@JsonProperty("att")
	private int attack;
	@JsonProperty("crit")
	private int critChance; //out of 100
	@JsonProperty("dur")
	private int durability;
	
	public static class Builder extends Item.Builder{
		private int attack;
		private int critChance; //out of 100
		private int durability;
		
		public Builder(){
			super();
			use(ItemUse.Weapon);
			
		}
		public Builder(Weapon w){
			super(w);
			this.attack = w.attack;
			this.critChance = w.critChance;
			this.durability = w.durability;	
		}
		public Builder(AbstractGameElement g){
			super(g);
			use(ItemUse.Weapon);
		}
		public Builder cost(int cost){
			super.cost(cost);
			return this;
		}
		@JsonProperty("att")
		public Builder attack(int attack){
			this.attack = attack;
			return this;
		}
		@JsonProperty("crit")
		public Builder critChance (int critChance){
			this.critChance = critChance;
			return this;
		}
		@JsonProperty("dur")
		public Builder durability(int durability){
			this.durability = durability;
			return this;
		}
		public Builder viewDescription(String viewDescription){
			super.viewDescription(viewDescription);
			return this;
		}
		public Builder level(int level){
			super.level(level);
			return this;
		}
		public Builder hidden(boolean hidden){
			super.hidden(hidden);
			return this;
		}
		public Builder movable(boolean movable){
			super.movable(movable);
			return this;
		}
		public Builder use(ItemUse use){
			super.use(use);
			return this;
		}
		public Builder name(String name){
			super.name(name);
			return this;
		}
		public Builder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public Builder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Weapon build(){
			return new Weapon(this);
		}
	}
	protected Weapon(Builder b){
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
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (!super.equals(o)){
			return false;
		}
		if (o instanceof Weapon){
			Weapon other = (Weapon) o;
			if (attack != other.attack){
				return false;
			}
			if (critChance != other.critChance){
				return false;
			}
			if (durability != other.durability){
				return false;
			}
			
			return true;
		}
		return false;
	}
	public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(),
				ObjectsUtil.DEFAULT_PRIME, attack, critChance, durability);
	}
}
