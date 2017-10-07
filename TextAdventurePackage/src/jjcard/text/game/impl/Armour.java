package jjcard.text.game.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.text.game.IArmour;
import jjcard.text.game.util.ObjectsUtil;


@JsonDeserialize(builder = Armour.Builder.class)
public class Armour extends Item implements IArmour {
	@JsonProperty("def")
	private int defense;
	
	public static class Builder extends Item.Builder{
		private int defense;
		
		public Builder(){
			super();
			use(ItemUse.Armour);
		}
		public Builder(Armour armour){
			super(armour);
			this.defense = armour.defense;
		}
		public Builder(AbstractGameElement element){
			super(element);
			use(ItemUse.Armour);
		}
		public Builder cost(int cost){
			super.cost(cost);
			return this;
		}
		@JsonProperty("def")
		public Builder defense(int defense){
			this.defense = defense;
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
		public Armour build(){
			return new Armour(this);
		}
	}
	protected Armour(Builder builder){
		super(builder);
		setDefense(builder.defense);
	}
	public Armour(String name) {
		super(name);
	}
	public int getDefense(){
		return defense;
	}
	public void setDefense(int defense){
		this.defense = defense;
		if (doValidateFields() && this.defense < 0){
			this.defense = 0;
		}
	}
	public void changeDefense(int change){
		setDefense(defense + change);
	}
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (!super.equals(o)){
			return false;
		}
		if (o instanceof Armour){
			Armour other = (Armour) o;
			if (defense != other.defense){
				return false;
			}
			
			return true;
		}
		return false;
	}
	public int hashCode(){
		return ObjectsUtil.getHashWithStart(super.hashCode(),
				ObjectsUtil.DEFAULT_PRIME, defense);
	}
}
