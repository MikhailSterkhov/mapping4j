package com.lyx.mappings.mapbox4j;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Mapping<I, V> {

    void delete(I index);

    void insert(I index, Supplier<V> value);

    void insert(I index, Supplier<V> value, MappingConfiguration<I> configuration);

    void insertAll(Mapping<I, V> other);

    Stream<MapElement<I, V>> stream();

    Optional<V> get(I index);

    Optional<MapElement<I, V>> getElement(I index);
}
