package extensions

fun String.prepend(element: Char, count: Int = 1): String =
	this.toCharArray().prepend(element, count).joinToString("")

fun String.append(element: Char, count: Int = 1): String =
	this.toCharArray().append(element, count).joinToString("")
