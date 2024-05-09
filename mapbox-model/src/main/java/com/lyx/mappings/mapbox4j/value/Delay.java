package com.lyx.mappings.mapbox4j.value;

import lombok.Value;

import java.util.concurrent.TimeUnit;

@Value(staticConstructor = "of")
public class Delay {

    long duration;

    TimeUnit unit;
}
