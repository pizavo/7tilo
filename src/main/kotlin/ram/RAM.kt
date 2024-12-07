package ram

typealias Instruction = Instructions.Instruction
typealias ValueType = Instructions.ValueType

class RAM(private val input: Tape, private val instructions: Instructions, private val output: Tape = Tape()) {
	private var instructionPointer: Int = 0
	private var memory: Int? = null
	private val register: Register = Register()
	
	private fun read(index: Int) =
		input.read().let {
			if (index == 0) memory = it
			else register[index] = it
		}
	
	private fun write(value: Int?) =
		output.write(value)
	
	private fun writeFrom(index: Int) =
		write(
			if (index == 0) memory
			else register[index]
		)
	
	private fun writeBy(offset: Int) =
		write(register.offsetted(offset))
	
	private fun load(value: Int?) {
		memory = value
	}
	
	private fun loadFrom(index: Int) =
		load(register[index])
	
	private fun loadBy(offset: Int) =
		load(register.offsetted(offset))
	
	private fun store(index: Int) {
		register[index] = memory
	}
	
	private fun storeBy(offset: Int) =
		store(register.offset(offset))
	
	private fun add(value: Int) {
		memory = memory?.let { it + value } ?: throw IllegalStateException("Memory is null")
	}
	
	private fun addFrom(index: Int) =
		add(register[index] ?: throw IllegalArgumentException("Value in register is null"))
	
	private fun addBy(offset: Int) =
		addFrom(register.offset(offset))
	
	private fun subtract(value: Int) {
		memory = memory?.let { it - value } ?: throw IllegalStateException("Memory is null")
	}
	
	private fun subtractFrom(index: Int) =
		subtract(register[index] ?: throw IllegalArgumentException("Value in register is null"))
	
	private fun subtractBy(offset: Int) =
		subtractFrom(register.offset(offset))
	
	private fun multiply(value: Int) {
		memory = memory?.let { it * value } ?: throw IllegalStateException("Memory is null")
	}
	
	private fun multiplyFrom(index: Int) =
		multiply(register[index] ?: throw IllegalArgumentException("Value in register is null"))
	
	private fun multiplyBy(offset: Int) =
		multiplyFrom(register.offset(offset))
	
	private fun divide(value: Int) {
		memory = memory?.let { it / value } ?: throw IllegalStateException("Memory is null")
	}
	
	private fun divideFrom(index: Int) =
		divide(register[index] ?: throw IllegalArgumentException("Value in register is null"))
	
	private fun divideBy(offset: Int) =
		divideFrom(register.offset(offset))
	
	private fun jump(index: Int) {
		instructionPointer = index - 1
	}
	
	private fun jumpIfZero(index: Int) {
		if (memory == 0) jump(index)
	}
	
	private fun jumpIfGreaterThanZero(index: Int) =
		memory?.let { if (it > 0) jump(index) } ?: Unit
	
	private fun jumpIfEmpty(index: Int) {
		if (memory == null) jump(index)
	}
	
	private fun halt(): Unit? {
		println("Result: $output")
		return null
	}
	
	private fun handleWrite(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> write(value)
			ValueType.DIRECT -> writeFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> writeBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleLoad(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> load(value)
			ValueType.DIRECT -> loadFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> loadBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleStore(type: ValueType, value: Int?) =
		when (type) {
			ValueType.DIRECT -> store(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> storeBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleAdd(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> add(value ?: throw IllegalArgumentException("Value is null"))
			ValueType.DIRECT -> addFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> addBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleSubtract(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> subtract(value ?: throw IllegalArgumentException("Value is null"))
			ValueType.DIRECT -> subtractFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> subtractBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleMultiply(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> multiply(value ?: throw IllegalArgumentException("Value is null"))
			ValueType.DIRECT -> multiplyFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> multiplyBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun handleDivide(type: ValueType, value: Int?) =
		when (type) {
			ValueType.CONSTANT -> divide(value ?: throw IllegalArgumentException("Value is null"))
			ValueType.DIRECT -> divideFrom(value ?: throw IllegalArgumentException("Index is null"))
			ValueType.INDIRECT -> divideBy(value ?: throw IllegalArgumentException("Offset is null"))
			else -> throw IllegalArgumentException("$type is not allowed")
		}
	
	private fun executeInstruction() =
		instructions[instructionPointer].let {
			when (it.first) {
				Instruction.READ -> read(it.third ?: throw IllegalArgumentException("Value is null"))
				Instruction.WRITE -> handleWrite(it.second, it.third)
				Instruction.LOAD -> handleLoad(it.second, it.third)
				Instruction.STORE -> handleStore(it.second, it.third)
				Instruction.ADD -> handleAdd(it.second, it.third)
				Instruction.SUB -> handleSubtract(it.second, it.third)
				Instruction.MUL -> handleMultiply(it.second, it.third)
				Instruction.DIV -> handleDivide(it.second, it.third)
				Instruction.JMP -> jump(it.third ?: throw IllegalArgumentException("Value is null"))
				Instruction.JZ -> jumpIfZero(it.third ?: throw IllegalArgumentException("Value is null"))
				Instruction.JGTZ -> jumpIfGreaterThanZero(it.third ?: throw IllegalArgumentException("Value is null"))
				Instruction.JH -> jumpIfEmpty(it.third ?: throw IllegalArgumentException("Value is null"))
				Instruction.HALT -> halt()
			}
		}
	
	fun start() {
		while (instructionPointer++ < instructions.size) {
			if (executeInstruction() == null) break
		}
	}
}