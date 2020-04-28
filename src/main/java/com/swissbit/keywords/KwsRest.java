package com.swissbit.keywords;

import step.handlers.javahandler.AbstractKeyword;
import step.handlers.javahandler.Keyword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KwsRest extends AbstractKeyword {

    private static final Logger logger = LogManager.getLogger();

    @Keyword
    public void dummyKw() {
        String arg = input.getString("arg");
        logger.info("input argument is: " + arg);
        output.add("arg", arg);
    }

}
