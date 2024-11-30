package hw7

import java.math.BigDecimal
import java.util.*

fun dijkstra(start: Airport, goal: Airport): List<Airport> {
    val distances = mutableMapOf<Airport, BigDecimal>().withDefault { BigDecimal.valueOf(Double.MAX_VALUE) }
    val priorityQueue = PriorityQueue<Pair<Airport, BigDecimal>>(compareBy { it.second })
    val cameFrom = mutableMapOf<Airport, Airport?>()

    distances[start] = BigDecimal.ZERO
    cameFrom[start] = null
    priorityQueue.add(start to BigDecimal.ZERO)

    while (priorityQueue.isNotEmpty()) {
        val (currentAirport, currentDist) = priorityQueue.poll()

        // Stop as soon as we reach the goal
        if (currentAirport == goal) {
            return reconstructPath(cameFrom, goal)
        }

        currentAirport.connections.forEach { connection ->
            val tentativeDist = currentDist + connection.distance

            // If a shorter path is found, update distances and cameFrom
            if (tentativeDist < distances.getValue(connection.airport)) {
                distances[connection.airport] = tentativeDist
                cameFrom[connection.airport] = currentAirport
                priorityQueue.add(connection.airport to tentativeDist)
            }
        }
    }

    return emptyList()
}

fun aStarSearch(start: Airport, goal: Airport, heuristic: (Airport, Airport) -> BigDecimal): List<Airport> {
    val openSet = PriorityQueue<Pair<Airport, BigDecimal>>(compareBy { it.second })
    val cameFrom = mutableMapOf<Airport, Airport?>()
    val gScore = mutableMapOf<Airport, BigDecimal>().withDefault { BigDecimal.valueOf(Double.MAX_VALUE) }
    val fScore = mutableMapOf<Airport, BigDecimal>().withDefault { BigDecimal.valueOf(Double.MAX_VALUE) }

    gScore[start] = BigDecimal.ZERO
    fScore[start] = heuristic(start, goal)
    openSet.add(start to fScore[start]!!)

    while (openSet.isNotEmpty()) {
        val (current, _) = openSet.poll()

        if (current == goal) {
            return reconstructPath(cameFrom, current)
        }

        current.connections.forEach { connection ->
            val tentativeGScore = gScore.getValue(current) + connection.distance

            if (tentativeGScore < gScore.getValue(connection.airport)) {
                cameFrom[connection.airport] = current
                gScore[connection.airport] = tentativeGScore
                fScore[connection.airport] = tentativeGScore + heuristic(connection.airport, goal)
                if (connection.airport !in openSet.map { it.first }) {
                    openSet.add(connection.airport to fScore[connection.airport]!!)
                }
            }
        }
    }

    return emptyList()
}

private fun reconstructPath(cameFrom: Map<Airport, Airport?>, current: Airport): List<Airport> {
    var currentNode = current
    val totalPath = mutableListOf(currentNode)

    while (cameFrom[currentNode] != null) {
        currentNode = cameFrom[currentNode]!!
        totalPath.add(currentNode)
    }

    return totalPath.reversed()
}