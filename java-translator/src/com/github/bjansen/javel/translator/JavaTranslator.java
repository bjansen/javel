package com.github.bjansen.javel.translator;

import com.github.bjansen.javel.SyntaxError;
import com.github.bjansen.javel.language.*;
import com.github.bjansen.javel.translator.functions.Hello;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.IOException;
import java.io.OutputStream;

import static com.github.bjansen.javel.language.BuiltinFunctions.register;

public class JavaTranslator extends JavelBaseVisitor<Void> {

    static {
        register(new Hello());
    }

    private OutputStream out;

    public static void launch(String content, OutputStream out) {
        JavelLexer lexer = new JavelLexer(new ANTLRInputStream(content));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JavelParser parser = new JavelParser(tokenStream);

        parser.program().accept(new JavaTranslator(out));
    }

    private JavaTranslator(OutputStream out) {
        this.out = out;
    }

    @Override
    public Void visitProgram(@NotNull JavelParser.ProgramContext ctx) {
        writeLine("public class Main {");
        writeLine("    public static void main(String args[]) {");
        Void result = super.visitProgram(ctx);
        writeLine("    }");
        writeLine("}");

        return result;
    }

    @Override
    public Void visitMethodCall(@NotNull JavelParser.MethodCallContext ctx) {
        String functionName = ctx.IDENTIFIER().getText();
        Function<JavaTranslator> function = BuiltinFunctions.get(functionName);

        if (function == null) {
            throw new SyntaxError("Unknown function '" + functionName + "'", ctx.start.getLine(), ctx.start.getCharPositionInLine());
        } else {
            function.call(ctx, this);
        }

        return super.visitMethodCall(ctx);
    }

    public void writeLine(String content) {
        try {
            out.write(content.getBytes());
            out.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
