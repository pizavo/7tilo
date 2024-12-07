package ram

class Tape(string: String) {
	private val cells: MutableList<Int?> = string.takeIf(String::isNotEmpty)
		?.plus("|#")?.split('|')?.map { if (it == "#") null else it.toInt() }?.toMutableList()
		?: mutableListOf()
	private var pointer = 0

	constructor() : this("")

	fun write(value: Int?) {
		if (pointer >= cells.size) cells.add(value)
		else cells[pointer++] = value
	}

	fun read(): Int? = cells[pointer++]

	override fun toString(): String = cells.joinToString("|")
}