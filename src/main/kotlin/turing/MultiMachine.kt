package turing

import extensions.allIndexed

class MultiMachine(
    val tapes: List<Tape>,
    val transitions: List<StateMultiTransition>,
    var state: State,
    val endStates: List<State>,
    val finishTapes: List<Tape>,
) {
    var timeComplexity = 0
        private set
    val writtenToCells: Map<Int, MutableSet<Int>> =
        mutableMapOf<Int, MutableSet<Int>>().also {
            tapes.forEachIndexed { index, tape -> it[index] = tape.initialUsedSpaces.toMutableSet() }
        }
    val spaceComplexity: Int
        get() = writtenToCells.map { it.value.size }.sum()
    
    fun run() {
        while (state !in endStates) {
            executeTransition(findTransition())
        }
    }
    
    private fun findTransition() =
        transitions.filter { it.state == state }.find { multiTransition ->
            multiTransition.transitions.allIndexed { index, transition ->
                tapes[index].read() == transition.readSymbol
            }
        } ?: throw TransitionNotFoundException("Transition not found for state $state")
    
    private fun executeTransition(transition: StateMultiTransition) {
        timeComplexity++
        
        transition.transitions.forEachIndexed { index, multiTransition ->
            tapes[index].run {
                if (multiTransition.writeSymbol != read()) writtenToCells[index]!!.add(head)
                
                write(multiTransition.writeSymbol)
                move(multiTransition.direction)
            }
        }
        
        state = transition.nextState
    }
    
    fun print(withDebug: Boolean = false) =
        finishTapes.forEachIndexed { index, tape -> tape.print(index) }
            .also {
                println("Time complexity: $timeComplexity")
                println("Space complexity: $spaceComplexity")
            }
            .also { if (withDebug) debug() }
    
    fun debug() =
        tapes.forEachIndexed { index, tape -> tape.debug(index) }
}