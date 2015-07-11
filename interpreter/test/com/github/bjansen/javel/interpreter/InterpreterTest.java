package com.github.bjansen.javel.interpreter;

import com.github.bjansen.javel.SyntaxError;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class InterpreterTest {

    private ByteArrayOutputStream out;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {
        out = new ByteArrayOutputStream();

        System.setOut(new PrintStream(out));
    }

    @Test
    public void testHello() {
        Interpreter.launch("hello();");

        assertOutput("Hello, world!\n");
    }

    @Test
    public void testUnknownFunction() {
        expectedEx.expect(SyntaxError.class);
        expectedEx.expectMessage("Unknown function 'goodbye'");

        Interpreter.launch("goodbye();");
    }

    private void assertOutput(String output) {
        System.out.flush();

        assertEquals("Console output matches", output, out.toString());
    }
}