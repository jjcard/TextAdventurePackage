package jjcard.text.game;

public interface ConcealableGameElement extends IGameElement {

	public boolean isHidden();
	public void setHidden(final boolean hidden);
}
