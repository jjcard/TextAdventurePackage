package jjcard.text.game.leveling.impl;

import jjcard.text.game.leveling.HasLeveling;
import jjcard.text.game.leveling.LevelingStrategy;
import jjcard.text.game.util.Experimental;


@Experimental
public class BaseLevelingStrategy implements LevelingStrategy<HasLeveling> {
	private static final int[] DEFAULT_LEVELING_CHART = new int[]{1000, 3000, 6000, 
		10000, 15000, 21000, 28000, 36000, 45000, 55000, 66000, 78000, 
		91000, 105000, 120000, 136000, 153000, 171000, 190000};
	private final HasLeveling client;
	private final int[] levelingChart;
	/**
	 * Uses the default levelingChart
	 * @param player
	 */
	public BaseLevelingStrategy(HasLeveling player) {
		client = player;
		this.levelingChart = DEFAULT_LEVELING_CHART;
	}
	public BaseLevelingStrategy(HasLeveling player, int[] levelingChart) {
		client = player;
		this.levelingChart = levelingChart;
	}
	protected int updateLevel() {
		
		int xp = client.getXp();
		
		int generatedLevel = getLevelFromChart(xp);
		
		return generatedLevel;
		
		
	}
	protected int getLevelFromChart(int xp){
		for (int i = 0; i < levelingChart.length; i++){
			if (levelingChart[i] < xp){
				return i;
			}
		}
		return 0;
	}
	@Override
	public HasLeveling getUser() {
		return client;
	}
	@Override
	public HasLeveling update() {
		
		int levelNew = updateLevel();
		if (levelNew > client.getLevel()){
			client.setLevel(levelNew);
		}
		return client;
	}
	


}
