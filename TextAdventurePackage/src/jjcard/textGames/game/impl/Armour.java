package jjcard.textGames.game.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jjcard.textGames.game.IArmour;


@JsonDeserialize(builder = Armour.ArmourBuilder.class)
public class Armour extends Item implements IArmour {
	@JsonProperty("def")
	private int defense;
	
	public static class ArmourBuilder extends ItemBuilder{
		private int defense;
		
		public ArmourBuilder(){
			super();
			use(ItemUse.Armour);
		}
		public ArmourBuilder(Armour armour){
			super(armour);
			this.defense = armour.defense;
		}
		public ArmourBuilder(AbstractGameElement element){
			super(element);
			use(ItemUse.Armour);
		}
		public ArmourBuilder cost(int cost){
			super.cost(cost);
			return this;
		}
		@JsonProperty("def")
		public ArmourBuilder defense(int defense){
			this.defense = defense;
			return this;
		}
		public ArmourBuilder info(String info){
			super.info(info);
			return this;
		}
		public ArmourBuilder level(int level){
			super.level(level);
			return this;
		}
		public ArmourBuilder hidden(boolean hidden){
			super.hidden(hidden);
			return this;
		}
		public ArmourBuilder movable(boolean movable){
			super.movable(movable);
			return this;
		}
		public ArmourBuilder use(ItemUse use){
			super.use(use);
			return this;
		}
		public ArmourBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public ArmourBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public ArmourBuilder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Armour build(){
			return new Armour(this);
		}
	}
	protected Armour(ArmourBuilder builder){
		super(builder);
		setDefense(builder.defense);
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
}
