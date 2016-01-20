package jjcard.text.game.impl;

import jjcard.text.game.IMob;
import jjcard.text.game.parser.ITextParser;
import jjcard.text.game.parser.ITextTokenStream;
import jjcard.text.game.parser.ITextTokenType;
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
		endCleanUp();
	}
	/**
	 * Set up the game before the main game loop
	 */
	public abstract void setUp();
	/**
	 * clean up after game loop is exited
	 */
	protected abstract void endCleanUp();
	/**
	 * Receives the input from the player
	 */
	protected abstract String getInput();
	/**
	 * Parses the input to be sent to the {@link #executeCommands(ITextTokenStream)} method.
	 * By default simply calls the {@code parser} 
	 * @param input
	 * @return ITextTokenStream
	 */
	protected ITextTokenStream<T> parseInput(String input){
		return parser.parseText(input);
	}
	/**
	 * Execute the given command
	 * @param stream
	 */
	protected abstract void executeCommands(ITextTokenStream<T> stream);
	/**
	 * Returns true if there is a condition where the game loop should exit. 
	 * By default returns true if gameOver flag set.
	 * @return true if game over
	 */
	public boolean isGameOver(){
		return gameOver;
	}
	/**
	 * Called after {@link #executeCommands(ITextTokenStream)} in game loop.
	 */
	protected abstract void gameUpdate();
}
