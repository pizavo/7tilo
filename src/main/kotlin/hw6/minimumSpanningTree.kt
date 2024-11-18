package hw6

fun kruskal(graph: List<Edge>, nodes: List<Node>): List<Edge> {
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
