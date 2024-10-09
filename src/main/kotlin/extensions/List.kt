package extensions

inline fun <reified T> List<*>.toTypedList(): List<T>? =
    map { it as? T ?: return@toTypedList null }