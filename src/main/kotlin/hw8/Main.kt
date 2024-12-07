package hw8

import extensions.resource
import ram.Instructions
import ram.RAM
import ram.Tape


fun main() {
	val file1 = resource("ram/1.txt")
	val tape1 = Tape("1|1|1|2|2|2|3|3|3")
	val instructions1 = Instructions.fromFile(file1)

	println("1. {1ⁿ 2ⁿ 3ⁿ | n ≥ 0}")
	println("Tape IN: $tape1")
	RAM(tape1, instructions1).start()

	println()

	val file2 = resource("ram/2.txt")
	val tape2 = Tape("1|2|3|4|5")
	val instructions2 = Instructions.fromFile(file2)

	println("2. n")
	println("Tape IN: $tape2")
	RAM(tape2, instructions2).start()

	println()

	val file3 = resource("ram/3.txt")
	val tape3 = Tape("0|1|2|3|4|5|6|7|8|9")
	val instructions3 = Instructions.fromFile(file3)

	println("3. reversed")
	println("Tape IN: $tape3")
	RAM(tape3, instructions3).start()

	println()

	val file4 = resource("ram/4.txt")
	val tape4 = Tape("0|0|1|1|2|2|1|1|0|2|0|1|2|1|0")
	val instructions4 = Instructions.fromFile(file4)

	println("4. Σ = {0, 1, 2}, remove '1's")
	println("Tape IN: $tape4")
	RAM(tape4, instructions4).start()
}