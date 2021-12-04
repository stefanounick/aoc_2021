fun main() {
    fun findCommonBit(input: List<String>, index: Int, common: Boolean): Boolean {
        if (index >= input[0].length) throw Exception()
        var countOne = 0
        var countZero = 0

        input.forEach {
            if (it[index] == '0')
                countZero++
            else
                countOne++
        }

        return if (common) countOne >= countZero else countOne < countZero
    }

    fun part1(input: List<String>): Int {
        val size = input[0].length
        val countOne = IntArray(size)
        val countZero = IntArray(size)

        input.forEach {
            it.forEachIndexed { index, c ->
                if (c == '0')
                    countZero[index]++
                else
                    countOne[index]++
            }
        }

        val gammaRate = StringBuilder("0".repeat(size))
        val epsilonRate = StringBuilder("0".repeat(size))
        for (i in countZero.indices){
            if (countZero[i] > countOne[i]) {
                gammaRate[i] = '0'
                epsilonRate[i] = '1'
            }
            else {
                gammaRate[i] = '1'
                epsilonRate[i] = '0'
            }
        }

        return gammaRate.toString().toInt(2) * epsilonRate.toString().toInt(2)
    }

    fun part2(input: List<String>): Int {
        val size = input[0].length

        var init = input
        for (i in 0 until size){
            val commonBit = if (findCommonBit(init, i, true)) '1' else '0'
            init = init.filter { it[i] == commonBit }
            if (init.size <= 1)
                break
        }
        val oxygenRating = init[0].toInt(2)

        init = input
        for (i in 0 until size){
            val commonBit = if (findCommonBit(init, i, false)) '1' else '0'
            init = init.filter { it[i] == commonBit }
            if (init.size <= 1)
                break
        }
        val co2Rating = init[0].toInt(2)

        return oxygenRating * co2Rating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
