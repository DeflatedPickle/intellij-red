package com.deflatedpickle.red

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.*

object RedFileType : LanguageFileType(RedLanguage) {
    override fun getName() : String = "Red file"

    override fun getDescription() : String = "Red language file"

    override fun getDefaultExtension() : String = "red"

    override fun getIcon() : Icon = RedIcons.RED
}