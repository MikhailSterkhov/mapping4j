package com.lyx.mappings.mapbox4j.test;

import com.lyx.mappings.mapbox4j.Mapbox;
import com.lyx.mappings.mapbox4j.Mapper;
import com.lyx.mappings.mapbox4j.ObjectMapper;
import com.lyx.mappings.mapbox4j.test.objects.carmodel.*;
import com.lyx.mappings.mapbox4j.test.objects.message.BotMessage;
import com.lyx.mappings.mapbox4j.test.objects.message.MailMessage;
import com.lyx.mappings.mapbox4j.test.objects.message.Message;
import com.lyx.mappings.mapbox4j.test.objects.message.RemoteMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapboxWithMessagesTest {

    @Test
    public void test_builderMapper() {
        assertMapper(Mapbox.builder()
                .insert("itzstonlex", RemoteMessage::new)
                .insert("mikhail.sterkhov", MailMessage::new)
                .insert("github", BotMessage::new)
                .toMapper(String.class, Message.class));
    }

    @Test
    public void test_justMapper() {
        Mapper<String, Message> mapper = Mapbox.createMapper(String.class, Message.class);

        mapper.insert("itzstonlex", RemoteMessage::new);
        mapper.insert("mikhail.sterkhov", MailMessage::new);
        mapper.insert("github", BotMessage::new);

        assertMapper(mapper);
    }

    @Test
    public void test_objectMapper() {
        ObjectMapper objectMapper = Mapbox.createObjectMapper();

        objectMapper.insert("itzstonlex", RemoteMessage::new);
        objectMapper.insert("mikhail.sterkhov", MailMessage::new);
        objectMapper.insert("github", BotMessage::new);

        Mapper<String, Message> mapper = objectMapper.castTo(String.class, Message.class);

        assertMapper(mapper);
    }

    private static void assertMapper(Mapper<String, Message> mapper) {
        Message messageByItzstonlex = mapper.get("itzstonlex").orElseThrow(() -> new IllegalArgumentException("no sender name"));
        Message messageByGithub = mapper.get("github").orElseThrow(() -> new IllegalArgumentException("no sender name"));

        Assertions.assertEquals(RemoteMessage.class, messageByItzstonlex.getClass());
        Assertions.assertEquals(BotMessage.class, messageByGithub.getClass());

        System.out.println(messageByItzstonlex.getClass());
        System.out.println(messageByGithub.getClass());
    }
}
