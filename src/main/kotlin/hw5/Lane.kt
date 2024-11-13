package hw5

class Lane(var price: Int, val node: Node) {
	override fun toString(): String =
		"Taking lane to ${node.value} for $price"
}

infix fun Int.to(node: Node) = Lane(this, node)