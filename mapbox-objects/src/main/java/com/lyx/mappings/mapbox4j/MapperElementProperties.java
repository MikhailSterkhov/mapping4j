package com.lyx.mappings.mapbox4j;

import com.lyx.mappings.mapbox4j.value.Delay;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MapperElementProperties<I> implements ElementProperties<I> {

    private final Long insertionTimeAtMillis;

    private Delay expireOnAccessDelay;
    private Predicate<I> indexPredication;

    @Override
    public Long getInsertionTimeAtNanos() {
        return TimeUnit.MICROSECONDS.toNanos(insertionTimeAtMillis);
    }
}