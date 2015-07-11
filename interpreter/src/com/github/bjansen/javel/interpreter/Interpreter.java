package com.github.bjansen.javel.interpreter;

import com.github.bjansen.javel.SyntaxError;
import com.github.bjansen.javel.interpreter.functions.Hello;
import com.github.bjansen.javel.language.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import static com.github.bjansen.javel.language.BuiltinFunctions.register;

public class Interpreter extends JavelBaseVisitor {

    static {
        register(new Hello());
    }

    public static void launch(String content) {
        JavelLexer lexer = new JavelLexer(new ANTLRInputStream(content));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JavelParser parser = new JavelParser(tokenStream);

        parser.program().accept(new Interpreter());
    }

    @Override
    public Object visitMethodCall(@NotNull JavelParser.MethodCallContext ctx) {
        String methodName = ctx.IDENTIFIER().getText();

        Function<Void> function = BuiltinFunctions.get(methodName);

        if (function == null) {
            throw new SyntaxError("Unknown function '" + methodName + "'", ctx.start.getLine(), ctx.start.getCharPositionInLine());
        } else {
            function.call(ctx, null);
        }
        return super.visitMethodCall(ctx);
    }
}
