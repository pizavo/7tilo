package turing

data class Transition(
    val state: State,
    val readSymbol: Char,
    val writeSymbol: Char,
    val direction: MovementDirection,
    val nextState: State
) {
    fun encode(symbols: List<Char>): String {
        return "0".repeat(state.id) + "1" +
                "0".repeat(symbols.indexOf(readSymbol) + 1) + "1" +
                "0".repeat(nextState.id) + "1" +
                "0".repeat(symbols.indexOf(writeSymbol) + 1) + "1" +
                "0".repeat(direction.ordinal + 1)
    }
    
    companion object {
        fun decode(encoded: String, symbols: List<Char>, states: MutableList<State>): Transition {
            val parts = encoded.split("1")
            
            val encodedState = parts[0].count { it == '0' }
            val encodedNextState = parts[2].count { it == '0' }
            
            val state = states.find { it.id == encodedState } ?: State(encodedState).also { states.add(it) }
            val readSymbol = symbols[parts[1].count { it == '0' } - 1]
            val nextState = states.find { it.id == encodedNextState } ?: State(encodedNextState).also { states.add(it) }
            val writeSymbol = symbols[parts[3].count { it == '0' } - 1]
            val direction = MovementDirection.entries[parts[4].count { it == '0' } - 1]
            
            return Transition(state, readSymbol, writeSymbol, direction, nextState)
        }
    }
}
