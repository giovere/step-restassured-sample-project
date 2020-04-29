package com.swissbit.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.IOException;
import java.io.PrintStream;

public class RestUtils {
    private static final Logger logger = LogManager.getLogger();

    public static RequestSpecification getRequestSpecification() throws IOException {
        PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
        String baseUri = Properties.getProperty("baseUri");
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUri)
//                .addFilter(RequestLoggingFilter.logRequestTo(logStream))
//                .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                .build();
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

}
