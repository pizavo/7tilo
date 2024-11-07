package hw1

import turing.Machine
import turing.MovementDirection
import turing.State
import turing.Tape
import turing.Transition

fun main() {
    val tape = Tape("100+10")

    val states = mutableListOf<State>(
        State(1),
        State(2),
        State(3),
        State(4),
        State(5),
        State(6),
        State(7),
        State(8),
        State(9),
    )

    val transitions = listOf<Transition>(
        states[0].transition('0', '0', MovementDirection.RIGHT, states[0]),
        states[0].transition('1', '1', MovementDirection.RIGHT, states[0]),
        states[0].transition('+', '+', MovementDirection.RIGHT, states[0]),
        states[0].transition('#', '#', MovementDirection.LEFT, states[1]),

        states[1].transition('0', '1', MovementDirection.LEFT, states[2]),
        states[1].transition('1', '0', MovementDirection.STAY, states[4]),

        states[2].transition('0', '1', MovementDirection.LEFT, states[2]),
        states[2].transition('1', '0', MovementDirection.STAY, states[3]),
        states[2].transition('+', '#', MovementDirection.RIGHT, states[7]),

        states[3].transition('0', '0', MovementDirection.LEFT, states[3]),
        states[3].transition('1', '1', MovementDirection.LEFT, states[3]),
        states[3].transition('+', '+', MovementDirection.LEFT, states[5]),

        states[4].transition('0', '0', MovementDirection.LEFT, states[4]),
        states[4].transition('1', '1', MovementDirection.LEFT, states[4]),
        states[4].transition('+', '+', MovementDirection.LEFT, states[5]),

        states[5].transition('0', '1', MovementDirection.STAY, states[6]),
        states[5].transition('1', '0', MovementDirection.LEFT, states[5]),
        states[5].transition('#', '1', MovementDirection.STAY, states[6]),

        states[6].transition('0', '0', MovementDirection.STAY, states[6]),
        states[6].transition('1', '1', MovementDirection.STAY, states[6]),
        states[6].transition('+', '+', MovementDirection.STAY, states[6]),
        states[6].transition('#', '#', MovementDirection.STAY, states[0]),

        states[7].transition('0', '#', MovementDirection.RIGHT, states[7]),
        states[7].transition('1', '#', MovementDirection.RIGHT, states[7]),
        states[7].transition('+', '#', MovementDirection.RIGHT, states[7]),
        states[7].transition('#', '#', MovementDirection.STAY, states[8]),
    )

    Machine(tape, listOf('0', '1', '+', '#'), transitions, states[0], listOf(states[8])).run {
        run()
        print()
    }
}
