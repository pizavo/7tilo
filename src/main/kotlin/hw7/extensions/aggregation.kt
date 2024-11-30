package hw7.extensions

import java.math.BigDecimal

fun Iterable<BigDecimal>.average() =
    reduce { acc, bigDecimal -> acc + bigDecimal } / BigDecimal.valueOf(count().toLong())