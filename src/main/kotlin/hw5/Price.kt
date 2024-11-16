package hw5

data class Price(var value: Int) {
    override fun toString(): String = value.toString()
}

operator fun Price.plusAssign(price: Int) { value += price }
operator fun Price.minusAssign(price: Int) { value -= price }
operator fun Price.compareTo(price: Int): Int = value.compareTo(price)

operator fun Int.plus(price: Price): Int = this + price.value
operator fun Int.minus(price: Price): Int = this - price.value
operator fun Int.compareTo(price: Price): Int = this.compareTo(price.value)