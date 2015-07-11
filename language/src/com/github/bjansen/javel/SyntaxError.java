package com.github.bjansen.javel;

public class SyntaxError extends RuntimeException {

    public final String message;
    public final int line;
    public final int column;

    public SyntaxError(String message, int line, int column) {
        super(message);
        this.message = message;
        this.line = line;
        this.column = column;
    }
}
