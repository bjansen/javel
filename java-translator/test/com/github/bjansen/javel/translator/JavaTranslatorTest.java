package com.github.bjansen.javel.translator;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class JavaTranslatorTest {

    @Test
    public void testHello() {
        test("hello");
    }

    private void test(String name) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JavaTranslator.launch(readFile(name + ".javel"), bos);

        assertEquals(readFile(name + ".java"), bos.toString());
    }

    private String readFile(String fileName) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + fileName)));
        StringBuilder builder = new StringBuilder();

        try {
            String line = buf.readLine();
            while (line != null) {
                builder.append(line).append('\n');
                line = buf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }
}