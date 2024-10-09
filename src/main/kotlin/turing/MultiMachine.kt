package turing

class MultiMachine(
	val tapes: List<Tape>,
	val transitions: List<StateMultiTransition>,
	var state: State,
	val endStates: List<State>,
	val finishTape: Tape,
) {
	var timeComplexity = 0
		private set
	val places = mutableSetOf<Int>()
	val spaceComplexity: Int
		get() = places.size
	
	fun run() {
		while (state !in endStates) {
			executeTransition(findTransition())
		}
	}
	
	private fun findTransition() =
		transitions.filter { it.state == state }.find { multiTransition ->
			multiTransition.transitions.all { transition ->
				tapes.find { it == transition.tape }?.read() == transition.readSymbol
			}
		} ?: throw TransitionNotFoundException("Transition not found for state $state and tape1: ${tapes[0]}, tape2: ${tapes[1]}")
	
	private fun executeTransition(transition: StateMultiTransition) {
		transition.transitions.forEach { multiTransition ->
			tapes.find { it == multiTransition.tape }!!.run {
				write(multiTransition.writeSymbol)
				move(multiTransition.direction)
			}
		}
		
		state = transition.nextState
	}
	
	fun print() {
		tapes.forEachIndexed { index, tape ->
			println("Tape ${index + 1}: $tape")
		}
	}
}