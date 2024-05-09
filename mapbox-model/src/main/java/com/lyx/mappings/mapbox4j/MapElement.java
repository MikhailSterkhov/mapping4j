package com.lyx.mappings.mapbox4j;

import java.util.Optional;
import java.util.function.Supplier;

public interface MapElement<I, V> {

    ElementProperties<I> properties();

    Optional<V> get();

    Supplier<V> factory();

    I index();
}
