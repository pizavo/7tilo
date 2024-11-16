package hw5

data class Node(var value: Int) {
    val id: Int = getNextId()
    val lanes = mutableListOf<Lane>()

    override fun toString(): String = "Node($value)"


    infix fun to(node: Node): Lane =
        Lane(this, node).also { lanes.add(it); node.lanes.add(it) }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

    companion object {
        private var id = 0
        private fun getNextId(): Int = id++
    }
}