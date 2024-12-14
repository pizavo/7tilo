package hw9

@JvmRecord
data class Path(val move: Int, val previous: Step? = null)

@JvmRecord
data class Step(val number: Int, val options: MutableList<Path> = mutableListOf())