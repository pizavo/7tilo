package hw8

import extensions.resource
import ram.Instructions
import ram.RAM
import ram.Tape


fun main() {
	val path1 = resource("ram/1.txt")
	
	val tape1 = Tape("1|1|1|2|2|3|3|3")
	val instructions1 = Instructions.fromFile(path1)
	
	RAM(tape1, instructions1).start()
	
	/*val file2 = resource("ram/2.txt")
	
	val tape2 = Tape("1|2|3|4|5")
	val instructions2 = Instructions.fromFile(file2)
	
	RAM(tape2, instructions2).start()*/
}