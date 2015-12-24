package jjcard.textGames.game.impl;

import jjcard.textGames.game.IMob;
import jjcard.textGames.game.parser.ITextParser;
import jjcard.textGames.game.parser.ITextTokenStream;
import jjcard.textGames.game.parser.ITextTokenType;
/**
 * Abstract class that includes basic game loop
 *
 * @param <T> extends ITextTokenType.
 * @param <P> extends IMob. The Player class.
 */
public abstract class TextGame<T extends ITextTokenType, P extends IMob> {

	protected P player;
	protected boolean gameOver;
	protected ITextParser<T> parser;
	
	public void play(){
		setUp();
		while (!isGameOver()){
			String input = getInput();
			ITextTokenStream<T> stream = parseInput(input);
			execumeCommands(stream);
		}
	}
	/**
	 * Set up the game before the main game loop
	 */
	public abstract void setUp();
	/**
	 * Receives the input from the player
	 */
	protected abstract String getInput();
	protected ITextTokenStream<T> parseInput(String input){
		return parser.parseText(input);
	}
	protected abstract void execumeCommands(ITextTokenStream<T> stream);
	/**
	 * Returns true if there is a condition where the gameloop should exit. Returns if player.isDead() or gameOver flag set.
	 * @return 
	 */
	public boolean isGameOver(){
		return player.isDead() || gameOver;
	}
}
