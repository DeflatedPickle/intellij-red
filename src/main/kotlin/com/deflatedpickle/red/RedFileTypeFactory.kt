/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.red

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

class RedFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(RedFileType, "red")
    }
}
