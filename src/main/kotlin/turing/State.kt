package turing

import extensions.toTypedList
import kotlin.math.ceil
import kotlin.math.floor

class State(private val id: String) {
    constructor(id: Short) : this(id.toString())
    
    fun transition(readSymbol: Char, writeSymbol: Char, direction: MovementDirection, nextState: State) =
        Transition(this, readSymbol, writeSymbol, direction, nextState)
    
    fun stateMultiTransition(nextState: State, vararg transitions: MultiTransition) =
        stateMultiTransition(nextState, transitions.toList())
    
    private fun stateMultiTransition(nextState: State, transitions: List<MultiTransition>) =
        StateMultiTransition(this, transitions, nextState)
    
    fun multiTransition(vararg args: Any): StateMultiTransition {
        val transitionCount = ((args.size - 1) / 3.0).takeIf { ceil(it) == floor(it) }?.let { it.toInt() }
            ?: throw IllegalArgumentException("Invalid number of arguments")
        val nextState = args.last() as? State
            ?: throw IllegalArgumentException("Last argument must be a State")
        val multiTransitions = mutableListOf<MultiTransition>()
        
        for (i in 0 until transitionCount) {
            val readSymbol = args[i] as Char
            val writeSymbol = args[i + transitionCount] as Char
            val direction = args[i + (2 * transitionCount)] as MovementDirection
            
            multiTransitions.add(MultiTransition(readSymbol, writeSymbol, direction))
        }
        
        return stateMultiTransition(nextState, multiTransitions)
    }
    
    override fun toString(): String {
        return "q$id"
    }
}
