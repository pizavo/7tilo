package hw2

import turing.MovementDirection
import turing.MultiMachine
import turing.MultiTransition
import turing.State
import turing.Tape
import kotlin.run

fun main() {
	val tape1 = Tape("aabccaabcddacbad");
	val tape2 = Tape("")
	
	val tapes = listOf(
		tape1, tape2
	)
	
	val states = listOf(
		State(0),
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
	)
	val qF = State("qF")
	
	val transitions = listOf(
		states[0].stateMultiTransition(
			states[1],
			MultiTransition(tape1, 'a', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'a', MovementDirection.RIGHT),
		),
		states[0].stateMultiTransition(
			states[1],
			MultiTransition(tape1, 'b', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'b', MovementDirection.RIGHT),
		),
		states[0].stateMultiTransition(
			states[1],
			MultiTransition(tape1, 'c', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'c', MovementDirection.RIGHT),
		),
		states[0].stateMultiTransition(
			states[1],
			MultiTransition(tape1, 'd', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'd', MovementDirection.RIGHT),
		),
		/* FINISH STATE FOR FIRST SUBPROBLEM */
		/*states[0].stateMultiTransition(
			qF,
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),*/
		states[1].stateMultiTransition(
			states[0],
			MultiTransition(tape1, 'a', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[1].stateMultiTransition(
			states[0],
			MultiTransition(tape1, 'c', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[1].stateMultiTransition(
			states[0],
			MultiTransition(tape1, 'b', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'b', MovementDirection.RIGHT),
		),
		states[1].stateMultiTransition(
			states[0],
			MultiTransition(tape1, 'd', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', 'd', MovementDirection.RIGHT),
		),
		states[0].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[1].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[2].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape2, 'a', 'a', MovementDirection.LEFT),
		),
		states[2].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape2, 'b', 'b', MovementDirection.LEFT),
		),
		states[2].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape2, 'c', 'c', MovementDirection.LEFT),
		),
		states[2].stateMultiTransition(
			states[2],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape2, 'd', 'd', MovementDirection.LEFT),
		),
		states[2].stateMultiTransition(
			states[3],
			MultiTransition(tape1, '#', '#', MovementDirection.RIGHT),
			MultiTransition(tape2, '#', '#', MovementDirection.RIGHT),
		),
		states[3].stateMultiTransition(
			states[3],
			MultiTransition(tape1, '#', 'a', MovementDirection.RIGHT),
			MultiTransition(tape2, 'a', 'a', MovementDirection.RIGHT),
		),
		states[3].stateMultiTransition(
			states[3],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.RIGHT),
		),
		states[3].stateMultiTransition(
			states[3],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.RIGHT),
		),
		states[3].stateMultiTransition(
			states[3],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.RIGHT),
		),
		states[3].stateMultiTransition(
			states[4],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[4].stateMultiTransition(
			states[4],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.LEFT),
		),
		states[4].stateMultiTransition(
			states[4],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.LEFT),
		),
		states[4].stateMultiTransition(
			states[4],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.LEFT),
		),
		states[4].stateMultiTransition(
			states[4],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.LEFT),
		),
		states[4].stateMultiTransition(
			states[5],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.RIGHT),
		),
		states[5].stateMultiTransition(
			states[5],
			MultiTransition(tape1, '#', 'b', MovementDirection.RIGHT),
			MultiTransition(tape2, 'b', 'b', MovementDirection.RIGHT),
		),
		states[5].stateMultiTransition(
			states[5],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.RIGHT),
		),
		states[5].stateMultiTransition(
			states[5],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.RIGHT),
		),
		states[5].stateMultiTransition(
			states[5],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.RIGHT),
		),
		states[5].stateMultiTransition(
			states[6],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[6].stateMultiTransition(
			states[6],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.LEFT),
		),
		states[6].stateMultiTransition(
			states[6],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.LEFT),
		),
		states[6].stateMultiTransition(
			states[6],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.LEFT),
		),
		states[6].stateMultiTransition(
			states[6],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.LEFT),
		),
		states[6].stateMultiTransition(
			states[7],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.RIGHT),
		),
		states[7].stateMultiTransition(
			states[7],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.RIGHT),
		),
		states[7].stateMultiTransition(
			states[7],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.RIGHT),
		),
		states[7].stateMultiTransition(
			states[7],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.RIGHT),
		),
		states[7].stateMultiTransition(
			states[7],
			MultiTransition(tape1, '#', 'c', MovementDirection.RIGHT),
			MultiTransition(tape2, 'c', 'c', MovementDirection.RIGHT),
		),
		states[7].stateMultiTransition(
			states[8],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.STAY),
		),
		states[8].stateMultiTransition(
			states[8],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.LEFT),
		),
		states[8].stateMultiTransition(
			states[8],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.LEFT),
		),
		states[8].stateMultiTransition(
			states[8],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.LEFT),
		),
		states[8].stateMultiTransition(
			states[8],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'd', 'd', MovementDirection.LEFT),
		),
		states[8].stateMultiTransition(
			states[9],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, '#', '#', MovementDirection.RIGHT),
		),
		states[9].stateMultiTransition(
			states[9],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'a', 'a', MovementDirection.RIGHT),
		),
		states[9].stateMultiTransition(
			states[9],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'b', 'b', MovementDirection.RIGHT),
		),
		states[9].stateMultiTransition(
			states[9],
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape2, 'c', 'c', MovementDirection.RIGHT),
		),
		states[9].stateMultiTransition(
			states[9],
			MultiTransition(tape1, '#', 'd', MovementDirection.RIGHT),
			MultiTransition(tape2, 'd', 'd', MovementDirection.RIGHT),
		),
		states[9].stateMultiTransition(
			states[10],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape2, '#', '#', MovementDirection.LEFT),
		),
		states[10].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'a', 'a', MovementDirection.LEFT),
			MultiTransition(tape1, 'a', '1', MovementDirection.STAY),
		),
		states[10].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'b', 'b', MovementDirection.LEFT),
			MultiTransition(tape1, 'b', '1', MovementDirection.STAY),
		),
		states[10].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'c', 'c', MovementDirection.LEFT),
			MultiTransition(tape1, 'c', '1', MovementDirection.STAY),
		),
		states[10].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'd', 'd', MovementDirection.LEFT),
			MultiTransition(tape1, 'd', '1', MovementDirection.STAY),
		),
		states[10].stateMultiTransition(
			states[11],
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'a', 'a', MovementDirection.LEFT),
			MultiTransition(tape1, '1', '0', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'b', 'b', MovementDirection.LEFT),
			MultiTransition(tape1, '1', '0', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'c', 'c', MovementDirection.LEFT),
			MultiTransition(tape1, '1', '0', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'd', 'd', MovementDirection.LEFT),
			MultiTransition(tape1, '1', '0', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'a', 'a', MovementDirection.STAY),
			MultiTransition(tape1, '0', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'b', 'b', MovementDirection.STAY),
			MultiTransition(tape1, '0', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'c', 'c', MovementDirection.STAY),
			MultiTransition(tape1, '0', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'd', 'd', MovementDirection.STAY),
			MultiTransition(tape1, '0', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'a', 'a', MovementDirection.STAY),
			MultiTransition(tape1, 'a', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'b', 'b', MovementDirection.STAY),
			MultiTransition(tape1, 'b', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'c', 'c', MovementDirection.STAY),
			MultiTransition(tape1, 'c', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'd', 'd', MovementDirection.STAY),
			MultiTransition(tape1, 'd', '1', MovementDirection.STAY),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'a', 'a', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'b', 'b', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'c', 'c', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'd', 'd', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'a', 'a', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'b', 'b', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'c', 'c', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[12],
			MultiTransition(tape1, 'd', 'd', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.RIGHT),
		),
		states[12].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'a', 'a', MovementDirection.STAY),
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
		),
		states[12].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'b', 'b', MovementDirection.STAY),
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
		),
		states[12].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'c', 'c', MovementDirection.STAY),
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
		),
		states[12].stateMultiTransition(
			states[11],
			MultiTransition(tape1, 'd', 'd', MovementDirection.STAY),
			MultiTransition(tape1, '#', '#', MovementDirection.LEFT),
		),
		states[11].stateMultiTransition(
			qF,
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.STAY),
		),
		states[11].stateMultiTransition(
			qF,
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.STAY),
		),
		states[12].stateMultiTransition(
			qF,
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape1, '1', '1', MovementDirection.STAY),
		),
		states[12].stateMultiTransition(
			qF,
			MultiTransition(tape1, '#', '#', MovementDirection.STAY),
			MultiTransition(tape1, '0', '0', MovementDirection.STAY),
		),
	)
	
	val machine = MultiMachine(
		tapes,
		transitions,
		states[0],
		listOf(states[4]),
		tape1
	).run {
		run()
		print()
	}
}