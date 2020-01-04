/*
    Parser
 */

parser grammar RedParser;

options {
    language=Java;
    tokenVocab=RedLexer;
}

program: header line* EOF;
line: statement;

header: TITLE OPEN_BRKT header_assignment* CLOSE_BRKT;

statement: print_stmt | assignment | header_assignment;
value: STRING | NUMBER | ID;
assignment: ID TYPE? ASSIGN value;
header_assignment: OPTION ASSIGN value;
print_stmt: PRINT value;