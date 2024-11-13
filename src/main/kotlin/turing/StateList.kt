package turing

class StateList(private val states: List<State>) {
    operator fun get(index: Int) =
        states.find { it.id == index } ?: throw IllegalArgumentException("State q$index not found")
}