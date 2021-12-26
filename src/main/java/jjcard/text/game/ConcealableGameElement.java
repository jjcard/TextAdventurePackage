package jjcard.text.game;

public interface ConcealableGameElement extends IGameElement {

	boolean isHidden();
	void setHidden(final boolean hidden);
}
