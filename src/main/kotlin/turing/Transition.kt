package turing

data class Transition(
    val state: State,
    val readSymbol: Char,
    val writeSymbol: Char,
    val direction: MovementDirection,
    val nextState: State
)
