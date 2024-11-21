package hw6

fun main() {
    val data =
        "(Lordaeron)-[2]-(Tristram) (Lordaeron)-[9]-(Arrakeen) (Tristram)-[2]-(Lordaeron) (Tristram)-[7]-(Arrakeen) (Tristram)-[12]-(Rivia) (Tristram)-[4]-(Minas Tirith) (Solitude)-[8]-(Rivia) (Arrakeen)-[16]-(Lannisport) (Arrakeen)-[9]-(Lordaeron) (Arrakeen)-[7]-(Tristram) (Arrakeen)-[24]-(Ankh Morpork) (Lannisport)-[16]-(Arrakeen) (Lannisport)-[10]-(Ankh Morpork) (Minas Tirith)-[4]-(Tristram) (Minas Tirith)-[3]-(Rivia) (Minas Tirith)-[8]-(Mordheim) (Minas Tirith)-[9]-(Gondolin) (Rivia)-[8]-(Solitude) (Rivia)-[12]-(Tristram) (Rivia)-[3]-(Minas Tirith) (Gondolin)-[9]-(Minas Tirith) (Gondolin)-[5]-(Mordheim) (Gondolin)-[15]-(Godric's Hollow) (Gondolin)-[20]-(Mos Eisley) (Gondolin)-[5]-(Ankh Morpork) (Mordheim)-[8]-(Minas Tirith) (Mordheim)-[5]-(Gondolin) (Mordheim)-[1]-(Godric's Hollow) (Ankh Morpork)-[10]-(Lannisport) (Ankh Morpork)-[24]-(Arrakeen) (Ankh Morpork)-[5]-(Gondolin) (Ankh Morpork)-[4]-(Mos Eisley) (Mos Eisley)-[4]-(Ankh Morpork) (Mos Eisley)-[20]-(Gondolin) (Mos Eisley)-[7]-(LV-426) (Godric's Hollow)-[1]-(Mordheim) (Godric's Hollow)-[15]-(Gondolin) (Godric's Hollow)-[3]-(LV-426) (LV-426)-[7]-(Mos Eisley) (LV-426)-[3]-(Godric's Hollow)"
    val cities = mutableSetOf<Node>()
    val powerLines = mutableSetOf<Edge>()

    data.split(") (", "]-", "-[").map { it.trim('(', ')', '[', ']') }.chunked(3).map {
        val (from, weight, to) = it
        val fromNode = cities.find { it.name == from } ?: Node(from).also { cities.add(it) }
        val toNode = cities.find { it.name == to } ?: Node(to).also { cities.add(it) }

        powerLines.add(fromNode to toNode over weight.toInt())
    }

    val results = mutableListOf<Pair<String, Pair<Int, Int>>>()

    ElectricCompany(kruskal(powerLines, cities))
        .also { it.build(); it.printWorkload(); results.add("Kruskal" to it.results) }
    ElectricCompany(jarnik(cities))
        .also { it.build(); it.printWorkload(); results.add("Jarnik" to it.results) }
    ElectricCompany(boruvka(cities))
        .also { it.build(); it.printWorkload(); results.add("Boruvka" to it.results) }

    results.sortedWith(compareBy<Pair<String, Pair<Int, Int>>> { it.second.first }.thenBy { it.second.second })
        .forEachIndexed { index, value -> println("${index + 1}. ${value.first}") }
}
