package xyz.davidsimon.aoc2020

class Day7 : Solution {

    override val inputFileName: String = "day7.txt"

    override fun printSolution() {
        val rules = readInputFile()
        println(findContainers("shiny gold", rules).size)
        println(countChildren("shiny gold", rules))
    }

    private fun countChildren(color: String, rules: Map<String, List<Pair<Int, String>>>): Int {
        var count = 0

        for(child in rules[color]!!) {
            count += child.first + child.first * countChildren(child.second, rules)
        }

        return count
    }

    private fun findContainers(color: String, rules: Map<String, List<Pair<Int, String>>>): Set<String> {
        val containers = mutableSetOf<String>()
        for(rule in rules) {
            if(rule.value.map { it.second }.contains(color)) {
                containers.add(rule.key)
                containers.addAll(findContainers(rule.key, rules))
            }
        }

        return containers
    }


    private fun readInputFile(): Map<String, List<Pair<Int, String>>> {
        return readInputLines().associate { line ->
            val (bagName, containsPart) = line.split(" bags contain ")
            val containedBags = containsPart.split(", ").mapNotNull {
                val stripped = it.replace(".", "")
                    .replace(Regex(" bags?"), "")

                if (stripped == "no other") {
                    null
                } else {
                    val match = Regex("(\\d+)? ?(.*)").matchEntire(stripped)
                    Pair(match!!.groups[1]!!.value.toInt(), match.groups[2]!!.value)
                }
            }

            bagName to containedBags
        }
    }
}