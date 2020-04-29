package com.swissbit.pojo;

import java.util.List;

public class Anything {
    String title;
    String quoteOfTheDay;
    List<QuoteSlide> quoteSlides;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuoteOfTheDay() {
        return quoteOfTheDay;
    }

    public void setQuoteOfTheDay(String quoteOfTheDay) {
        this.quoteOfTheDay = quoteOfTheDay;
    }

    public List<QuoteSlide> getQuoteSlides() {
        return quoteSlides;
    }

    public void setQuoteSlides(List<QuoteSlide> quoteSlides) {
        this.quoteSlides = quoteSlides;
    }
}
