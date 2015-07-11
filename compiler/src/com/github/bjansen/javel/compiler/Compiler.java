package com.github.bjansen.javel.compiler;

import com.github.bjansen.javel.SyntaxError;
import com.github.bjansen.javel.compiler.functions.Hello;
import com.github.bjansen.javel.language.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.io.OutputStream;

import static com.github.bjansen.javel.language.BuiltinFunctions.register;
import static org.objectweb.asm.Opcodes.*;

public class Compiler extends JavelBaseVisitor {

    static {
        register(new Hello());
    }

    private ClassWriter writer;
    private MethodVisitor methodVisitor;
    private String className;

    public static void launch(String content, String className, OutputStream out) {
        JavelLexer lexer = new JavelLexer(new ANTLRInputStream(content));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JavelParser parser = new JavelParser(tokenStream);

        Compiler compiler = new Compiler(className);
        parser.program().accept(compiler);
        try {
            out.write(compiler.getBytecode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Compiler(String className) {
        this.className = className;
        this.writer = new ClassWriter(0);
    }

    public byte[] getBytecode() {
        return writer.toByteArray();
    }

    @Override
    public Object visitProgram(@NotNull JavelParser.ProgramContext ctx) {
        writer.visit(V1_6, ACC_PUBLIC, className, null, "java/lang/Object", null);

        methodVisitor = writer.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        Object result = super.visitProgram(ctx);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitEnd();

        writer.visitEnd();
        return result;
    }

    @Override
    public Object visitMethodCall(@NotNull JavelParser.MethodCallContext ctx) {
        String functionName = ctx.IDENTIFIER().getText();

        Function<MethodVisitor> function = BuiltinFunctions.get(functionName);

        if (function == null) {
            throw new SyntaxError("Unknown function '" + functionName + "'", ctx.start.getLine(), ctx.start.getCharPositionInLine());
        } else {
            function.call(ctx, methodVisitor);
        }

        return super.visitMethodCall(ctx);
    }
}
