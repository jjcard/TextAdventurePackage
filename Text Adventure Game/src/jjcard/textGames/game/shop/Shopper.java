package jjcard.textGames.game.shop;

import jjcard.textGames.game.parser.ITextTokenType;

public interface Shopper<T extends ITextTokenType> extends Buyer<T>, Seller<T>{

}
