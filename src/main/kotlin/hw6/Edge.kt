package hw6

data class Edge(val first: Node, val second: Node, val weight: Int = 0) {
    infix fun over(weight: Int): Edge = Edge(first, second, weight)

    fun getOtherNode(node: Node): Node = if (node == first) second else first

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Edge) return false
        if (first != other.first && second != other.second) return false
        if (first != other.second && second != other.first) return false
        return true
    }
    
    override fun toString(): String {
        return "$first --$weight-- $second"
    }
}