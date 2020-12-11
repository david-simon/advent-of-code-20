package xyz.davidsimon.aoc2020

import java.lang.Integer.min

class Day10 : Solution {

    override val inputFileName: String = "day10.txt"
    
    override fun printSolution() {
        var numbers = readInputFile()
        numbers = (numbers + 0 + (numbers.maxOrNull()!! + 3)).sorted()

        println(calculateJoltageDifference(numbers))
    }

    private fun calculateJoltageDifference(numbers: List<Int>): Int {
        val sortedList = numbers.sorted()
        return sortedList.zipWithNext().map {
            it.second - it.first
        }.fold(Pair(0, 0)) { acc, diff ->
            when (diff) {
                1 -> Pair(acc.first + 1, acc.second)
                3 -> Pair(acc.first, acc.second + 1)
                else -> acc
            }
        }.let { it.first * it.second }
    }

    private fun readInputFile(): List<Int> {
        return readInputLines().map { line ->
            line.toInt()
        }
    }
}