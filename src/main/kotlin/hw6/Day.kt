package hw6

class Day {
    var hours = WORKDAY_HOURS
    val workLogs = mutableListOf<WorkLog>()

    companion object {
        const val WORKDAY_HOURS: Int = 8
    }
}
