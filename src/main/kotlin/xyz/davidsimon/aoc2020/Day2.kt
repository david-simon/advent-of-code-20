package xyz.davidsimon.aoc2020

class Day2 : Solution {
    override val inputFileName: String = "day2.txt"

    override fun printSolution() {
        val inputItems = readInputFile()
        println(countValidItems(inputItems, ::isValidItem1))
        println(countValidItems(inputItems, ::isValidItem2))
    }


    private fun countValidItems(inputItems: List<InputItem>, validator: (InputItem) -> Boolean): Int {
        var validItems = 0

        for(item in inputItems) {
            if(validator(item)) {
                ++validItems
            }
        }

        return validItems
    }

    private fun isValidItem1(item: InputItem): Boolean {
        var charCount = 0
        for(char in item.password) {
            if(char == item.policy.char) {
                ++charCount
            }
        }

        return item.policy.min <= charCount && charCount <= item.policy.max
    }

    private fun isValidItem2(item: InputItem): Boolean {
        val pos1 = item.password[item.policy.min] == item.policy.char
        val pos2 = item.password[item.policy.max] == item.policy.char

        return pos1 xor pos2
    }

    private fun readInputFile(): List<InputItem> {
        return readInputLines().map { line ->
            val (policyPart, password) = line.split(":")
            val (rangePart, char) = policyPart.split(" ")
            val (min, max) = rangePart.split("-")

            InputItem(
                Policy(min.toInt(), max.toInt(), char[0]),
                password
            )
        }
    }

    data class InputItem(
        val policy: Policy,
        val password: String
    )

    data class Policy(
        val min: Int,
        val max: Int,
        val char: Char
    )
}