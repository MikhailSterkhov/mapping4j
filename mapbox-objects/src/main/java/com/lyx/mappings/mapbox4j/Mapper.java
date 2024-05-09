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
                    new MapperElementProperties<>(System.currentTimeMillis())));
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
        return handle.values().stream().filter(this::doVerify);
    }

    @Override
    public Optional<V> get(I index) {
        return getElement(index).flatMap(MapElement::get);
    }

    @Override
    public Optional<MapElement<I, V>> getElement(I index) {
        MapElement<I, V> mapElement = handle.get(index);
        if (!doVerify(mapElement)) {
            delete(index);
            return Optional.empty();
        }
        return Optional.ofNullable(mapElement);
    }

    private boolean doVerify(MapElement<I, V> element) {
        if (element == null) {
            return true;
        }

        ElementProperties<I> properties = element.properties();

        long accessTimeout = properties.getExpireOnAccessDelay().withAtMillis(properties.getInsertionTimeAtNanos());
        if (accessTimeout - System.currentTimeMillis() < 0) {
            return false;
        }

        if (properties.getIndexPredication() != null && !properties.getIndexPredication().test(element.index())) {
            return false;
        }

        return true;
    }
}
