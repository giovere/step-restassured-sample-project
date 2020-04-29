package com.swissbit.keywords;

import com.swissbit.pojo.*;
import com.swissbit.utils.RestUtils;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import step.handlers.javahandler.AbstractKeyword;
import step.handlers.javahandler.Keyword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class KwsRest extends AbstractKeyword {

    private static final Logger logger = LogManager.getLogger();

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
        output.add("slideTitles", slideTitles);
        output.add("slideshowTitle", slideshowTitle);
    }


    @Keyword(name = "get params")
    public void getParams() throws IOException {
        String uriPath = input.getString("uriPath"); // "/get"
        String slideTitle1 = input.getString("slideTitle1");
        String slideTitle2 = input.getString("slideTitle2");

        //@formatter:off
        Response response =
                given()
                        .spec(RestUtils.getRequestSpecification())
                        .param("param", slideTitle1)
                        .param("param", slideTitle2)
                        .expect().defaultParser(Parser.JSON)
                .when()
                        .get(uriPath);
        //@formatter:on
        response.then().spec(RestUtils.getResponseSpecification());
        Param param = response.as(Param.class);

        String originIp = param.getOrigin();
        String parameters = param.getArgs().getParam().stream().collect(Collectors.joining("|"));

        logger.info("OriginIP: " + originIp);
        logger.info("parameters " + parameters);
        output.add("originIp", originIp);
        output.add("parameters", parameters);
    }

    @Keyword(name = "get uuid")
    public void getUuid() throws IOException {
        String uriPath = input.getString("uriPath"); // "/uuid"

        //@formatter:off
        Response response =
                given()
                        .spec(RestUtils.getRequestSpecification())
                        .expect().defaultParser(Parser.JSON)
                .when()
                        .get(uriPath);
        //@formatter:on
        response.then().spec(RestUtils.getResponseSpecification());
        String uuid = response.as(Uuid.class).getUuid();

        logger.info("UUID: " + uuid);
        output.add("uuid", uuid);
    }

    @Keyword(name = "post anything")
    public void postAnything() throws IOException {
        String uriPath = input.getString("uriPath"); // "/anything"

        //@formatter:off
        Response response =
                given()
                        .spec(RestUtils.getRequestSpecification())
                        .body(getAnything())
                        .expect().defaultParser(Parser.JSON)
                .when()
                        .post(uriPath);
        //@formatter:on
        response.then().spec(RestUtils.getResponseSpecification());
        AnythingResponse anythingResponse = response.as(AnythingResponse.class);
        Anything anything = anythingResponse.getJson();

        logger.info("Method: " + anythingResponse.getMethod());
        logger.info("Quote of the day: " + anything.getQuoteOfTheDay());
        logger.info("Title: " + anything.getTitle());
        output.add("title", anything.getTitle());
        output.add("quoteOfTheDay", anything.getQuoteOfTheDay());

        IntStream.range(0, 2).forEach(i -> {
                    String pairNo = "pair" + (i + 1);
                    String pair = anything.getQuoteSlides().get(i).getTitle() + "#" + anything.getQuoteSlides().get(i);
                    logger.info(pairNo + ": " + pair);
                    output.add(pairNo, pair);
                }
        );
    }

    private Anything getAnything() {
        String title = input.getString("title");
        String quote = input.getString("quote");

        List<QuoteSlide> quoteSlides = new ArrayList<>();
        IntStream.range(1, 3).forEach(i -> quoteSlides.add(getQuoteSlide(i)));

        Anything anything = new Anything();
        anything.setTitle(title);
        anything.setQuoteOfTheDay(quote);
        anything.setQuoteSlides(quoteSlides);
        return anything;
    }

    private QuoteSlide getQuoteSlide(int i) {
        String quoteTitle = input.getString("quoteTitle" + i);
        String uuid = input.getString("uuid" + i);
        QuoteSlide quoteSlide = new QuoteSlide();
        quoteSlide.setTitle(quoteTitle);
        quoteSlide.setUuid(uuid);
        return quoteSlide;
    }

}
