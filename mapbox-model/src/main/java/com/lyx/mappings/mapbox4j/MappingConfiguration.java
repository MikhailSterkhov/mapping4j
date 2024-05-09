package com.lyx.mappings.mapbox4j;

import com.lyx.mappings.mapbox4j.value.Delay;

import java.util.function.Predicate;

public interface MappingConfiguration<I> {

    ElementProperties<I> forElement();

    MappingConfiguration<I> expireOnWrite(Delay delay);

    MappingConfiguration<I> expireOnAccess(Delay delay);

    MappingConfiguration<I> predication(Predicate<I> predication);
}
