# Rekurzivní funkce a Dynamické programování

## Autoři

- Kateřina Šůstalová
- Vojtěch Píža

## Popis

NPC může vyjít z každého schodu (včetně patra) o jeden nebo dva kroky. Skákat může pouze ze schodů na schody mezi
stejnými patry, případně na patro samotné (nelze skočit ze 4. schodu na 1. schod nad dalším patrem, a přeskočit tedy patro).
Výtahy je možné používat pouze z patra na patro, nikoliv mezi schody.

Je možné nastavit počáteční patro a schod, na kterém se NPC nachází (`Game.start(floor, stair)`), stejně tak cílové
patro a schod (`Game(floor, stair)`). NPC může postupovat pouze vhůru (cílové hodnoty patra a schodu nižší než počáteční
patro a schod povedou k nenalezení žádné cesty; všechny hodnoty musí být ≥ 0).

## Výsledky

Nejkratší cesta z 0. patra a 0. schodu do 25. patra na 4. schod má celkem 27 kroků (25× výtah + krok + skok).<br>
Celkový počet všech nalezených možných cest je cca 261,9 sextiliard.

```txt
Shortest path:
Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Elevator -> Step -> Jump
Total: 27 moves

Total paths: 261 853 330 279 369 823 566 432 247 911 978 053 632 (≈ 261,9 undecillion)
```

## Složitost

Mapování cest je řešeno pomocí iterativní funkce, čímž je docíleno prostorvé složistoi `O(n * m)`, kde `n` je počet
všech meoizovaných schodů (včetně pater) a `m` je maximální počet možných cest z daného schodu. Časová složitost je
rovněž `O(n * m)`, kde `n` je počet iterací (`finalStepNum` - `startingStepNum`) a `m` je počet možných kroků z daného
schodu. Rekurzivní verze by měla stejné složitosti, avšak použití iterativní funkce eliminuje možnost stack overflow.

Prostorová složitost iterativního hledání nejkratší cesty je `O(m)`, kde `m` je počet kroků nejkratší cesty. Časová složistost je
`O(m * k)`, kde `m` je počet kroků nejkratší cesty a `k` jsou všechny evaluované možné cesty na další schod.

Časová složitost rekurzivního spočítání všech možných cest je `O(n * k)`, kde `n` je celkový počet kroků a `k` je počet
možností na krok. Prostorová složitost je `O(d + n)`, kde `d` je počet vnoření a `n` je celkový počet výsledků.

### Rekurzivní způsob hledání nejkratší cesty

Rekurzivní verze má stejnou časovou a prostorovou složitost jako iterativní verze, avšak může být náchylnější na
stack overflow.

```kotlin
private fun findShortestPath(currentStep: Step, resultPath: MutableList<Path> = mutableListOf()): List<Path> {
    if (currentStep.number == startingStepNumber) {
        return resultPath
    }

    val path = currentStep.options.find { it.previous?.number?.mod(FLOOR_STEP_DISTANCE) == 0 }
        ?: currentStep.options.maxBy { it.move }

    resultPath.add(0, path)

    return findShortestPath(path.previous!!, resultPath)
}
```

### Iterativní způsob hledání všech cest

Stejná časová složitost jako rekurzivní verze, prostorová je `O(2n)`. Přestože ji zjednodušíme na `O(n)`, stejně
jako u rekurziního přístupu, reálně by zde mohl být vidět rozdíl, pokud by u rekurzivního bylo `d` o dost menší než `m`.
Zase ale eliminujeme možnost stack overflow.

```kotlin
private fun countAllPaths(startingStep: Step): BigInteger {
    val memo = mutableMapOf<Int, BigInteger>()
    val stack = ArrayDeque<Step>()
    stack.add(startingStep)

    while (stack.isNotEmpty()) {
        val current = stack.last()

        if (current.number == startingStepNumber) {
            memo[current.number] = 1.toBigInteger()
            stack.removeLast()
        } else if (current.options.all { it.previous!!.number in memo }) {
            val totalPaths = current.options.sumOf { option ->
                memo[option.previous!!.number]!!
            }
            memo[current.number] = totalPaths
            stack.removeLast()
        } else {
            for (option in current.options) {
                val previousStep = option.previous
                if (previousStep != null && previousStep.number !in memo) {
                    stack.add(previousStep)
                }
            }
        }
    }

    return memo[startingStep.number]!!
}
```