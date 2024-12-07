package ram

class Register {
	private var index: Int = 0
	private val register: MutableList<Int?> = mutableListOf()
	
	operator fun get(index: Int) =
		if (index < 1) throw IndexOutOfBoundsException("Register index must be greater than 0")
		else (index - 1).also { this.index = it }.let { register[it] }
		
	operator fun set(index: Int, value: Int?) {
		if (index < 1) throw IndexOutOfBoundsException("Register index must be greater than 0")
		else (index - 1).also { this.index = it; fill(it) }.let { register[it] = value }
	}
	
	private fun fill(index: Int) {
		while (register.size <= index) register.add(null)
	}
	
	fun offset(offset: Int) =
		(this.index + offset).let {
			if (it < 0) throw IndexOutOfBoundsException("Register index must be greater than 0")
			
			it
		}
	
	fun offsetted(offset: Int) =
		register[offset(offset)]
}
