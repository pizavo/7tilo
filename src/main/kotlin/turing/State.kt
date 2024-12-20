package turing

import kotlin.math.ceil
import kotlin.math.floor

class State(val id: Int) {
	init {
		require(id >= 0) { "State ID must be non-negative" }
	}

	fun transition(readSymbol: Char, writeSymbol: Char, direction: MovementDirection, nextState: State) =
		Transition(this, readSymbol, writeSymbol, direction, nextState)

	fun transition(readSymbol: Byte, writeSymbol: Byte, direction: MovementDirection, nextState: State) =
		require(readSymbol in 0..1  && writeSymbol in 0..1) { "Symbol must be 0 or 1" }
			.let { Transition(this, readSymbol.toInt().digitToChar(), writeSymbol.toInt().digitToChar(), direction, nextState) }

	fun stateMultiTransition(nextState: State, vararg transitions: MultiTransition) =
		stateMultiTransition(nextState, transitions.toList())

	private fun stateMultiTransition(nextState: State, transitions: List<MultiTransition>) =
		StateMultiTransition(this, transitions, nextState)

	fun multiTransition(vararg args: Any): StateMultiTransition {
		val transitionCount = ((args.size - 1) / 3.0).takeIf { ceil(it) == floor(it) }?.toInt()
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
