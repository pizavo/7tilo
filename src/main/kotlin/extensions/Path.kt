package extensions

import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path

fun resource(file: String): File =
	File({}.javaClass.getResource("/$file")?.path ?: throw FileNotFoundException("Resource not found: $file"))