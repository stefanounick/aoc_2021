fun main() {
    class Board {
        var b = Array(5) { IntArray(5) }
        var marked = Array(5) { BooleanArray(5) { false } }
        var bingo = false

        fun parseBoard(input: List<String>){
            input.forEachIndexed{index, s ->
                s.trim().split(Regex("\\s+")).map { it.trim().toInt() }.forEachIndexed { i, n ->
                    b[index][i] = n
                }
            }
        }

        fun markNumber(number: Int) {
            for (i in 0..4){
                for (j in 0..4){
                    if (b[i][j] == number)
                        marked[i][j] = true
                }
            }
        }

        fun hasBingo(): Boolean {
            for (i in 0..4){
                var bingo = true
                for (j in 0..4){
                    if (!marked[i][j])
                        bingo = false
                }
                if (bingo) {
                    this.bingo = true
                    return true
                }
            }

            for (i in 0..4){
                var bingo = true
                for (j in 0..4){
                    if (!marked[j][i])
                        bingo = false
                }
                if (bingo) {
                    this.bingo = true
                    return true
                }
            }

            return false
        }

        fun getScore(): Int {
            var sum = 0
            for (i in 0..4){
                for (j in 0..4){
                    if (!marked[i][j])
                        sum += b[i][j]
                }
            }
            return sum
        }
    }

    fun part1(input: List<String>): Int {
        val numbers = input[0].split(',').map { it.toInt() }
        val boards = mutableListOf<Board>()

        for (i in 1..input.size - 6 step 6){
            val board = Board()
            board.parseBoard(input.subList(i + 1, i + 6))
            boards.add(board)
        }

        numbers.forEach { number ->
            boards.forEach {
                it.markNumber(number)
                if (it.hasBingo())
                    return it.getScore() * number
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(',').map { it.toInt() }
        val boards = mutableListOf<Board>()
        var lastBoard: Board? = null
        var lastNumber = 0

        for (i in 1..input.size - 6 step 6){
            val board = Board()
            board.parseBoard(input.subList(i + 1, i + 6))
            boards.add(board)
        }

        numbers.forEach { number ->
            boards.forEach {
                if (!it.bingo) {
                    it.markNumber(number)
                    if (it.hasBingo()) {
                        lastBoard = it
                        lastNumber = number
                    }
                }
            }
        }

        return (lastBoard?.getScore() ?: 0) * lastNumber
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}