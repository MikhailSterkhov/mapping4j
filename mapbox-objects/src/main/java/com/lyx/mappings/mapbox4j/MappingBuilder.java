package com.lyx.mappings.mapbox4j;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public final class MappingBuilder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MappingBuilder insert(Object index, Supplier<Object> value) {
        objectMapper.insert(index, value);
        return this;
    }

    public <I> MappingBuilder insert(I index, Supplier<Object> value, MappingConfiguration<I> configuration) {
        //noinspection unchecked
        objectMapper.insert(index, value, (MappingConfiguration<Object>) configuration);
        return this;
    }

    public <I, V> Mapper<I, V> toMapper(Class<I> indexType, Class<V> valueType) {
        Mapper<I, V> mapper = Mapbox.createMapper(indexType, valueType);
        //noinspection unchecked
        mapper.insertAll((Mapping<I, V>) objectMapper);
        return mapper;
    }

    public <I, V> Mapper<I, V> toConcurrencyMapper(Class<I> indexType, Class<V> valueType) {
        Mapper<I, V> mapper = Mapbox.createConcurrencyMapper(indexType, valueType);
        //noinspection unchecked
        mapper.insertAll((Mapping<I, V>) objectMapper);
        return mapper;
    }

    public ObjectMapper toObjectMapper() {
        ObjectMapper mapper = Mapbox.createObjectMapper();
        mapper.insertAll(objectMapper);
        return mapper;
    }

    public ObjectMapper toConcurrencyObjectMapper() {
        ObjectMapper mapper = Mapbox.createConcurrencyObjectMapper();
        mapper.insertAll(objectMapper);
        return mapper;
    }
}
