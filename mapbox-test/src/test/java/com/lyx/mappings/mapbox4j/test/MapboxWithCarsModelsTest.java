package com.lyx.mappings.mapbox4j.test;

import com.lyx.mappings.mapbox4j.Mapbox;
import com.lyx.mappings.mapbox4j.Mapper;
import com.lyx.mappings.mapbox4j.ObjectMapper;
import com.lyx.mappings.mapbox4j.test.objects.carmodel.*;
import com.lyx.mappings.mapbox4j.value.Delay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class MapboxWithCarsModelsTest {

    @Test
    public void test_builderMapper() {
        assertMapper(Mapbox.builder()
                .insert(0x01, Bmw::new, Mapbox.configuration().expireOnAccess(Delay.of(5, TimeUnit.SECONDS)))
                .insert(0x02, Chevrolet::new)
                .insert(0x03, Hyundai::new)
                .insert(0x04, Mercedes::new)
                .insert(0x05, Omoda::new)
                .insert(0x06, Volkswagen::new)
                .toMapper(Integer.class, CarModel.class));
    }

    @Test
    public void test_justMapper() {
        Mapper<Integer, CarModel> mapper = Mapbox.createMapper(Integer.class, CarModel.class);

        mapper.insert(0x01, Bmw::new, Mapbox.<Integer>configuration().expireOnAccess(Delay.of(5, TimeUnit.SECONDS)));
        mapper.insert(0x02, Chevrolet::new);
        mapper.insert(0x03, Hyundai::new);
        mapper.insert(0x04, Mercedes::new);
        mapper.insert(0x05, Omoda::new);
        mapper.insert(0x06, Volkswagen::new);

        assertMapper(mapper);
    }

    @Test
    public void test_objectMapper() {
        ObjectMapper objectMapper = Mapbox.createObjectMapper();

        objectMapper.insert(0x01, Bmw::new, Mapbox.configuration().expireOnAccess(Delay.of(5, TimeUnit.SECONDS)));
        objectMapper.insert(0x02, Chevrolet::new);
        objectMapper.insert(0x03, Hyundai::new);
        objectMapper.insert(0x04, Mercedes::new);
        objectMapper.insert(0x05, Omoda::new);
        objectMapper.insert(0x06, Volkswagen::new);

        Mapper<Integer, CarModel> mapper = objectMapper.castTo(Integer.class, CarModel.class);

        assertMapper(mapper);
    }

    private static void assertMapper(Mapper<Integer, CarModel> mapper) {
        CarModel carModel3 = mapper.get(0x03).orElseThrow(() -> new IllegalArgumentException("no index"));
        CarModel carModel6 = mapper.get(0x06).orElseThrow(() -> new IllegalArgumentException("no index"));

        Assertions.assertEquals("Hyundai", carModel3.name());
        Assertions.assertEquals("Volkswagen", carModel6.name());

        System.out.println(carModel3.name());
        System.out.println(carModel6.name());
    }
}
