package com.lyx.mappings.mapbox4j;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public final class Mapbox {

    public MappingBuilder builder() {
        return new MappingBuilder();
    }

    public <I> MappingConfiguration<I> configuration() {
        return new MapperConfiguration<>();
    }

    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

    public ObjectMapper createConcurrencyObjectMapper() {
        return new ObjectMapper(new ConcurrentHashMap<>());
    }

    public <I, V> Mapper<I, V> createMapper(Class<I> indexType, Class<V> valueType) {
        return new Mapper<>(new HashMap<>());
    }

    public <I, V> Mapper<I, V> createConcurrencyMapper(Class<I> indexType, Class<V> valueType) {
        return new Mapper<>(new ConcurrentHashMap<>());
    }
}
