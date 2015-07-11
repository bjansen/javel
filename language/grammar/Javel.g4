grammar Javel;

program
    : statement* EOF
    ;

statement
    : methodCall ';'
    ;

methodCall
    : IDENTIFIER arguments
    ;

arguments
    : '()'
    ;

PAREN           : '()';
IDENTIFIER      : [a-z]+;
WS              : [ \t\r\n]+ -> skip;
