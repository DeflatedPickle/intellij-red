/*
    Lexer
 */

lexer grammar RedLexer;

TITLE: 'Red';
OPTION: 'Title';

ASSIGN: ':';

OPEN_BRKT: '[';
CLOSE_BRKT: ']';

PRINT: 'print';

COMMENT: ';' ~[\r\n]* -> skip;
MULTI_COMMENT: ID '{' ~[\r\n]* '}' -> skip;
SPACE: [ \t\r\n] -> skip;

STRING: ["] ~["\r\n]* ["];
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment LETTER: (LOWERCASE | UPPERCASE)+;
NUMBER: [0-9]+;

ID: LOWERCASE+ (LETTER | NUMBER)*;
VALUE: STRING | NUMBER | ID ;

WS: [ \t\r\n\f]+ -> skip;