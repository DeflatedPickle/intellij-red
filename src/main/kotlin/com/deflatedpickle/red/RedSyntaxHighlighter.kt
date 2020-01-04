package com.deflatedpickle.red

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.TokenIElementType


class RedSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val EMPTY_KEYS = emptyArray<TextAttributesKey>()

        val ID: TextAttributesKey = createTextAttributesKey("RED_ID", DefaultLanguageHighlighterColors.IDENTIFIER)
        val KEYWORD: TextAttributesKey = createTextAttributesKey("RED_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING: TextAttributesKey = createTextAttributesKey("RED_STRING", DefaultLanguageHighlighterColors.STRING)
        val NUMBER: TextAttributesKey = createTextAttributesKey("RED_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val LINE_COMMENT: TextAttributesKey = createTextAttributesKey("RED_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT: TextAttributesKey = createTextAttributesKey("RED_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)

        init {
            PSIElementTypeFactory.defineLanguageIElementTypes(RedLanguage, RedParser.tokenNames, RedParser.ruleNames)
        }
    }

    override fun getHighlightingLexer(): Lexer = ANTLRLexerAdaptor(RedLanguage, RedLexer(null))

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType !is TokenIElementType) return EMPTY_KEYS

        return arrayOf(when (tokenType.antlrTokenType) {
            RedLexer.ID -> ID
            RedLexer.TITLE, RedLexer.OPTION, RedLexer.TYPE, RedLexer.PRINT -> KEYWORD
            RedLexer.STRING -> STRING
            RedLexer.NUMBER -> NUMBER
            RedLexer.COMMENT -> LINE_COMMENT
            RedLexer.MULTI_COMMENT -> BLOCK_COMMENT
            else -> return EMPTY_KEYS
        })
    }
}