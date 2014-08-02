package jjcard.textGames.game.shop;

import jjcard.textGames.game.parser.ITextTokenType;
/**
 * Experimental: Subject to Change
 *
 * @param <T>
 */
public interface Shopper<T extends ITextTokenType> extends Buyer<T>, Seller<T>{

}
