package hw5

fun main() {
    val debug = false

    val startNode = Node(5).apply {
        (this to Node(40) priced 145).apply {
            this to Node(1) priced 4
            this to Node(3) priced 30
            (this to Node(4) priced 78).apply {
                this to Node(20) priced 30
                this to Node(19) priced 57
            }
        }
        (this to Node(14) priced 132).apply {
            this to Node(10) priced 48
            this to Node(15) priced 21
        }
        (this to Node(1) priced 193).apply {
            this to Node(5) priced 12
            (this to Node(12) priced 150).apply {
                this to Node(43) priced 23
                this to Node(36) priced 27
            }
        }
    }

    Traversal(485, startNode, debug).run()
}