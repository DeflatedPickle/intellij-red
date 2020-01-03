/*
    Parser
 */

parser grammar RedParser;

options {
    language=Java;
    tokenVocab=RedLexer;
}

program: header? line* EOF;
line: statement;

header: TITLE OPEN_BRKT header_options* CLOSE_BRKT;
header_options: assignment;

statement: print_stmt | assignment;
value: STRING | NUMBER | ID;
assignment: (ID | OPTION) ASSIGN value;
print_stmt: PRINT value;