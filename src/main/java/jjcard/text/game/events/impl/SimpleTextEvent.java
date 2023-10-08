package jjcard.text.game.events.impl;

import jjcard.text.game.events.ITextEvent;

public record SimpleTextEvent(String key, String commandKey) implements ITextEvent {


}
