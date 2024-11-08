package hw4

import turing.Machine
import turing.State
import turing.StateList
import turing.Tape
import turing.MovementDirection as MD

fun main() {
    val tape = Tape("0#0")
    val states = StateList((1..17).map { State(it) })

    val transitions = mutableListOf(
        states[1].transition(1, 1, MD.RIGHT, states[1]),
        states[1].transition(0, 0, MD.RIGHT, states[1]),
        states[1].transition('#', '#', MD.LEFT, states[2]),

        states[2].transition(0, 0, MD.LEFT, states[2]),
        states[2].transition(1, 0, MD.RIGHT, states[3]),
        states[2].transition('#', '#', MD.RIGHT, states[13]),

        states[3].transition(0, 1, MD.RIGHT, states[3]),
        states[3].transition('#', '#', MD.RIGHT, states[4]),

        states[4].transition(0, 0, MD.RIGHT, states[4]),
        states[4].transition(1, 1, MD.RIGHT, states[4]),
        states[4].transition('#', '#', MD.LEFT, states[5]),

        states[5].transition(0, 0, MD.LEFT, states[5]),
        states[5].transition('#', '#', MD.RIGHT, states[10]),
        states[5].transition(1, 0, MD.RIGHT, states[6]),

        states[6].transition(0, 1, MD.RIGHT, states[6]),
        states[6].transition('#', '#', MD.LEFT, states[7]),

        states[7].transition(0, 0, MD.LEFT, states[7]),
        states[7].transition(1, 1, MD.LEFT, states[7]),
        states[7].transition('#', '#', MD.LEFT, states[8]),

        states[8].transition(0, 0, MD.LEFT, states[8]),
        states[8].transition(1, 0, MD.RIGHT, states[9]),
        states[8].transition('#', '#', MD.RIGHT, states[13]),

        states[9].transition(0, 1, MD.RIGHT, states[9]),
        states[9].transition('#', '#', MD.RIGHT, states[4]),

        states[10].transition(0, 0, MD.RIGHT, states[10]),
        states[10].transition(1, 1, MD.RIGHT, states[10]),
        states[10].transition('#', '#', MD.LEFT, states[11]),

        states[11].transition('0', '#', MD.LEFT, states[11]),
        states[11].transition('1', '#', MD.LEFT, states[11]),
        states[11].transition('#', '#', MD.LEFT, states[12]),

        states[12].transition('0', '#', MD.LEFT, states[12]),
        states[12].transition('1', '#', MD.LEFT, states[12]),
        states[12].transition('#', '1', MD.STAY, states[17]),

        states[13].transition(0, 0, MD.RIGHT, states[13]),
        states[13].transition(1, 1, MD.RIGHT, states[13]),
        states[13].transition('#', '#', MD.RIGHT, states[14]),

        states[14].transition('0', '#', MD.RIGHT, states[14]),
        states[14].transition('1', '#', MD.RIGHT, states[14]),
        states[14].transition('#', '#', MD.LEFT, states[15]),

        states[15].transition('#', '#', MD.LEFT, states[15]),
        states[15].transition('0', '#', MD.LEFT, states[16]),
        states[15].transition('1', '#', MD.LEFT, states[16]),

        states[16].transition('0', '#', MD.LEFT, states[16]),
        states[16].transition('1', '#', MD.LEFT, states[16]),
        states[16].transition('#', '0', MD.STAY, states[17]),
    )

    val encoded = Machine(tape.copy(), listOf('0', '1', '#'), transitions, states[1], listOf(states[17])).apply {
        run()
        print()
    }.encode()

    println("\nEncoded:")
    println(encoded + "\n")

    Machine.decode(tape.copy(), encoded).run {
        run()
        print()
    }
}