import turing.Machine
import turing.MovementDirection
import turing.State
import turing.Tape
import turing.Transition

fun main() {
    val tape = Tape("#110+10#")

    val q0 = State("q0")
    val q1 = State("q1")
    val q2 = State("q2")
    val q3 = State("q3")
    val q4 = State("q4")
    val q5 = State("q5")
    val q6 = State("q6")
    val q7 = State("q7")
    val qF = State("qF")

    val transition1 = Transition('1', '1', MovementDirection.RIGHT, q0)
    val transition2 = Transition('0', '0', MovementDirection.RIGHT, q0)
    val transition3 = Transition('+', '+', MovementDirection.RIGHT, q0)
    val transition4 = Transition('#', '#', MovementDirection.LEFT, q1)

    q0.transitions.apply {
        add(transition1)
        add(transition2)
        add(transition3)
        add(transition4)
    }

    val transition5 = Transition('0', '1', MovementDirection.LEFT, q2)
    val transition6 = Transition('1', '0', MovementDirection.STAY, q3)
    val transition7 = Transition('1', '1', MovementDirection.LEFT, q3)
    val transition8 = Transition('0', '0', MovementDirection.LEFT, q3)
    val transition9 = Transition('+', '+', MovementDirection.LEFT, q5)

    val transition10 = Transition('1', '0', MovementDirection.STAY, q4)
    val transition11 = Transition('1', '1', MovementDirection.LEFT, q4)
    val transition12 = Transition('0', '0', MovementDirection.LEFT, q4)

    val transition13 = Transition('1', '0', MovementDirection.LEFT, q5)
    val transition14 = Transition('0', '1', MovementDirection.STAY, q6)
    val transition15 = Transition('#', '1', MovementDirection.STAY, q6)

    val transition16 = Transition('1', '1', MovementDirection.STAY, q0)
    val transition17 = Transition('0', '0', MovementDirection.STAY, q0)
    val transition18 = Transition('+', '+', MovementDirection.STAY, q0)
    val transition19 = Transition('#', '#', MovementDirection.STAY, q0)

    val transition20 = Transition('+', '#', MovementDirection.RIGHT, q7)

    val transition21 = Transition('1', '#', MovementDirection.RIGHT, q7)
    val transition22 = Transition('0', '#', MovementDirection.RIGHT, q7)
    val transition23 = Transition('+', '#', MovementDirection.RIGHT, q7)

    val transition24 = Transition('#', '#', MovementDirection.STAY, qF)

    q1.transitions.apply {
        add(transition5)
        add(transition10)
    }

    q2.transitions.apply {
        add(transition5)
        add(transition6)
        add(transition20)
    }

    q3.transitions.apply {
        add(transition7)
        add(transition8)
        add(transition9)
    }

    q4.transitions.apply {
        add(transition11)
        add(transition12)
        add(transition9)
    }

    q5.transitions.apply {
        add(transition13)
        add(transition14)
        add(transition15)
    }

    q6.transitions.apply {
        add(transition16)
        add(transition17)
        add(transition18)
        add(transition19)
    }

    q7.transitions.apply {
        add(transition21)
        add(transition22)
        add(transition23)
        add(transition24)
    }

    val machine = Machine(tape, q0, mutableListOf(qF))

    machine.run()
}
