package xyz.davidsimon.aoc2020

import java.util.*

class Day6 : Solution {

    override val inputFileName: String = "day6.txt"

    override fun printSolution() {
        println(readInputFile(false).sum())
        println(readInputFile(true).sum())
    }


    private fun readInputFile(partTwo: Boolean): List<Int> {
        val file = object{}.javaClass.getResourceAsStream("/day6.txt")
        return file.bufferedReader().useLines {
            val groups = mutableListOf<BitSet>()

            for (line in it) {
                if(line.isEmpty() || groups.isEmpty()) {
                    val group = BitSet(26)
                    if(partTwo) {
                        group.set(0, 26, true)
                    }
                    groups.add(group)
                    continue
                }

                val person = BitSet(26)
                for(char in line) {
                    person[char.toInt() - 97] = true
                }

                if(partTwo) {
                    groups.last().and(person)
                } else {
                    groups.last().or(person)
                }
            }

            groups.map { group -> group.cardinality() }
        }
    }
}