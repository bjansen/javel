// Generated from /Users/bastien/Dev/javel/language/grammar/Javel.g4 by ANTLR 4.4
package com.github.bjansen.javel.language;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavelParser}.
 */
public interface JavelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavelParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull JavelParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavelParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull JavelParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavelParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull JavelParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavelParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull JavelParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavelParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull JavelParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavelParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull JavelParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavelParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull JavelParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavelParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull JavelParser.MethodCallContext ctx);
}