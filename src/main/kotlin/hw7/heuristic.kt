package hw7

import hw7.extensions.average
import java.math.BigDecimal

fun combinedHeuristic(current: Airport, goal: Airport): BigDecimal {
    val directConnection = current.connections.find { it.airport.code == goal.code }?.distance

    val avgCurrent = current.connections.map { it.distance }.average()
    val avgGoal = goal.connections.map { it.distance }.average()

    val centralityFactor = (current.connections.size + goal.connections.size).toBigDecimal()

    return when {
        directConnection != null -> directConnection
        else -> avgCurrent.add(avgGoal).divide(BigDecimal.TWO).add(centralityFactor)
    }
}
