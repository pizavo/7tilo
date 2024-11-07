package hw4

import turing.Machine
import turing.State
import turing.Tape
import turing.MovementDirection as MD

fun main() {
    val tape = Tape("111#10")

    val states = mutableListOf(
        State(1),
        State(2),
        State(3),
        State(4),
        State(5),
        State(6),
        State(7),
        State(8),
        State(9),
        State(10),
        State(11),
    )

    val transitions = mutableListOf(
        states[0].transition(1, 1, MD.RIGHT, states[0]),
        states[0].transition(0, 0, MD.RIGHT, states[0]),
        states[0].transition('#', '#', MD.LEFT, states[1]),

        states[1].transition(0, 0, MD.LEFT, states[1]),
        states[1].transition(1, 0, MD.RIGHT, states[2]),

        states[2].transition(0, 1, MD.RIGHT, states[2]),
        states[2].transition('#', '#', MD.RIGHT, states[3]),

        states[3].transition(0, 0, MD.RIGHT, states[3]),
        states[3].transition(1, 1, MD.RIGHT, states[3]),
        states[3].transition('#', '#', MD.LEFT, states[4]),

        states[4].transition(0, 0, MD.LEFT, states[4]),
        states[4].transition('#', '#', MD.STAY, states[9]),
        states[4].transition(1, 0, MD.RIGHT, states[5]),

        states[5].transition(0, 1, MD.RIGHT, states[5]),
        states[5].transition('#', '#', MD.LEFT, states[6]),

        states[6].transition(0, 0, MD.LEFT, states[6]),
        states[6].transition(1, 1, MD.LEFT, states[6]),
        states[6].transition('#', '#', MD.RIGHT, states[7]),

        states[7].transition(0, 0, MD.LEFT, states[7]),
        states[7].transition(1, 0, MD.RIGHT, states[8]),
        states[7].transition('#', '#', MD.STAY, states[10]),

        states[8].transition('#', '#', MD.RIGHT, states[2]),
    )

    Machine(tape, listOf('0', '1', '#'), transitions, states[0], listOf(states[9], states[10])).run {
        run()
        println(state)
        tape.debug()
    }
}