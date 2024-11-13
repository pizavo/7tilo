package hw5

fun main() {
	val startNode = Node(5).apply {
		lanes += 145 to Node(40).apply {
			lanes += 4 to Node(1)
			lanes += 30 to Node(3)
			lanes += 78 to Node(4).apply {
				lanes += 30 to Node(20)
				lanes += 57 to Node(19)
			}
		}
		lanes += 132 to Node(14).apply {
			lanes += 48 to Node(10)
			lanes += 21 to Node(15)
		}
		lanes += 193 to Node(1).apply {
			lanes += 12 to Node(5)
			lanes += 150 to Node(12).apply {
				lanes += 23 to Node(43)
				lanes += 27 to Node(36)
			}
		}
	}
	
	Traverse(485, startNode)
}