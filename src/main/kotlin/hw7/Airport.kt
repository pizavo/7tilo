package hw7

@JvmRecord
data class Airport(val name: String, val code: String, val connections: MutableList<Connection> = mutableListOf()) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Airport) return false

        if (name != other.name) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + code.hashCode()
        return result
    }
}