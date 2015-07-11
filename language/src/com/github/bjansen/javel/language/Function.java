package com.github.bjansen.javel.language;

public interface Function<T> {

    String getName();

    void call(JavelParser.MethodCallContext ctx, T param);
}
