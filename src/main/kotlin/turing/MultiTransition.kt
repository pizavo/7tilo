package turing

class MultiTransition(
	val tape: Tape,
	val readSymbol: Char,
	val writeSymbol: Char,
	val direction: MovementDirection,
)

class StateMultiTransition(
	val state: State,
	val transitions: List<MultiTransition>,
	val nextState: State
)
