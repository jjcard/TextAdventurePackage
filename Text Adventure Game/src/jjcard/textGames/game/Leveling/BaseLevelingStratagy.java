package jjcard.textGames.game.Leveling;

import jjcard.textGames.game.HasLeveling;

public class BaseLevelingStratagy implements LevelingStratagy {
	private static final int[] levelingChart = new int[]{1000, 3000, 6000, 
		10000, 15000, 21000, 28000, 36000, 45000, 55000, 66000, 78000, 
		91000, 105000, 120000, 136000, 153000, 171000, 190000};
	private HasLeveling client;
	
	public BaseLevelingStratagy(HasLeveling player) {
	}
	@Override
	public int updateLevel() {
		
		int xp = client.getXp();
		
		int generatedLevel = getLevelFromChart(xp);
		
		return generatedLevel;
		
		
	}
	public int getLevelFromChart(int xp){
		for (int i = 0; i < levelingChart.length; i++){
			if (levelingChart[i] < xp){
				return i;
			}
		}
		return 0;
	}
	


}
