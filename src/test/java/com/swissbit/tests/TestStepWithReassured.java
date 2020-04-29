package com.swissbit.tests;

import com.swissbit.keywords.KwsRest;
import org.junit.jupiter.api.*;
import step.handlers.javahandler.KeywordRunner;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStepWithReassured {

    private static KeywordRunner.ExecutionContext ctx;
//    static String slideTitles;
    static String slideTitle1;
    static String slideTitle2;
    static String uuid1;
    static String uuid2;

    @BeforeAll
    public static void setUp() {
        Map<String, String> properties = new HashMap<>();
        ctx = KeywordRunner.getExecutionContext(properties, KwsRest.class);
    }

    @Test
    @Order(1)
    void getSlides() throws Exception {
        JsonObject getSlidesPayload = ctx.run("get slides", Json.createObjectBuilder()
                .add("uriPath", "/json")
                .build().toString()
        ).getPayload();
        String[] slideTitles = getSlidesPayload.getString("slideTitles").split("\\|");
        assertEquals("Sample Slide Show", getSlidesPayload.getString("slideshowTitle"));
        assertEquals(2, slideTitles.length);
        slideTitle1 = slideTitles[0];
        slideTitle2 = slideTitles[1];
    }

    @Test
    @Order(2)
    void getParams() throws Exception {
        JsonObject getParamsPayload = ctx.run("get params", Json.createObjectBuilder()
                .add("uriPath", "/get")
                .add("slideTitle1", slideTitle1)
                .add("slideTitle2", slideTitle2)
                .build().toString()
        ).getPayload();

        assertTrue(getParamsPayload.getString("originIp").length()>=8);
        assertEquals(2, getParamsPayload.getString("parameters").split("\\|").length);
    }

    @Test
    @Order(3)
    void getUuid1() throws Exception {
        String uuid = ctx.run("get uuid", Json.createObjectBuilder()
                .add("uriPath", "/uuid")
                .build().toString()
        ).getPayload().getString("uuid");
        assertEquals(36, uuid.length());
        uuid1 = uuid;
    }

    @Test
    @Order(4)
    void getUuid2() throws Exception {
        String uuid = ctx.run("get uuid", Json.createObjectBuilder()
                .add("uriPath", "/uuid")
                .build().toString()
        ).getPayload().getString("uuid");
        assertEquals(36, uuid.length());
        uuid2 = uuid;
    }

    @Test
    @Order(5)
    void postAnything() throws Exception {
        String quoteOfTheDay = "Apples fall. Birds fly. We live. We die.";
        String title = "Sample Slide Show";

        JsonObject postAnythingPayload = ctx.run("post anything", Json.createObjectBuilder()
                .add("uriPath", "/anything")
                .add("title", title)
                .add("quote", quoteOfTheDay)
                .add("quoteTitle1", slideTitle1)
                .add("quoteTitle2", slideTitle2)
                .add("uuid1", uuid1)
                .add("uuid2", uuid2)
                .build().toString()
        ).getPayload();
        assertEquals(quoteOfTheDay, postAnythingPayload.getString("quoteOfTheDay"));
        assertEquals(title, postAnythingPayload.getString("title"));
    }

    @AfterAll
    public static void tearDown() {
        ctx.close();
    }

}
