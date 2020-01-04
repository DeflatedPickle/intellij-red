/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.red

import com.deflatedpickle.red.psi.RedIdentifierPSINode
import com.intellij.core.CoreASTFactory
import com.intellij.psi.impl.source.tree.LeafElement
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType

class RedASTFactory : CoreASTFactory() {
    override fun createLeaf(type: IElementType, text: CharSequence): LeafElement {
        return if (type is TokenIElementType && type.antlrTokenType == RedLexer.ID) {
            RedIdentifierPSINode(type, text)
        } else {
            return super.createLeaf(type, text)
        }
    }
}
