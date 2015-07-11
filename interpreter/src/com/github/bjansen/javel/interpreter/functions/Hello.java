package com.github.bjansen.javel.interpreter.functions;

import com.github.bjansen.javel.language.Function;
import com.github.bjansen.javel.language.JavelParser;

public class Hello implements Function<Void> {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public void call(JavelParser.MethodCallContext ctx, Void ignore) {
        System.out.println("Hello, world!");
    }
}
