package hw5

class Traversal(private var budget: Int, private val startNode: Node, private val debug: Boolean = false) {
    private var step = 0
    private var gathered: Int = 0
    private var results = mutableListOf<PossiblePath>()

    private fun traverse(taken: Lane, node: Node, currentCost: Int, currentGain: Int, currentPath: List<Node>) {
        val newGain = currentGain + node.value
        val newPath = currentPath + node

        results.add(PossiblePath(currentCost, newGain, newPath))

        for (lane in node.lanes.filter { it != taken }) {
            val newCost = currentCost + lane.price

            traverse(lane, lane.first.takeIf { node !== it } ?: lane.second, newCost, newGain, newPath)
        }
    }

    fun run() {
        println("r=$budget, z=$gathered")
        println("Starting traversal\n")

        var node = startNode

        gathered += node.value

        printInfo(null, node)

        node.value = 0

        while (true) {
            node.lanes.forEach { lane ->
                traverse(lane, lane.getOtherNode(node), lane.price.value, 0, emptyList())
            }

            if (debug) {
                for (result in results) {
                    println("Cost: ${result.cost}, Gain: ${result.gain}, Path: ${result.path.joinToString(" -> ")}")
                }

                println("Paths mapped\n")
            }

            getBestPath()?.let {
                if (debug) println("Best path: ${it.path.joinToString(" -> ")}, Cost: ${it.cost}, Gain: ${it.gain}")
                node = gather(node, it.path)
                if (debug) println()
            } ?: break

            results.clear()
        }

        println("\nNo more profitable paths available")
        println("r=$budget, z=$gathered")
    }


    private fun getBestPath(): PossiblePath? {
        return results.filter { it.gain != 0 && it.cost - budget < it.gain }
            .maxByOrNull { it.gain }
            ?.takeIf { it.gain + (budget - it.cost) > 0 }
    }

    private fun gather(current: Node, path: List<Node>): Node {
        return current.lanes.find { it.getOtherNode(current) == path.firstOrNull() }?.let {
            val other = it.getOtherNode(current)

            gathered += other.value

            if (budget < it.price) {
                it.price -= budget
                budget = 0

                gathered -= it.price
            } else {
                budget -= it.price
            }

            printInfo(it, other)

            other.value = 0
            it.setPrice(0)

            gather(other, path.drop(1))
        } ?: current
    }

    private fun printInfo(lane: Lane?, node: Node) =
        "%-5s %-9s %-7s %-9s %-5s -> r=%-6s z=%d"
            .format(
                "[${step++}]",
                "Lane<${lane?.id ?: 'X'}>",
                "(${lane?.price?.value ?: 'X'}),",
                "Node<${node.id}>",
                "(${node.value})",
                "$budget,",
                gathered
            )
            .also { println(it) }
}
