/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.red

import com.deflatedpickle.red.psi.RedPSIFileRoot
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory.createTokenSet
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree

class RedParserDefinition : ParserDefinition {
    companion object {
        val FILE = IFileElementType(RedLanguage)

        val ID: TokenIElementType

        init {
            PSIElementTypeFactory.defineLanguageIElementTypes(RedLanguage, RedParser.tokenNames, RedParser.ruleNames)

            val tokenIElementTypes = PSIElementTypeFactory.getTokenIElementTypes(RedLanguage)
            ID = tokenIElementTypes[RedLexer.ID]
        }

        val COMMENTS: TokenSet = createTokenSet(
                RedLanguage,
                RedLexer.COMMENT,
                RedLexer.MULTI_COMMENT)

        val WHITESPACE: TokenSet = createTokenSet(
                RedLanguage,
                RedLexer.WS)

        val STRING: TokenSet = createTokenSet(
                RedLanguage,
                RedLexer.STRING)
    }

    override fun createLexer(project: Project?): Lexer = ANTLRLexerAdaptor(RedLanguage, RedLexer(null))
    override fun createParser(project: Project?): PsiParser = object : ANTLRParserAdaptor(RedLanguage, RedParser(null)) {
        override fun parse(parser: Parser?, root: IElementType?): ParseTree {
            val newParse = parser as RedParser
            return if (root is IFileElementType) {
                newParse.program()
            } else {
                newParse.value()
            }
        }
    }

    override fun getWhitespaceTokens(): TokenSet = WHITESPACE
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = STRING
    override fun getFileNodeType(): IFileElementType = FILE

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements = ParserDefinition.SpaceRequirements.MAY

    override fun createFile(viewProvider: FileViewProvider): PsiFile = RedPSIFileRoot(viewProvider)

    override fun createElement(node: ASTNode): PsiElement {
        return if (node.elementType is TokenIElementType || node.elementType !is RuleIElementType) {
            ANTLRPsiNode(node)
        } else {
            when ((node.elementType as RuleIElementType).ruleIndex) {
                else -> ANTLRPsiNode(node)
            }
        }
    }
}
