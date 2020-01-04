/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.red.psi

import com.deflatedpickle.red.RedFileType
import com.deflatedpickle.red.RedIcons
import com.deflatedpickle.red.RedLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import javax.swing.Icon
import org.antlr.intellij.adaptor.SymtabUtils
import org.antlr.intellij.adaptor.psi.ScopeNode

class RedPSIFileRoot(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RedLanguage), ScopeNode {
    override fun getFileType(): FileType = RedFileType
    override fun getIcon(flags: Int): Icon? = RedIcons.RED

    override fun getContext(): ScopeNode? = null

    override fun resolve(element: PsiNamedElement?): PsiElement? {
        return SymtabUtils.resolve(this, RedLanguage, element, "/script/vardef/ID")
    }

    override fun toString(): String = "Red Language file"
}
