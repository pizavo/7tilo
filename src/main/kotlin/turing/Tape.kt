package turing

import kotlin.math.floor

class Tape(tape: String) {
    var head: Int
        private set
    val symbols: MutableList<Char>
    val initialUsedSpaces: List<Int>
    
    init {
        val listSize = floor((UShort.MAX_VALUE.toInt() - tape.length) / 2.0).toInt()
        
        symbols = MutableList(UShort.MAX_VALUE.toInt()) { '#' }
        symbols.addAll(listSize, tape.toList())
        
        head = listSize
        initialUsedSpaces = symbols.indices.filter { symbols[it] != '#' }
    }
    
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
    
    override fun toString(): String {
        return symbols.joinToString("").trim('#')
    }
}
