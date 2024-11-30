package hw7.extensions

import hw7.Airport
import hw7.Connection
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.math.BigDecimal
import kotlin.collections.plusAssign

fun BigDecimal.milesToKilometers(): BigDecimal =
    this.multiply(BigDecimal("1.609344"))

fun JsonArray.toAirportConnections(): List<Airport> {
    val airports = mutableListOf<Airport>()

    this.forEach {
        it.jsonObject.also { airport ->
            airports += Airport(
                airport.getValue("code").jsonPrimitive.content,
                airport.getValue("name").jsonPrimitive.content
            )
        }
    }

    this.forEach {
        it.jsonObject.also { airport ->
            val current = airports.find { it.code == airport.getValue("name").jsonPrimitive.content }!!
            airport.getValue("connections").jsonArray.forEach { connection ->
                val distance = connection.jsonObject.getValue("distance").jsonObject
                var distanceValue = distance.getValue("value").jsonPrimitive.content.toBigDecimal()
                    .let { if (distance.getValue("unit").jsonPrimitive.content == "km") it else it.milesToKilometers() }

                current.connections += Connection(
                    airports.find { it.code == connection.jsonObject.getValue("code").jsonPrimitive.content }!!,
                    distanceValue
                )
            }
        }
    }

    return airports
}