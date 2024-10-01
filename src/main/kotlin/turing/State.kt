package turing

data class State(val id: String) {
    val transitions: MutableList<Transition> = mutableListOf()
}
