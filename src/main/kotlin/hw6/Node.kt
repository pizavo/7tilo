package hw6

data class Node(val name: String) {
    val edges = mutableListOf<Edge>()

    override fun toString(): String = name
    
    infix fun to(node: Node): Edge =
        Edge(this, node).also { edges.add(it); node.edges.add(it) }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false
        if (name != other.name) return false

        return true
    }
}