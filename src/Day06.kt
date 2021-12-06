fun main() {
    fun simulate(input: List<String>, days: Int): Long {
        val fish = LongArray(9) {0L}
        var counter = 0L

        input[0].split(',').forEach {
            fish[it.toInt()]++
        }

        for (i in 0 until days){
            val temp = fish.copyOf()
            for (j in 7 downTo  0)
                fish[j] = temp[j + 1]
            fish[6] += temp[0]
            fish[8] = temp[0]
        }

        for (i in 0 until 9)
            counter += fish[i]

        return counter
    }

    fun part1(input: List<String>): Long {
        return simulate(input, 80)
    }

    fun part2(input: List<String>): Long {
        return simulate(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
