/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.red.psi

import com.deflatedpickle.red.RedLanguage
import com.deflatedpickle.red.RedParserDefinition
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode
import org.antlr.intellij.adaptor.psi.Trees

class RedIdentifierPSINode(type: IElementType, text: CharSequence) : ANTLRPsiLeafNode(type, text), PsiNamedElement {
    override fun setName(name: String): PsiElement {
        if (parent == null) return this

        Trees.createLeafFromText(project, RedLanguage, context, name, RedParserDefinition.ID)

        return this
    }
}
