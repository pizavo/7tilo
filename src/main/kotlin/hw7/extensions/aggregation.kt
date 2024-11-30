package hw7.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Iterable<BigDecimal>.average(): BigDecimal =
    if (this.none()) BigDecimal.ZERO
    else reduce { acc, bigDecimal -> acc + bigDecimal }
        .divide(BigDecimal.valueOf(count().toLong()), RoundingMode.HALF_UP)