package com.swissbit.keywords;

import step.handlers.javahandler.AbstractKeyword;
import step.handlers.javahandler.Keyword;

public class KwsRest extends AbstractKeyword {

    @Keyword
    public void dummyKw() {
        String arg = input.getString("arg");
        output.add("arg", arg);
    }

}
