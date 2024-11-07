package turing

class Machine(
    val tape: Tape,
    val symbols: List<Char>,
    val transitions: List<Transition>,
    val startState: State,
    val endStates: List<State>,
    val wildcard: List<Char> = listOf()
) {
    var state: State
        private set
    var timeComplexity = 0
        private set
    val places = mutableSetOf<Int>()
    val spaceComplexity: Int
        get() = places.size

    init {
        state = startState
    }

    fun run() {
        places.addAll(tape.initialUsedSpaces)

        while (state !in endStates) {
            findTransition().let {
                handleComplexity(it)
                executeTransition(it)

                state = it.nextState
            }
        }
    }

    private fun findTransition(): Transition =
        tape.read().let { symbol ->
            transitions.find {
                it.state == state && (it.readSymbol == symbol || (it.readSymbol == '*' && symbol in wildcard))
            }
        } ?: throw TransitionNotFoundException("Transition not found for state $state and symbol '${tape.read()}'")

    private fun handleComplexity(transition: Transition) {
        timeComplexity++

        if (transition.writeSymbol != tape.read()) {
            places.add(tape.head)
        }
    }

    private fun executeTransition(transition: Transition) {
        if (transition.writeSymbol == '*' && wildcard.isNotEmpty()) {
            tape.read().let { symbol ->
                if (symbol in wildcard) {
                    tape.write(symbol)
                } else throw WildcardException("Symbol '$symbol' not included in wildcard for state $state")
            }
        } else {
            tape.write(transition.writeSymbol)
        }

        tape.move(transition.direction)
    }

    fun print() {
        println("Result: $tape")
        println("Time complexity: $timeComplexity")
        println("Space complexity: $spaceComplexity")
    }

    fun printConfig() {
        println("Start state: $startState")
        println("End states: $endStates")
        println("Transitions:")
        transitions.forEach {
            println("  $it")
        }
    }

    fun encode(): String =
        "0".repeat(startState.id) + "11" + endStates.joinToString("1") { "0".repeat(it.id) } +
                "111" + transitions.joinToString("11") { it.encode(symbols) } + "111"

    companion object {
        fun decode(tape: Tape, encoded: String, symbols: List<Char>): Machine {
            val startState: State
            val states = mutableListOf<State>()
            val endStates = mutableListOf<State>()
            val transitions: List<Transition>

            encoded.split("111").let { parts ->
                startState = decodeMetadata(parts[0], states, endStates)
                transitions = parts[1].split("11").map { Transition.decode(it, symbols, states) }
            }

            return Machine(tape, symbols, transitions, startState, endStates)
        }

        private fun decodeMetadata(encoded: String, states: MutableList<State>, endStates: MutableList<State>): State =
            encoded.split("11").let { metadata ->
                metadata[1].split("1").forEach { encodedEndState ->
                    val id = encodedEndState.count { it == '0' }
                    endStates += states.find { state -> state.id == id }
                        ?: State(id).also { state -> states.add(state) }
                }

                metadata[0].count { it == '0' }.let { startStateId: Int ->
                    states.find { it.id == startStateId } ?: State(startStateId).also { states.add(it) }
                }
            }
    }
}
