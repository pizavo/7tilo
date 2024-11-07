package hw2

import turing.*

typealias MD = MovementDirection

fun main() {
    val tapes = listOf(Tape("aabccaabcddacbad"), Tape())

    val states = listOf(
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
        State(12),
        State(13),
        State(14),
        State(15)
    )

    val transitions = listOf(
        states[0].multiTransition('a', '#', '#', 'a', MD.RIGHT, MD.RIGHT, states[1]),
        states[0].multiTransition('b', '#', '#', 'b', MD.RIGHT, MD.RIGHT, states[1]),
        states[0].multiTransition('c', '#', '#', 'c', MD.RIGHT, MD.RIGHT, states[1]),
        states[0].multiTransition('d', '#', '#', 'd', MD.RIGHT, MD.RIGHT, states[1]),
        states[1].multiTransition('a', '#', '#', '#', MD.RIGHT, MD.STAY, states[0]),
        states[1].multiTransition('c', '#', '#', '#', MD.RIGHT, MD.STAY, states[0]),
        states[1].multiTransition('b', '#', '#', 'b', MD.RIGHT, MD.RIGHT, states[0]),
        states[1].multiTransition('d', '#', '#', 'd', MD.RIGHT, MD.RIGHT, states[0]),
        states[0].multiTransition('#', '#', '#', '#', MD.LEFT, MD.LEFT, states[2]),
        states[1].multiTransition('#', '#', '#', '#', MD.LEFT, MD.LEFT, states[2]),

        states[2].multiTransition('#', 'a', '#', 'a', MD.LEFT, MD.LEFT, states[2]),
        states[2].multiTransition('#', 'b', '#', 'b', MD.LEFT, MD.LEFT, states[2]),
        states[2].multiTransition('#', 'c', '#', 'c', MD.LEFT, MD.LEFT, states[2]),
        states[2].multiTransition('#', 'd', '#', 'd', MD.LEFT, MD.LEFT, states[2]),
        states[2].multiTransition('#', '#', '#', '#', MD.RIGHT, MD.RIGHT, states[3]),

        states[3].multiTransition('#', 'a', '#', 'a', MD.STAY, MD.RIGHT, states[3]),
        states[3].multiTransition('#', 'b', '#', 'b', MD.STAY, MD.RIGHT, states[3]),
        states[3].multiTransition('#', 'c', '#', 'c', MD.STAY, MD.RIGHT, states[3]),
        states[3].multiTransition('#', 'd', 'd', 'd', MD.RIGHT, MD.RIGHT, states[3]),
        states[3].multiTransition('#', '#', '#', '#', MD.STAY, MD.LEFT, states[4]),

        states[4].multiTransition('#', 'a', '#', 'a', MD.STAY, MD.LEFT, states[4]),
        states[4].multiTransition('#', 'b', '#', 'b', MD.STAY, MD.LEFT, states[4]),
        states[4].multiTransition('#', 'c', 'c', 'c', MD.RIGHT, MD.LEFT, states[4]),
        states[4].multiTransition('#', 'd', '#', 'd', MD.STAY, MD.LEFT, states[4]),
        states[4].multiTransition('#', '#', '#', '#', MD.STAY, MD.RIGHT, states[5]),

        states[5].multiTransition('#', 'a', '#', 'a', MD.STAY, MD.RIGHT, states[5]),
        states[5].multiTransition('#', 'b', 'b', 'b', MD.RIGHT, MD.RIGHT, states[5]),
        states[5].multiTransition('#', 'c', '#', 'c', MD.STAY, MD.RIGHT, states[5]),
        states[5].multiTransition('#', 'd', '#', 'd', MD.STAY, MD.RIGHT, states[5]),
        states[5].multiTransition('#', '#', '#', '#', MD.STAY, MD.LEFT, states[6]),

        states[6].multiTransition('#', 'a', 'a', 'a', MD.RIGHT, MD.LEFT, states[6]),
        states[6].multiTransition('#', 'b', '#', 'b', MD.STAY, MD.LEFT, states[6]),
        states[6].multiTransition('#', 'c', '#', 'c', MD.STAY, MD.LEFT, states[6]),
        states[6].multiTransition('#', 'd', '#', 'd', MD.STAY, MD.LEFT, states[6]),
        states[6].multiTransition('#', '#', '#', '#', MD.LEFT, MD.RIGHT, states[7]),

        states[7].multiTransition('a', 'a', 'a', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('b', 'a', 'b', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('c', 'a', 'c', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('d', 'a', 'd', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('a', 'b', 'a', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('b', 'b', 'b', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('c', 'b', 'c', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('d', 'b', 'd', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('a', 'c', 'a', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('b', 'c', 'b', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('c', 'c', 'c', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('d', 'c', 'd', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('a', 'd', 'a', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('b', 'd', 'b', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('c', 'd', 'c', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('d', 'd', 'd', '#', MD.LEFT, MD.RIGHT, states[7]),
        states[7].multiTransition('#', '#', '#', '#', MD.RIGHT, MD.LEFT, states[8]),

        states[8].multiTransition('a', '#', 'a', '1', MD.RIGHT, MD.STAY, states[9]),
        states[8].multiTransition('b', '#', 'b', '1', MD.RIGHT, MD.STAY, states[9]),
        states[8].multiTransition('c', '#', 'c', '1', MD.RIGHT, MD.STAY, states[9]),
        states[8].multiTransition('d', '#', 'd', '1', MD.RIGHT, MD.STAY, states[9]),

        states[9].multiTransition('a', '1', 'a', '0', MD.STAY, MD.LEFT, states[9]),
        states[9].multiTransition('b', '1', 'b', '0', MD.STAY, MD.LEFT, states[9]),
        states[9].multiTransition('c', '1', 'c', '0', MD.STAY, MD.LEFT, states[9]),
        states[9].multiTransition('d', '1', 'd', '0', MD.STAY, MD.LEFT, states[9]),
        states[9].multiTransition('a', '#', 'a', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('b', '#', 'b', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('c', '#', 'c', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('d', '#', 'd', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('a', '0', 'a', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('b', '0', 'b', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('c', '0', 'c', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('d', '0', 'd', '1', MD.RIGHT, MD.RIGHT, states[10]),
        states[9].multiTransition('#', '1', '#', '1', MD.STAY, MD.STAY, states[11]),

        states[10].multiTransition('a', '1', 'a', '1', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('b', '1', 'b', '1', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('c', '1', 'c', '1', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('d', '1', 'd', '1', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('a', '0', 'a', '0', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('b', '0', 'b', '0', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('c', '0', 'c', '0', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('d', '0', 'd', '0', MD.STAY, MD.RIGHT, states[10]),
        states[10].multiTransition('a', '#', 'a', '#', MD.STAY, MD.LEFT, states[9]),
        states[10].multiTransition('b', '#', 'b', '#', MD.STAY, MD.LEFT, states[9]),
        states[10].multiTransition('c', '#', 'c', '#', MD.STAY, MD.LEFT, states[9]),
        states[10].multiTransition('d', '#', 'd', '#', MD.STAY, MD.LEFT, states[9]),
        states[10].multiTransition('#', '#', '#', '#', MD.STAY, MD.LEFT, states[11]),
        states[10].multiTransition('#', '0', '#', '0', MD.STAY, MD.STAY, states[11]),
        states[10].multiTransition('#', '1', '#', '1', MD.STAY, MD.STAY, states[11]),

        states[11].multiTransition('#', '0', '#', '0', MD.STAY, MD.LEFT, states[11]),
        states[11].multiTransition('#', '1', '#', '1', MD.STAY, MD.LEFT, states[11]),
        states[11].multiTransition('#', '#', '#', '#', MD.RIGHT, MD.RIGHT, states[12]),

        states[12].multiTransition('#', '0', '0', '#', MD.RIGHT, MD.RIGHT, states[12]),
        states[12].multiTransition('#', '1', '1', '#', MD.RIGHT, MD.RIGHT, states[12]),
        states[12].multiTransition('#', '#', '#', '#', MD.STAY, MD.STAY, states[13]),
    )

    val machine = MultiMachine(
        tapes,
        transitions,
        states[0],
        listOf(states[13]),
        listOf(tapes[0]),
    ).run {
        try {
            run()
        } catch (e: RuntimeException) {
            debug()
            throw e
        }

        this.print()
    }
}