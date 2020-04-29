package com.swissbit.keywords;

import com.swissbit.pojo.Slideshow;
import com.swissbit.pojo.SlideshowParent;
import com.swissbit.utils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.io.IoBuilder;
import step.handlers.javahandler.AbstractKeyword;
import step.handlers.javahandler.Keyword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintStream;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class KwsRest extends AbstractKeyword {

    private static final Logger logger = LogManager.getLogger();

    @Keyword
    public void dummyKw() {
        String arg = input.getString("arg");
        logger.info("input argument is: " + arg);
        output.add("arg", arg);
    }

    @Keyword(name = "get slides")
    public void getSlides() throws IOException {
        String uriPath = input.getString("uriPath"); // "/json"
        //@formatter:off
        Response response =
                given()
                        .spec(RestUtils.getRequestSpecification())
                        .expect().defaultParser(Parser.JSON)
                .when()
                        .get(uriPath);
        //@formatter:on
        response.then().spec(RestUtils.getResponseSpecification());
        Slideshow slideshow = response.as(SlideshowParent.class).getSlideshow();
        String slideshowTitle = slideshow.getTitle();
        String slideTitles = slideshow.getSlides().stream().map(s -> s.getTitle()).collect(Collectors.joining("|"));
        logger.info("slide titles: " + slideTitles);
        logger.info("slideshow title: " + slideshowTitle);
        output.add("slide titles", slideTitles);
        output.add("slideshow title", slideshowTitle);
    }

}
