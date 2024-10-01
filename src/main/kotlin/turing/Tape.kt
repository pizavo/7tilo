package turing

class Tape(tape: String) {
    var head: Int = 1
    val symbols: MutableList<Char> = tape.toMutableList()
	
    fun read(): Char {
        return symbols[head]
    }
	
    fun write(symbol: Char) {
        symbols[head] = symbol
    }
	
    fun move(direction: MovementDirection) {
        when (direction) {
            MovementDirection.LEFT -> head--
            MovementDirection.RIGHT -> head++
            else -> return
        }
    }
}
