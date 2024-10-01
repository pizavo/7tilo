package turing

class Machine(
    val tape: Tape,
    val transitions: List<Transition>,
    var state: State,
    val endStates: List<State>,
    val wildcard: List<Char> = listOf()
) {
    var timeComplexity = 0
        private set
    val places = mutableSetOf<Int>()
    val spaceComplexity: Int
        get() = places.size
    
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
}
