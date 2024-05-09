package com.lyx.mappings.mapbox4j;

import com.lyx.mappings.mapbox4j.value.Delay;

import java.util.function.Predicate;

public class MapperConfiguration<I> implements MappingConfiguration<I> {

    private final ElementProperties<I> handle = new MapperElementProperties<>(System.currentTimeMillis());

    @Override
    public ElementProperties<I> forElement() {
        return handle;
    }

    @Override
    public MappingConfiguration<I> expireOnWrite(Delay delay) {
        handle.setExpireOnWriteDelay(delay);
        return this;
    }

    @Override
    public MappingConfiguration<I> expireOnAccess(Delay delay) {
        handle.setExpireOnAccessDelay(delay);
        return null;
    }

    @Override
    public MappingConfiguration<I> predication(Predicate<I> predication) {
        handle.setIndexPredication(predication);
        return null;
    }
}
