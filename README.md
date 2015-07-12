# javel

A revolutionary language that runs on the JVM, and does nothing but print hello worlds.

## Foreword

> The typechecker is going to start rejecting useless programs, you can’t write
 hello world in Ceylon anymore. Sorry. - Gavin King

[Source](https://gitter.im/ceylon/user?at=559cfebf21e1d6761f2a2a6b)

## Motivation

Every journey starts with a Hello World™, when you learn a new programming language. 
Some are easy to write, while others require a lot of boilerplate. The industry needs a 
simple and standard way to do a Hello World™®©. That's exactly what Javel features:

```
hello();
```

```
mbp:javel bastien$ ./javel samples/hello.javel
Hello, world!
```

## (Real) Motivation

I just wanted to know how to design a language that can run on the JVM. I already knew a few things
about grammars and parsers, but there was a missing piece between that and the actual program
execution.

While extremely simplistic, Javel consists of:

- an ANTLR grammar, which automatically generates a lexer and parser
- an interpreter, written in Java, that can read and execute `.javel` files
- a Java translator, that shows it is possible to avoid the need to write your own compiler, and instead 
rely on the bulletproof `javac`
- a compiler, that transforms an AST parsed by ANTLR to JVM bytecode, thanks to ASM

These are the most common possibilities to "run" a language.

## Building and testing

To build everything, just clone this repository and use ant:

```
$ ant clean dist
```

Then create a Javel file, for example `hello.javel`:

```
hello();
```

Finally, you can run the program using three different approaches:

- the interpreter can be called with `./javel hello.javel`
- the translator can be called with `./javel java hello.javel` to produce a Java file named `hello.java`
- the compiler can be called with `./javel compile hello.javel` to produce bytecode in a file named `hello.class`

## Testimonial

> I will definitely recommend this awesome language the next time someone asks me how to build a 
cloud/nosql/distributed/fault-tolerant/docker based Hello World®™©®©®™! - Bob D.

> I was so depressed that I felt the need to write `goodbye();` programs all day long... Then a colleague of mine
told me about Javel, it gave me hope again! Praise Javel! - Franck S.

> Worst. Language. Ever. - Jeff Albertson.