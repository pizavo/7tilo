package hw6

class ElectricCompany(private val blueprint: List<Edge>) {
    private val days: MutableList<Day> = mutableListOf()
    private val connected: MutableSet<MutableSet<Node>> = mutableSetOf(mutableSetOf(blueprint.first().first))
    private var last: Node = blueprint.first().first

    fun build() {
        for (powerLine in blueprint) {
            var day = Day().also { days.add(it) }

            val group = connected.find { it.contains(powerLine.first) || it.contains(powerLine.second) }
                ?: mutableSetOf(powerLine.first).also { connected.add(it) }
            val from = group.find { it == powerLine.first || it == powerLine.second }!!
            val to = powerLine.getOtherNode(from)

            if (!group.contains(last)) {
                getWorkLog(day, from, to).also { it.worked++ }
                day.hours--
            }

            repeat(powerLine.weight) {
                if (days.last().hours == 0) {
                    day = Day().also { days.add(it) }
                }

                getWorkLog(day, from, to).also { it.worked++; it.built++ }
                day.hours--
            }

            connected.find { it.contains(to) }?.also { group.addAll(it); connected.remove(it) } ?: group.add(to)
            last = to
        }
    }

    private fun getWorkLog(day: Day, from: Node, to: Node): WorkLog =
        day.workLogs.find { it.from == from && it.to == to }
            ?: WorkLog(from, to).also { day.workLogs.add(it) }

    fun printWorkload() {
        days.forEachIndexed { index, day ->
            day.workLogs.forEach { log ->
                println("[d_${index + 1}] ${log.from} -> ${log.to}: ${log.worked} hours, ${log.built} km")
            }
        }

        println("-------------------------------------")
        println("result: $totalDays days, $totalKm km")
        println()
    }

    private val totalDays: Int
        get() = days.size

    private val totalKm: Int
        get() = days.sumOf { day -> day.workLogs.sumOf { it.built } }

    val results
        get() = Pair(totalKm, totalDays)
}