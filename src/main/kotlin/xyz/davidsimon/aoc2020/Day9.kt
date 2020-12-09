package xyz.davidsimon.aoc2020

class Day9 : Solution {

    override val inputFileName: String = "day9.txt"

    override fun printSolution() {
        val numbers = readInputFile()

        val invalid = findFirstInvalid(numbers)!!
        println(invalid)

        println(findWeakness(numbers, invalid))
    }

    private fun findWeakness(numbers: List<Long>, target: Long): Long {
        var fromIndex = 0
        var toIndex = 1
        var currentSum = numbers[0] + numbers[1]

        while(currentSum != target) {
            if(currentSum < target) {
                ++toIndex
                currentSum += numbers[toIndex]
            }
            if(currentSum > target) {
                currentSum -= numbers[fromIndex]
                ++fromIndex
            }
        }

        val subList = numbers.subList(fromIndex, toIndex + 1)
        return subList.minOrNull()!! + subList.maxOrNull()!!
    }

    private fun findFirstInvalid(numbers: List<Long>): Long? {
        for(i in 25..numbers.lastIndex) {
            val valid = isValid(numbers.subList(i - 25, i), numbers[i])
            if(!valid) {
                return numbers[i]
            }
        }

        return null
    }

    private fun isValid(numbers: List<Long>, target: Long): Boolean {
        val combinations = generateCombinations(numbers)
        for(combination in combinations) {
            if(combination.first + combination.second == target) {
                return true
            }
        }

        return false
    }

    private fun generateCombinations(numbers: List<Long>): List<Pair<Long, Long>> {
        return numbers.flatMap { first ->
            numbers.map { second ->
                Pair(first, second)
            }
        }.filter { it.first != it.second }
    }

    private fun readInputFile(): List<Long> {
        return readInputLines().map { line ->
            line.toLong()
        }
    }
}