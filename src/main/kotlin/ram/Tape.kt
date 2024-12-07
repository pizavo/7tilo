package ram

class Tape(string: String) {
	private val cells: MutableList<Int?> = string.takeIf(String::isNotEmpty)
		?.plus("|#")?.split('|')?.map { if (it == "#") null else it.toInt() }?.toMutableList()
		?: mutableListOf()
	private var pointer = 0

	constructor() : this("")

	fun write(value: Int?) {
		cells.add(value)
	}

	fun read(): Int? = cells[pointer++]

	override fun toString(): String =
		if (cells.isEmpty()) "#"
		else cells.map { it ?: '#' }.joinToString("|")
}