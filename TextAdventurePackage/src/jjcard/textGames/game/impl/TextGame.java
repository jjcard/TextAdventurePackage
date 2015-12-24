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
	/**
	 * Game over flag
	 */
	protected boolean gameOver = false;
	protected ITextParser<T> parser;
	
	public void play(){
		setUp();
		while (!isGameOver()){
			String input = getInput();
			ITextTokenStream<T> stream = parseInput(input);
			executeCommands(stream);
			gameUpdate();
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
	/**
	 * Execute the given command
	 * @param stream
	 */
	protected abstract void executeCommands(ITextTokenStream<T> stream);
	/**
	 * Returns true if there is a condition where the gameloop should exit. Returns if player.isDead() or gameOver flag set.
	 * @return true if game over
	 */
	public boolean isGameOver(){
		return player.isDead() || gameOver;
	}
	/**
	 * Called after {@link #executeCommands(ITextTokenStream)} in game loop.
	 */
	protected abstract void gameUpdate();
}
