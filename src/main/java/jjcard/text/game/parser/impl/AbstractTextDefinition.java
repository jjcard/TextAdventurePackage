package jjcard.text.game.parser.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.text.game.parser.ITextDefinition;
import jjcard.text.game.parser.ITextTokenType;
import jjcard.text.game.util.ObjectsUtil;

import java.util.Objects;

public abstract class AbstractTextDefinition<T extends ITextTokenType> implements ITextDefinition<T> {
    @JsonProperty("type")
    private final T type;

    public AbstractTextDefinition(@JsonProperty("type") T type) {
        ObjectsUtil.checkArg(type, "type");
        this.type = type;
    }

    @Override
    public T getType() {
        return type;
    }

    /**
     * Returns the standardToken for the given token
     * 
     * @param token
     * @return the standardToken
     */
    @Override
    public abstract String getStandardToken(String token);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "=" + type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof AbstractTextDefinition<?> other) {
            return type.equals(other.type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

}
