package hw6

import java.util.*

fun kruskal(graph: Collection<Edge>, nodes: Collection<Node>): List<Edge> {
	val sortedEdges = graph.sortedBy { it.weight }
	val disjointSet = nodes.associateWith { it }.toMutableMap()
	val rank = nodes.associateWith { 0 }.toMutableMap()
	val mst = mutableListOf<Edge>()

	fun find(node: Node): Node {
		if (disjointSet[node] == node) return node

		return find(disjointSet[node]!!).also { disjointSet[node] = it }
	}

	fun union(node1: Node, node2: Node) {
		val root1 = find(node1)
		val root2 = find(node2)

		if (root1 != root2) {
			val rank1 = rank[root1]!!
			val rank2 = rank[root2]!!

			when {
				rank1 < rank2 -> disjointSet[root1] = root2
				rank1 > rank2 -> disjointSet[root2] = root1
				else -> {
					disjointSet[root2] = root1
					rank[root1] = rank1 + 1
				}
			}
		}
	}

	for (edge in sortedEdges) {
		if (mst.size >= nodes.size - 1) break

		if (find(edge.first) != find(edge.second)) {
			union(edge.first, edge.second)
			mst.add(edge)
		}
	}

	return mst
}

fun jarnik(nodes: Collection<Node>): List<Edge> {
	val node = nodes.random()
	val mst = mutableListOf<Edge>()
	val visitedNodes = mutableSetOf<Node>()
	val edgeQueue = PriorityQueue<Edge>(compareBy { it.weight })

	fun Node.visit() {
		if (visitedNodes.add(this)) {
			edgeQueue.addAll(this.edges.filter { (it.first !in visitedNodes) || (it.second !in visitedNodes) })
		}
	}

	node.visit()

	while (edgeQueue.isNotEmpty()) {
		val edge = edgeQueue.poll()
		val unvisitedNode = edge.first.takeUnless { it in visitedNodes } ?: edge.second

		if (unvisitedNode !in visitedNodes) {
			mst.add(edge)
			unvisitedNode.visit()
		}
	}

	return mst
}

fun boruvka(nodes: Collection<Node>): List<Edge> {
	val disjointSet = nodes.associateWith { it }.toMutableMap()
	val mst = mutableListOf<Edge>()

	fun find(node: Node): Node {
		if (disjointSet[node] == node) return node
		return find(disjointSet[node]!!).also { disjointSet[node] = it }
	}

	fun union(node1: Node, node2: Node) {
		val root1 = find(node1)
		val root2 = find(node2)

		if (root1 != root2) {
			disjointSet[root2] = root1
		}
	}

	while (mst.size < nodes.size - 1) {
		val smallestEdgeForComponent = mutableMapOf<Node, Edge>()

		for (node in nodes) {
			val root = find(node)
			node.edges.filter { find(it.first) != find(it.second) }
				.minByOrNull { it.weight }
				?.let { edge ->
					val currentSmallestEdge = smallestEdgeForComponent[root]
					if (currentSmallestEdge == null || edge.weight < currentSmallestEdge.weight) {
						smallestEdgeForComponent[root] = edge
					}
				}
		}

		for ((_, edge) in smallestEdgeForComponent) {
			if (find(edge.first) != find(edge.second)) {
				mst.add(edge)
				union(edge.first, edge.second)
			}
		}
	}

	return mst
}
