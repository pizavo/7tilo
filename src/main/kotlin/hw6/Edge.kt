package hw6

data class Edge(val first: Node, val second: Node) {
    var weight: Int = 0
        private set

    infix fun over(weight: Int): Edge = this.also { this.weight = weight }

    fun getOtherNode(node: Node): Node = if (node == first) second else first

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Edge) return false
        if ((first != other.first && second != other.second)
            || (first != other.second && second != other.first)
        ) return false
        return true
    }

    override fun toString(): String {
        return "$first --$weight-- $second"
    }
}