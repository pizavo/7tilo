package turing

import extensions.append
import extensions.prepend
import kotlin.math.floor

class Tape(tape: String = "") {
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

    fun print(index: Int = 0) =
        println("Tape $index: $this")

    fun debug(index: Int = 0) {
        var start = 0
        var end = 0
        var tape = ""

        for (i in symbols.indices) {
            if (symbols[i] == '#' && start == 0) {
                continue
            } else if (symbols[i] != '#' && start == 0) {
                start = i
            }

            tape += symbols[i]
        }

        tape = tape.trim('#')
        end = start + tape.length - 1

        tape = if (start > head) {
            start = head
            tape.prepend('#', start - head)
        } else if (end < head) {
            end = head
            tape.append('#', head - end)
        } else tape

        val headTmp = head - start

        println("Tape $index:")
        println(tape)
        println(" ".repeat(headTmp) + "^")
    }
}
