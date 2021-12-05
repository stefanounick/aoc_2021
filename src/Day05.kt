import kotlin.math.*

fun main() {
    fun findDangerousAreas(input: List<String>, f: (Array<IntArray>, Point, Point) -> Unit): Int {
        val floorSize = 1000
        val floor = Array(floorSize) { IntArray(floorSize) { 0 } }

        val r = Regex("(\\d+),(\\d+)\\s+->\\s+(\\d+),(\\d+)")
        input.forEach {
            val matchResult = r.find(it)
            val groupValues = matchResult!!.groupValues

            val start = Point(groupValues[1].toInt(), groupValues[2].toInt())
            val end = Point(groupValues[3].toInt(), groupValues[4].toInt())

            f(floor, start, end)
        }

        var dangerousAreas = 0
        for (i in 0 until floorSize){
            for (j in 0 until floorSize) {
                if (floor[i][j] >= 2)
                    dangerousAreas++
            }
        }
        return dangerousAreas
    }

    fun part1(input: List<String>): Int {
        val progression: (floor: Array<IntArray>, start: Point, end: Point) -> Unit = {
                floor: Array<IntArray>, start: Point, end: Point ->
            if (start.x == end.x || start.y == end.y) {
                if (start.x == end.x) {
                    for (j in IntProgression.fromClosedRange(start.y, end.y, if (start.y < end.y) 1 else -1)){
                        floor[start.x][j]++
                    }
                } else {
                    for (i in IntProgression.fromClosedRange(start.x, end.x, if (start.x < end.x) 1 else -1)){
                        floor[i][start.y]++
                    }
                }
            }
        }
        return findDangerousAreas(input, progression)
    }

    fun part2(input: List<String>): Int {
        val progression: (floor: Array<IntArray>, start: Point, end: Point) -> Unit = {
                floor: Array<IntArray>, start: Point, end: Point ->

            val stepsX = start.x - end.x
            val stepsY = start.y - end.y

            for (i in 0..max(abs(stepsX), abs(stepsY))){
                val x = if (stepsX == 0) end.x else if (stepsX > 0) end.x + (stepsX - i) else end.x + (stepsX + i)
                val y = if (stepsY == 0) end.y else if (stepsY > 0) end.y + (stepsY - i) else end.y + (stepsY + i)
                floor[x][y]++
            }
        }
        return findDangerousAreas(input, progression)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
