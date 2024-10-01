package hw1

import turing.Machine
import turing.MovementDirection
import turing.State
import turing.Tape
import turing.Transition

fun main() {
    val tape = Tape("100+10")
    
    val q0 = State(0)
    val q1 = State(1)
    val q2 = State(2)
    val q3 = State(3)
    val q4 = State(4)
    val q5 = State(5)
    val q6 = State(6)
    val q7 = State(7)
    val qF = State("F")
    
    val transitions = listOf<Transition>(
        q0.transition('*', '*', MovementDirection.RIGHT, q0),
        /*q0.transition('0', '0', MovementDirection.RIGHT, q0),
        q0.transition('1', '1', MovementDirection.RIGHT, q0),
        q0.transition('+', '+', MovementDirection.RIGHT, q0),*/
        q0.transition('#', '#', MovementDirection.LEFT, q1),
        
        q1.transition('0', '1', MovementDirection.LEFT, q2),
        q1.transition('1', '0', MovementDirection.STAY, q4),
        
        q2.transition('0', '1', MovementDirection.LEFT, q2),
        q2.transition('1', '0', MovementDirection.STAY, q3),
        q2.transition('+', '#', MovementDirection.RIGHT, q7),
        
        q3.transition('0', '0', MovementDirection.LEFT, q3),
        q3.transition('1', '1', MovementDirection.LEFT, q3),
        q3.transition('+', '+', MovementDirection.LEFT, q5),
        
        q4.transition('0', '0', MovementDirection.LEFT, q4),
        q4.transition('1', '1', MovementDirection.LEFT, q4),
        q4.transition('+', '+', MovementDirection.LEFT, q5),
        
        q5.transition('0', '1', MovementDirection.STAY, q6),
        q5.transition('1', '0', MovementDirection.LEFT, q5),
        q5.transition('#', '1', MovementDirection.STAY, q6),
        
        q6.transition('*', '*', MovementDirection.STAY, q0),
        /*q6.transition('0', '0', MovementDirection.STAY, q0),
        q6.transition('1', '1', MovementDirection.STAY, q0),
        q6.transition('+', '+', MovementDirection.STAY, q0),*/
        q6.transition('#', '#', MovementDirection.STAY, q0),
        
        q7.transition('*', '#', MovementDirection.RIGHT, q7),
        /*q7.transition('0', '#', MovementDirection.RIGHT, q7),
        q7.transition('1', '#', MovementDirection.RIGHT, q7),
        q7.transition('+', '#', MovementDirection.RIGHT, q7),*/
        q7.transition('#', '#', MovementDirection.STAY, qF),
    )
    
    Machine(tape, transitions, q0, listOf(qF), listOf('0', '1', '+')).run {
        run()
        print()
    }
}
