package com.github.bjansen.javel.translator.functions;

import com.github.bjansen.javel.language.Function;
import com.github.bjansen.javel.language.JavelParser;
import com.github.bjansen.javel.translator.JavaTranslator;

public class Hello implements Function<JavaTranslator> {
    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public void call(JavelParser.MethodCallContext ctx, JavaTranslator translator) {
        translator.writeLine("System.out.println(\"Hello, world!\");");
    }
}
