package hw7

import hw7.extensions.toAirportConnections
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val airports = File("src/main/resources/data.json").inputStream().readBytes().toString(Charsets.UTF_8)
        .let { Json.parseToJsonElement(it).jsonArray }.toAirportConnections()

    val flights: List<Pair<Airport, Airport>> = listOf(
        airports.find { it.code == "PRG" }!! to airports.find { it.code == "DEL" }!!,
        airports.find { it.code == "BRE" }!! to airports.find { it.code == "FNJ" }!!,
        airports.find { it.code == "JFK" }!! to airports.find { it.code == "CAI" }!!,
        airports.find { it.code == "DUB" }!! to airports.find { it.code == "DME" }!!,
        airports.find { it.code == "OKA" }!! to airports.find { it.code == "EVX" }!!,
    )

    println("Dijkstra's algorithm")
    flights.map { (start, goal) -> dijkstra(start, goal) }
        .also(::printResults)

    println()

    println("A* search")
    flights.map { (start, goal) ->
        aStarSearch(start, goal) { current, goal ->
            combinedHeuristic(current, goal)
        }
    }.also(::printResults)

}

fun printResults(results: List<List<Airport>>) {
    results.forEachIndexed { index, path ->
        var totalDistance = BigDecimal.ZERO

        print("[${index + 1}]: ")

        path.forEachIndexed { index, airport ->
            val distance = airport.connections
                .find { it.airport == path.getOrNull(index + 1) }?.distance ?: BigDecimal.ZERO

            print(airport.code)
            print("(${totalDistance.setScale(2, RoundingMode.HALF_UP)}, ${distance.setScale(2, RoundingMode.HALF_UP)})")

            if (index != path.size - 1) {
                print(" -> ")
                totalDistance += distance
            }
        }

        println()
    }
}

