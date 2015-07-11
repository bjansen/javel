package com.github.bjansen.javel.language;

import java.util.HashMap;
import java.util.Map;

public class BuiltinFunctions {

    private static final Map<String, Function> BUILT_IN_FUNCTIONS = new HashMap<>();

    public static void register(Function function) {
        BUILT_IN_FUNCTIONS.put(function.getName(), function);
    }

    public static Function get(String name) {
        return BUILT_IN_FUNCTIONS.get(name);
    }
}
