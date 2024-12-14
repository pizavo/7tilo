package extensions

import java.math.BigInteger

fun BigInteger.toWords(): String {
    if (this == BigInteger.ZERO) return "zero"

    val suffixes = listOf(
        "" to BigInteger.ONE,
        "million" to BigInteger("1000000"),
        "billion" to BigInteger("1000000000"),
        "trillion" to BigInteger("1000000000000"),
        "quadrillion" to BigInteger("1000000000000000"),
        "quintillion" to BigInteger("1000000000000000000"),
        "sextillion" to BigInteger("1000000000000000000000"),
        "septillion" to BigInteger("1000000000000000000000000"),
        "octillion" to BigInteger("1000000000000000000000000000"),
        "nonillion" to BigInteger("1000000000000000000000000000000"),
        "decillion" to BigInteger("1000000000000000000000000000000000"),
        "undecillion" to BigInteger("1000000000000000000000000000000000000"),
        "duodecillion" to BigInteger("1000000000000000000000000000000000000000"),
        "tredecillion" to BigInteger("1000000000000000000000000000000000000000000"),
        "quattuordecillion" to BigInteger("1000000000000000000000000000000000000000000000")
    )

    val formattedWords = mutableListOf<String>()

    for ((suffix, value) in suffixes.asReversed()) {
        if (this >= value) {
            formattedWords.add("%.1f %s".format(this.toBigDecimal().divide(value.toBigDecimal()), suffix))
            break
        }
    }

    return formattedWords.joinToString(separator = " ").trim()
}