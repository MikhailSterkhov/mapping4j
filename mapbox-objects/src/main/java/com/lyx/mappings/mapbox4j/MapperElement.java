package com.lyx.mappings.mapbox4j;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.function.Supplier;

@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class MapperElement<I, V> implements MapElement<I, V> {

    @EqualsAndHashCode.Include
    private final I index;
    @ToString.Include
    private final Supplier<V> value;

    private final ElementProperties<I> properties;

    @Override
    public ElementProperties<I> properties() {
        return properties;
    }

    @Override
    public Optional<V> get() {
        return Optional.ofNullable(value).map(Supplier::get);
    }

    @Override
    public Supplier<V> factory() {
        return value;
    }

    @Override
    public I index() {
        return index;
    }
}