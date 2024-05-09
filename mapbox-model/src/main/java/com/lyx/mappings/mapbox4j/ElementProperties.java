package com.lyx.mappings.mapbox4j;

import com.lyx.mappings.mapbox4j.value.Delay;

import java.util.function.Predicate;

public interface ElementProperties<I> {

    void setExpireOnWriteDelay(Delay delay);

    void setExpireOnAccessDelay(Delay delay);

    void setIndexPredication(Predicate<I> predication);

    Delay getExpireOnWriteDelay();

    Delay getExpireOnAccessDelay();

    Predicate<I> getIndexPredication();

    Long getInsertionTimeAtNanos();

    Long getInsertionTimeAtMillis();
}
