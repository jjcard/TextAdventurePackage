package jjcard.text.game.parser.impl;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jjcard.text.game.parser.ITextTokenType;
import jjcard.text.game.util.ObjectsUtil;

/**
 * Text Definition with map key-value pair for standardization. When
 * standardizing, if map does not contain given value as key, will simply return
 * value.
 *
 * @param <T>
 */
public class MappedTextDefinition<T extends ITextTokenType> extends AbstractTextDefinition<T> {
    @JsonProperty("map")
    private final Map<String, String> standardizedMap;

    public MappedTextDefinition(T type) {
        this(type, null);
    }

    @JsonCreator
    public MappedTextDefinition(@JsonProperty("type") T type, @JsonProperty("map") Map<String, String> standarizedMap) {
        super(type);
        if (standarizedMap == null) {
            this.standardizedMap = new TreeMap<>();
        } else {
            this.standardizedMap = standarizedMap;
        }

    }

    /**
     * Adds standardizing entry into internal map. Returns previous value
     * associated with that key
     * 
     * @param key
     * @param value
     * @return previous value
     */
    public String putEntry(String key, String value) {
        return standardizedMap.put(key, value);
    }

    @Override
    public String getStandardToken(String token) {
        return standardizedMap.getOrDefault(token, token);
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (o instanceof MappedTextDefinition<?>) {
                return this.standardizedMap.equals(((MappedTextDefinition<?>) o).standardizedMap);
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        return ObjectsUtil.getHashWithStart(super.hashCode(), ObjectsUtil.DEFAULT_PRIME, standardizedMap);
    }

    public static <T extends ITextTokenType> MappedTextDefinition<T> getInstance(T type) {
        return new MappedTextDefinition<>(type);
    }

    public static <T extends ITextTokenType> MappedTextDefinition<T> getInstance(T type, Map<String, String> map) {
        return new MappedTextDefinition<>(type, map);
    }

}
