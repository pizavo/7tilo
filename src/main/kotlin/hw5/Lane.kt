package hw5

data class Lane(val first: Node, val second: Node) {
    val id: Int = getNextId()
    var price: Price = Price(0)
        private set

    fun setPrice(price: Int) = run { this.price.value = price }

    infix fun priced(price: Int): Node {
        this.price = Price(price)
        return second
    }

    fun getOtherNode(node: Node): Node = if (node == first) second else first

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lane) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    companion object {
        private var id = 0
        private fun getNextId(): Int = ++id
    }
}