package ram

import java.io.File

class Instructions(private val instructions: List<Triple<Instruction, ValueType, Int?>>) {
	operator fun get(index: Int) =
		if (index < 1) throw IndexOutOfBoundsException("Instruction index must be greater than 0")
		else instructions[index - 1]
	
	enum class Instruction {
		READ, WRITE, LOAD, STORE, ADD, SUB, MUL, DIV, JMP, JZ, JGTZ, JH, HALT
	}
	
	enum class ValueType {
		CONSTANT, DIRECT, INDIRECT, EMPTY
	}
	
	val size: Int
		get() = instructions.size
	
	companion object {
		fun fromFile(file: File): Instructions {
			val lines = file.readLines()
			val instructions = mutableListOf<Triple<Instruction, ValueType, Int?>>()
			
			for (line in lines) {
				val instructionString: String
				val valueString: String?
				
				line.split(" ").let {
					instructionString = it[0]
					valueString = it.getOrNull(1)
				}
				
				val instruction = parseInstruction(instructionString)
				val type = valueString?.first()?.let {
					if (it == '*') ValueType.INDIRECT
					else if (it == '=') ValueType.CONSTANT
					else ValueType.DIRECT
				} ?: ValueType.EMPTY
				val value = valueString?.let {
					if (it.first().let { it == '*' || it == '=' }) it.drop(1)
					else it
				}?.let {
					if (it.isBlank() || it == "#") null
					else it.toInt()
				}
				
				instructions.add(Triple(instruction, type, value))
			}
			
			return Instructions(instructions)
		}
		
		private fun parseInstruction(instruction: String): Instruction =
			when (instruction) {
				"READ" -> Instruction.READ
				"WRITE" -> Instruction.WRITE
				"LOAD" -> Instruction.LOAD
				"STORE" -> Instruction.STORE
				"ADD" -> Instruction.ADD
				"SUB" -> Instruction.SUB
				"MUL" -> Instruction.MUL
				"DIV" -> Instruction.DIV
				"JMP" -> Instruction.JMP
				"JZ" -> Instruction.JZ
				"JGTZ" -> Instruction.JGTZ
				"JH" -> Instruction.JH
				"HALT" -> Instruction.HALT
				else -> throw IllegalArgumentException("Unknown instruction: $instruction")
			}
	}
}