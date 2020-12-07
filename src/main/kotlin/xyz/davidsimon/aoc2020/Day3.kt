package xyz.davidsimon.aoc2020

class Day3 : Solution {
    override val inputFileName: String = "day3.txt"

    override fun printSolution() {
        val pattern = readInputFile()
        val map = TobogganMap(pattern)

        val result = listOf(
            Pair(1, 1),
            Pair(1, 3),
            Pair(1, 5),
            Pair(1, 7),
            Pair(2, 1)
        ).fold(1L) { acc, slope ->
            acc * traverseMap(map, slope.first, slope.second)
        }

        println(result)
    }

    private fun traverseMap(map: TobogganMap, slopeH: Int, slopeW: Int): Int {
        var treeCount = 0

        var h = 0
        var w = 0

        while(h < map.height) {
            if(map[h, w]) {
                ++treeCount
            }

            h += slopeH
            w += slopeW
        }

        return treeCount
    }

    private fun readInputFile(): List<List<Boolean>> {
        return readInputLines().map { line ->
            line.map { char -> char == '#' }
        }
    }

    class TobogganMap(private val pattern: List<List<Boolean>>) {
        val height = pattern.size
        private val width = pattern.getOrNull(0)?.size ?: 0

        operator fun get(h: Int, w: Int): Boolean {
            return pattern[h][w % width]
        }
    }
}