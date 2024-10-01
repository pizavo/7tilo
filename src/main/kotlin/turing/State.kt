package turing

class State(val id: String) {
    constructor(id: Short) : this(id.toString())
    
    fun transition(readSymbol: Char, writeSymbol: Char, direction: MovementDirection, nextState: State) =
        Transition(this, readSymbol, writeSymbol, direction, nextState)
    
    override fun toString(): String {
        return "q$id"
    }
}