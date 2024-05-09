package com.lyx.mappings.mapbox4j;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapper extends Mapper<Object, Object> {

    ObjectMapper(Map<Object, MapElement<Object, Object>> handle) {
        super(handle);
    }

    public ObjectMapper() {
        this(new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    public <I, V> Mapper<I, V> castTo(Class<I> indexType, Class<V> valueType) {
        return (Mapper<I, V>) this;
    }
}
