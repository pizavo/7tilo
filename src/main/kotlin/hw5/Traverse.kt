package hw5

class Traverse(private var budget: Int, private var node: Node) {
	private var sources: Int = 0
	
	fun traverse(lane: Lane) {
		if (budget + sources < lane.price) {
			throw IllegalAccessException("Not enough budget")
		}
		
		println(lane)
		
		budget.apply { minus(lane.price) }.takeIf { it < 0 }?.apply {
			sources += this
			minus(this)
		}
		
		lane.price = 0
		
		lane.node.apply {
			sources += value
			value = 0
		}
		
		println("Current budget: $budget")
		println("Current sources: $sources")
	}
}