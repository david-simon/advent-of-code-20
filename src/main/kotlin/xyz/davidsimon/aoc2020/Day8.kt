package xyz.davidsimon.aoc2020

class Day8 : Solution {

    override val inputFileName: String = "day8.txt"

    override fun printSolution() {
        val instructions = readInputFile()

        println(executeUntilLoop(instructions).first.acc)
        println(executeMutations(instructions).first.acc)
    }

    private fun executeMutations(instructions: List<Instruction>): Pair<ExecutionState, ExecutionState> {
        for(i in instructions.indices) {
            val instruction = instructions[i]
            val newInstruction = when(instruction.operation) {
                Operation.ACC -> continue
                Operation.NOP -> Instruction(Operation.JMP, instruction.argument)
                Operation.JMP -> Instruction(Operation.NOP, instruction.argument)
            }

            val newInstructions = instructions.toMutableList()
            newInstructions[i] = newInstruction
            val res = executeUntilLoop(newInstructions)
            if (res.second.ip >= instructions.size) {
                return res
            }
        }

        return executeUntilLoop(instructions)
    }

    private fun executeUntilLoop(instructions: List<Instruction>): Pair<ExecutionState, ExecutionState> {
        val executedInstructions = mutableSetOf<Int>()

        var state = ExecutionState(0, 0)
        var previousState = ExecutionState(-1, 0)

        while(state.ip < instructions.size && !executedInstructions.contains(state.ip)) {
            executedInstructions.add(state.ip)

            val instruction = instructions[state.ip]
            var nextIp = state.ip + 1
            var nextAcc = state.acc

            when(instruction.operation) {
                Operation.NOP -> {}
                Operation.ACC -> nextAcc += instruction.argument
                Operation.JMP -> nextIp = state.ip + instruction.argument
            }

            previousState = state
            state = ExecutionState(nextIp, nextAcc)

        }

        return Pair(previousState, state)
    }

    private fun readInputFile(): List<Instruction> {
        return readInputLines().map { line ->
            val (operation, argument) = line.split(' ')
            Instruction(Operation.valueOf(operation.toUpperCase()), argument.toInt())
        }
    }

    data class ExecutionState(val ip: Int, val acc: Int)
    data class Instruction(val operation: Operation, val argument: Int)
    enum class Operation {
        NOP,
        ACC,
        JMP
    }
}