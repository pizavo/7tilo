package extensions

inline fun <reified T> List<*>.toNullableTypedList(): List<T>? =
    map { it as? T ?: return@toNullableTypedList null }

inline fun <reified T> List<*>.toTypedList(): List<T> =
    map { it as T }