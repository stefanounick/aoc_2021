fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var position = 0
        input.forEach {
            if (it.contains("forward")){
                position += it.split(' ')[1].toInt()
            } else if (it.contains("down")){
                depth += it.split(' ')[1].toInt()
            } else if (it.contains("up")){
                depth -= it.split(' ')[1].toInt()
            }
        }
        return depth * position
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var aim = 0
        var position = 0
        input.forEach {
            if (it.contains("forward")){
                position += it.split(' ')[1].toInt()
                depth += aim  * it.split(' ')[1].toInt()
            } else if (it.contains("down")){
                aim += it.split(' ')[1].toInt()
            } else if (it.contains("up")){
                aim -= it.split(' ')[1].toInt()
            }
        }
        return depth * position
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
