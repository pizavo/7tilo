package turing

class Machine(val tape: Tape, var state: State, val endStates: MutableList<State>) {
    var timeComplexity = 0

    fun run() {
        findTransition()?.let {
            if (endStates.contains(it.nextState)) {
                println(tape.symbols.joinToString(""))
                println("Time complexity: $timeComplexity")
                println("Space complexity: ${tape.symbols.size}")
                return
            }

            tape.write(it.writeSymbol)
            tape.move(it.direction)
            state = it.nextState
            timeComplexity++
            run()
        }
    }

    private fun findTransition(): Transition? =
        state.transitions.find { it.readSymbol == tape.read() }
}
