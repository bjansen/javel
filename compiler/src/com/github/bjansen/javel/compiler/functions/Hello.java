package com.github.bjansen.javel.compiler.functions;

import com.github.bjansen.javel.language.Function;
import com.github.bjansen.javel.language.JavelParser;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

public class Hello implements Function<MethodVisitor> {

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public void call(JavelParser.MethodCallContext ctx, MethodVisitor mv) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello, world!");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitMaxs(2, 1);
    }
}
