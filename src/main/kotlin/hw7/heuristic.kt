package hw7

import hw7.extensions.average
import java.math.BigDecimal
import java.math.RoundingMode

fun combinedHeuristic(current: Airport, goal: Airport, graph: List<Airport>): BigDecimal {
    val directConnection = current.connections.find { it.airport.code == goal.code }?.distance

    return when {
        directConnection != null -> directConnection
        else -> averageConnectionCostHeuristic(current, goal, graph)
            .add(centralityHeuristic(current, goal, graph))
    }
}

fun centralityHeuristic(current: Airport, goal: Airport, graph: List<Airport>): BigDecimal {
    val maxDegree = graph.maxOf { it.connections.size }.takeIf { it > 0 } ?: 1
    val centralityCurrent = BigDecimal.ONE - (current.connections.size.toBigDecimal() / maxDegree.toBigDecimal())
    val centralityGoal = BigDecimal.ONE - (goal.connections.size.toBigDecimal() / maxDegree.toBigDecimal())

    return centralityCurrent.add(centralityGoal).divide(BigDecimal.TWO, RoundingMode.HALF_UP)
}

fun averageConnectionCostHeuristic(current: Airport, goal: Airport, graph: List<Airport>): BigDecimal {
    val maxEdgeWeight = graph.flatMap { it.connections.map { connection -> connection.distance } }
        .maxOrNull() ?: BigDecimal.ONE

    val avgCurrent = current.connections.map { it.distance }.average().divide(maxEdgeWeight, RoundingMode.HALF_UP)
    val avgGoal = goal.connections.map { it.distance }.average().divide(maxEdgeWeight, RoundingMode.HALF_UP)

    val connectionFactorCurrent =
        if (current.connections.isNotEmpty())
            BigDecimal.ONE.divide(current.connections.size.toBigDecimal(), RoundingMode.HALF_UP)
        else BigDecimal.ONE
    val connectionFactorGoal =
        if (goal.connections.isNotEmpty())
            BigDecimal.ONE.divide(goal.connections.size.toBigDecimal(), RoundingMode.HALF_UP)
        else BigDecimal.ONE

    return avgCurrent.multiply(connectionFactorCurrent)
        .add(avgGoal.multiply(connectionFactorGoal))
        .divide(BigDecimal.TWO, RoundingMode.HALF_UP)
}
