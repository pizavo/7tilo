package extensions

fun CharArray.prepend(element: Char, count: Int = 1): CharArray =
    CharArray(count) { element } + this

fun CharArray.append(element: Char, count: Int = 1): CharArray =
    this + CharArray(count) { element }
