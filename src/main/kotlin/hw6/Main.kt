package hw6

fun main() {
	val minasTirith = Node("Minas Tirith")
	val gondolin = Node("Gondolin")
	val ankhMorpork = Node("Ankh-Morpork")
	val mosEisley = Node("Mos Eisley")
	val arrakeen = Node("Arrakeen")
	val lv426 = Node("LV-426")
	val godricsHollow = Node("Godric's Hollow")
	val mordheim = Node("Mordheim")
	val rivie = Node("Rivie")
	val solitude = Node("Solitude")
	val tristam = Node("Tristam")
	val lordaeron = Node("Lordaeron")
	val lannisport = Node("Lannisport")
	
	val nodes = listOf<Node>(
		minasTirith,
		gondolin,
		ankhMorpork,
		mosEisley,
		arrakeen,
		lv426,
		godricsHollow,
		mordheim,
		rivie,
		solitude,
		tristam,
		lordaeron,
		lannisport,
	)
	
	val edges = listOf<Edge>(
		minasTirith to gondolin over 9,
		minasTirith to mordheim over 8,
		minasTirith to rivie over 3,
		minasTirith to tristam over 4,
		
		gondolin to mordheim over 5,
		gondolin to godricsHollow over 15,
		gondolin to mosEisley over 20,
		gondolin to ankhMorpork over 5,
		
		ankhMorpork to mosEisley over 4,
		ankhMorpork to lannisport over 10,
		ankhMorpork to arrakeen over 24,
		
		mosEisley to lv426 over 7,
		
		godricsHollow to lv426 over 3,
		godricsHollow to mordheim over 1,
		
		rivie to solitude over 8,
		
		tristam to rivie over 12,
		tristam to arrakeen over 7,
		tristam to lordaeron over 2,
		
		arrakeen to lannisport over 16,
		arrakeen to lordaeron over 9,
	)
	
	kruskal(edges, nodes).forEach(::println)
}
