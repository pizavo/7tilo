package hw9

import extensions.toWords
import java.math.BigInteger

class Game(floor: Int, stair: Int = 0) {
    private val finalStepNumber: Int = calculateStepNumber(floor, stair)
    private val memo: MutableMap<Int, Step> = mutableMapOf()
    private val startingStepNumber by lazy { memo.keys.min() }
    private val finalStep by lazy { if (finalStepNumber != startingStepNumber) memo[finalStepNumber] else null }

    companion object {
        private const val FLOOR_STEP_DISTANCE = 6
        private const val ELEVATOR = FLOOR_STEP_DISTANCE
        private const val JUMP = 3
        private const val BIG_STEP = 2
        private const val STEP = 1
        private val moves = listOf(ELEVATOR, JUMP, BIG_STEP, STEP)
    }

    private fun calculateStepNumber(floor: Int, stair: Int): Int {
        require(floor >= 0) { "Floor must be greater than or equal 0" }
        require(stair >= 0 && stair < FLOOR_STEP_DISTANCE)
        { "Stair must be between 0 and ${FLOOR_STEP_DISTANCE - 1}, included" }

        return floor * FLOOR_STEP_DISTANCE + stair
    }

    private fun mapPaths() {
        for (i in startingStepNumber..finalStepNumber - 1) {
            move(memo[i] ?: continue)
        }
    }

    private fun move(currentStep: Step) {
        for (move in moves) {
            val nextStepNumber = currentStep.number + move

            if (nextStepNumber <= finalStepNumber
                && (move < JUMP || (move == ELEVATOR && currentStep.number % FLOOR_STEP_DISTANCE == 0)
                        || (move == JUMP && currentStep.number % FLOOR_STEP_DISTANCE < FLOOR_STEP_DISTANCE - 2))
            ) {
                memo[nextStepNumber]?.options?.add(Path(move, currentStep))
                    ?: Step(nextStepNumber, mutableListOf(Path(move, currentStep)))
                        .also { memo.put(nextStepNumber, it) }
            }
        }
    }

    fun start(floor: Int = 0, stair: Int = 0) {
        calculateStepNumber(floor, stair).let { memo.put(it, Step(it)) }
        mapPaths()
    }

    private fun findShortestPath(startingStep: Step): List<Path> {
        val resultPath = ArrayDeque<Path>()
        var currentStep: Step? = startingStep

        while (currentStep != null && currentStep.number != startingStepNumber) {
            val path = currentStep.options.find { it.previous?.number?.mod(FLOOR_STEP_DISTANCE) == 0 }
                ?: currentStep.options.maxByOrNull { it.move }

            if (path != null) {
                resultPath.addFirst(path)
                currentStep = path.previous
            } else {
                break
            }
        }

        return resultPath.toList()
    }


    private fun findShortestPath(): List<Path>? =
        finalStep?.let { findShortestPath(it) }


    private fun printShortestPath() {
        println("Shortest path:")
        findShortestPath()?.let {
            it.joinToString(" -> ") { moveToString(it.move) }.also { println(it) }
            println("Total: ${it.size} moves")
        } ?: println("No path found")
    }

    fun print() {
        printShortestPath()
        println()
        printCountOfAllPaths()
    }

    fun printCountOfAllPaths() =
        countAllPaths()?.let { println("Total paths: %,d (≈ %s)".format(it, it.toWords())) }

    private fun moveToString(move: Int): String =
        when (move) {
            ELEVATOR -> "Elevator"
            JUMP -> "Jump"
            BIG_STEP -> "Two steps"
            STEP -> "Step"
            else -> throw IllegalArgumentException("Unknown move: $move")
        }

    fun countAllPaths(): BigInteger? =
        finalStep?.let { countAllPaths(it, mutableMapOf<Int, BigInteger>()) }

    private fun countAllPaths(currentStep: Step, memo: MutableMap<Int, BigInteger>): BigInteger {
        if (memo.containsKey(currentStep.number)) return memo[currentStep.number]!!
        if (currentStep.number == startingStepNumber) return 1.toBigInteger()

        val totalPaths = currentStep.options.sumOf { countAllPaths(it.previous!!, memo) }
        memo[currentStep.number] = totalPaths

        return totalPaths
    }
}