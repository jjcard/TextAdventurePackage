package jjcard.text.game.leveling;

/**
 * Interface classes need to implement in order to use a LevelingStrategy
 *
 */
public interface HasLeveling {

	int getLevel();
	
	void setLevel(int level);
	
	int getXp();

}
