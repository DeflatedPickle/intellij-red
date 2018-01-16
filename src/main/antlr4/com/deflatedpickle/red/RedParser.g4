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

header: TITLE OPEN_BRKT header_options* CLOSE_BRKT;
header_options: assignment*;


statement: print_stmt | assignment;
assignment: (ID | OPTIONS) ASSIGN VALUE;
print_stmt: PRINT VALUE;