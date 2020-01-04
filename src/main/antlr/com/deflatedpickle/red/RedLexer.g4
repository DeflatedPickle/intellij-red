/*
    Lexer
 */

lexer grammar RedLexer;

TITLE: 'Red' '/System'?;
OPTION: 'Title' | 'Purpose' | 'Author' | 'File' | 'Version' | 'Date'
      | 'Rights' | 'License' | 'History' | 'Tabs'
      | 'Note' | 'Notes' | 'Note(s)';

ASSIGN: ':';

TYPE: ('action'
      |'binary'
      |'bitset'
      |'block'
      |'char'
      |'datatype'
      |'date'
      |'email'
      |'error'
      |'event'
      |'file'
      |'float'
      |'function'
      |'get-path'
      |'get-word'
      |'handle'
      |'hash'
      |'image'
      |'integer'
      |'issue'
      |'lit-path'
      |'lit-word'
      |'logic'
      |'map'
      |'native'
      |'none'
      |'object'
      |'op'
      |'paren'
      |'path'
      |'percent'
      |'refinement'
      |'routine'
      |'set-path'
      |'set-word'
      |'string'
      |'tag'
      |'time'
      |'tuple'
      |'typeset'
      |'unset'
      |'url'
      |'vector'
      |'word') '!';

OPEN_BRKT: '[';
CLOSE_BRKT: ']';

OPEN_BRACE: '{';
CLOSE_BRACE: '}';

PRINT: 'print';

COMMENT: ';' .*? [\r\n] -> channel(HIDDEN);
MULTI_COMMENT: ID WS* OPEN_BRACE .*? CLOSE_BRACE -> channel(HIDDEN);
SPACE: [ \t\r\n] -> channel(HIDDEN);

STRING: '"' ( '\\"' | . )*? '"';
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
fragment LETTER: (LOWERCASE | UPPERCASE)+;
NUMBER: [0-9]+;

ID: LOWERCASE+ (LETTER | NUMBER | '-')*;

WS: [ \t\r\n\f]+ -> channel(HIDDEN);

ERROR: . -> channel(HIDDEN);