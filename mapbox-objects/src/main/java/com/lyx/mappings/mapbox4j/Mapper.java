package com.lyx.mappings.mapbox4j;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Mapper<I, V> implements Mapping<I, V> {

    protected final Map<I, MapElement<I, V>> handle;

    private final Object lock = new Object();

    @Override
    public void delete(I index) {
        synchronized (lock) {
            handle.remove(index);
        }
    }

    @Override
    public void insert(I index, Supplier<V> value) {
        synchronized (lock) {
            handle.put(index, new MapperElement<>(index, value,
                    new MapperElementProperties<>()));
        }
    }

    @Override
    public void insert(I index, Supplier<V> value, MappingConfiguration<I> configuration) {
        synchronized (lock) {
            handle.put(index, new MapperElement<>(index, value,
                    configuration.forElement()));
        }
    }

    @Override
    public void insertAll(Mapping<I, V> other) {
        synchronized (lock) {
            other.stream().forEach(element ->
                    handle.put(element.index(),
                            new MapperElement<>(element.index(),
                                    element.factory(),
                                    element.properties())));
        }
    }

    @Override
    public Stream<MapElement<I, V>> stream() {
        return handle.values().stream();
    }

    @Override
    public Optional<V> get(I index) {
        return getElement(index).flatMap(MapElement::get);
    }

    @Override
    public Optional<MapElement<I, V>> getElement(I index) {
        return Optional.ofNullable(handle.get(index));
    }
}
