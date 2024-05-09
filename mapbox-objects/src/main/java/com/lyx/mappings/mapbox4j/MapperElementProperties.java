package com.lyx.mappings.mapbox4j;

import com.lyx.mappings.mapbox4j.value.Delay;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Getter
@Setter
@ToString
public class MapperElementProperties<I> implements ElementProperties<I> {

    private Long insertionTimeAtNanos;
    private Delay expireOnWriteDelay;
    private Delay expireOnAccessDelay;
    private Predicate<I> predication;

    @Override
    public Long getInsertionTimeAtMillis() {
        return TimeUnit.NANOSECONDS.toMillis(insertionTimeAtNanos);
    }
}