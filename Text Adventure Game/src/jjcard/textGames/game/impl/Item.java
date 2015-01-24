package jjcard.textGames.game.impl;

import jjcard.textGames.game.IItem;
import jjcard.textGames.game.util.EqualsUtil;

public class Item extends AbstractGameElement implements IItem{
	private int cost;
	private String info;
	private int level;
	private boolean hidden = false;
	private boolean movable = true;
	private ItemUse use = ItemUse.Item;
	
	
	
	public static class ItemBuilder extends GameElementBuilder{
		private int cost;
		private String info;
		private int level;
		private boolean hidden = false;
		private boolean movable = true;
		private ItemUse use = ItemUse.Item;
		
		public ItemBuilder(){
			super();
		}
		public ItemBuilder(Item i){
			  super(i);
			  cost = i.cost;
			  info = i.info;
			  level = i.level;
			  hidden = i.hidden;
			  movable = i.movable;
			  use = i.use;
		}
		public ItemBuilder(AbstractGameElement e){
			super(e);
		}
		public ItemBuilder cost(int cost){
			this.cost = cost;
			return this;
		}
		public ItemBuilder info(String info){
			this.info = info;
			return this;
		}
		public ItemBuilder level(int level){
			this.level = level;
			return this;
		}
		public ItemBuilder hidden(boolean hidden){
			this.hidden = hidden;
			return this;
		}
		public ItemBuilder movable(boolean movable){
			this.movable = movable;
			return this;
		}
		public ItemBuilder use(ItemUse use){
			this.use = use;
			return this;
		}
		public ItemBuilder standardName(String name){
			super.standardName(name);
			return this;
		}
		public ItemBuilder roomDescription(String roomDescrip){
			super.roomDescription(roomDescrip);
			return this;
		}
		public ItemBuilder validateFields(boolean validateFields){
			super.validateFields(validateFields);
			return this;
		}
		public Item build(){
			return new Item(this);
		}
	}
	
	protected Item(ItemBuilder b){
		  super(b);
		  setCost(b.cost);
		  setInfo(b.info);
		  setLevel(b.level);
		  setHidden(b.hidden);
		  setMovable(b.movable);
		  setUse(b.use);
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

	public void setCost(int costN){
		cost = costN;
	}
	public void changeCost(int change){
		setCost(cost + change);
	}
	public void setInfo(String change) {
		info = change;
	}
	public void changeLevel(int change){
		setLevel(level + change);
	}
	public void setLevel(int levelNew){
		level = levelNew;
		if (doValidateFields() && level < 0){
			level = 0;
		}
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
	public boolean equals(Object o){
		if (o == this){
			return true;
		}
		if (o == null){
			return false;
		}
		if (o instanceof Item){
			Item m = (Item) o;
			if (EqualsUtil.notEqual(getStandardName(), m.getStandardName())){
				return false;
			}
			if (EqualsUtil.notEqual(info, m.info)){
				return false;
			}
			return this.use.equals(m.getUse());
		} else {
			return false;
		}
	}
}
