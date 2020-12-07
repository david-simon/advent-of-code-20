package xyz.davidsimon.aoc2020

import kotlin.reflect.full.primaryConstructor

fun main(args: Array<String>) {

    if(args.size == 1) {
        runSolution(args[0].toInt())

    } else {
        for(i in 1 until 31) {
            runSolution(i)
        }
    }
}

fun runSolution(day: Int) {
    try {
        val clazz = Class.forName("xyz.davidsimon.aoc2020.Day$day").kotlin
        val solution = clazz.primaryConstructor?.call() as Solution?

        println("### Day $day ###")
        solution?.printSolution()
        println()
    } catch(e: ClassNotFoundException) {
    }

}