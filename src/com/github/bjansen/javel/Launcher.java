package com.github.bjansen.javel;

import com.github.bjansen.javel.compiler.Compiler;
import com.github.bjansen.javel.interpreter.Interpreter;
import com.github.bjansen.javel.translator.JavaTranslator;

import java.io.*;
import java.nio.CharBuffer;

public class Launcher {
    public static void main(String[] args) {
        if (args.length == 0) {
            showUsage();
            return;
        }

        String command = args[0];

        try {
            switch (command) {
                case "java":
                    translateToJava(getFile(args, 1));
                    break;
                case "compile":
                    compile(getFile(args, 1));
                    break;
                default:
                    interpret(getFile(args, 0));
            }
        } catch (UsageException e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            }
            showUsage();
        }
    }

    private static void compile(File sourceFile) {
        try {
            String baseName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf('.'));
            Compiler.launch(getFileContent(sourceFile), baseName, new FileOutputStream(baseName + ".class"));
            System.out.println("Created " + baseName + ".class");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SyntaxError error) {
            System.err.println("Compilation error in program " + sourceFile.getName() + ":");
            System.err.println(error.getMessage());
            System.err.println("  at line " + error.line + ", column " + error.column);
        }
    }

    private static void interpret(File sourceFile) {
        try {
            Interpreter.launch(getFileContent(sourceFile));
        } catch (SyntaxError error) {
            System.err.println("Could not run program " + sourceFile.getName() + ":");
            System.err.println(error.getMessage());
            System.err.println("  at line " + error.line + ", column " + error.column);
        }
    }

    private static void translateToJava(File sourceFile) {
        try {
            String baseName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf('.'));
            FileOutputStream out = new FileOutputStream(baseName + ".java");
            JavaTranslator.launch(getFileContent(sourceFile), out);
            System.out.println("Created " + baseName + ".java");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SyntaxError error) {
            System.err.println("Translation error in program " + sourceFile.getName() + ":");
            System.err.println(error.getMessage());
            System.err.println("  at line " + error.line + ", column " + error.column);
        }
    }

    private static String getFileContent(File sourceFile) {
        int programSize = (int) sourceFile.length();
        CharBuffer buffer = CharBuffer.allocate(programSize);
        try {
            new FileReader(sourceFile).read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer.rewind();

        return buffer.toString();
    }
    private static File getFile(String args[], int position) {
        if (args.length <= position) {
            throw new UsageException(null);
        }
        File file = new File(args[position]);

        if (!file.exists() || file.isDirectory()) {
            throw new UsageException("Invalid file " + args[position]);
        }

        return file;
    }

    private static void showUsage() {
        System.out.println("Usage: javel [<command>] <file>");
    }

    private static class UsageException extends RuntimeException {
        public UsageException(String message) {
            super(message);
        }
    }
}
