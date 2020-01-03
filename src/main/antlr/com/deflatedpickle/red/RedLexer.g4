/*
    Lexer
 */

lexer grammar RedLexer;

TITLE: 'Red';
OPTION: 'Title';

ASSIGN: ':';

OPEN_BRKT: '[';
CLOSE_BRKT: ']';

OPEN_BRACE: '{';
CLOSE_BRACE: '}';

PRINT: 'print';

COMMENT: ';' ~[\r\n]* -> skip;
MULTI_COMMENT: ID? OPEN_BRACE .*? CLOSE_BRACE -> skip;
SPACE: [ \t\r\n] -> skip;

STRING: '"' ( '\\"' | . )*? '"';
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment LETTER: (LOWERCASE | UPPERCASE)+;
NUMBER: [0-9]+;

ID: LOWERCASE+ (LETTER | NUMBER | '-')*;

WS: [ \t\r\n\f]+ -> skip;