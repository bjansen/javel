// Generated from /Users/bastien/Dev/javel/language/grammar/Javel.g4 by ANTLR 4.4
package com.github.bjansen.javel.language;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavelParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull JavelParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavelParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull JavelParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavelParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull JavelParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavelParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(@NotNull JavelParser.MethodCallContext ctx);
}