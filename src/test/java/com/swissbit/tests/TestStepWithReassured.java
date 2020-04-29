package com.swissbit.tests;

import com.swissbit.keywords.KwsRest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step.handlers.javahandler.KeywordRunner;

import javax.json.Json;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestStepWithReassured {

    private static KeywordRunner.ExecutionContext ctx;

    @BeforeAll
    public static void setUp() {
        Map<String, String> properties = new HashMap<>();
        ctx = KeywordRunner.getExecutionContext(properties, KwsRest.class);
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void runTest(String argIn) throws Exception {

//        String arg = ctx.run("dummyKw", Json.createObjectBuilder()
//                .add("arg", argIn)
//                .build().toString()
//        ).getPayload().getString("arg");
//        assertEquals("hi there", arg);
//        assertFalse(arg.isEmpty());

        ctx.run("get slides", Json.createObjectBuilder()
                .add("uriPath", "/json")
                .build().toString()
        );
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("hi there")
        );
    }

    @AfterAll
    public static void tearDown() {
        ctx.close();
    }

}
