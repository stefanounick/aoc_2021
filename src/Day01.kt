fun main() {
    fun part1(input: List<String>): Int {
        var increased = 0
        var previous = -1
        input.forEach {
            val n = it.toInt()
            if (n > previous && previous != -1)
                increased++
            previous = n
        }
        return increased
    }

    fun part2(input: List<String>): Int {
        var increased = 0
        var previous = -1
        val numbers = input.map{ it.toInt() }.windowed(3)

        numbers.forEach {
            val n = it.sum()
            if (n > previous && previous != -1)
                increased++
            previous = n
        }
        return increased
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
