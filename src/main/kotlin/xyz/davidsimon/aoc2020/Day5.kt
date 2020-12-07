package xyz.davidsimon.aoc2020

class Day5 : Solution{

    override val inputFileName: String = "day5.txt"

    override fun printSolution() {
        val occupiedSeats = readInputFile().sorted()
        println(occupiedSeats.last())

        var previousSeat = occupiedSeats.first() - 1
        for(seat in occupiedSeats) {
            if(seat - previousSeat > 1) {
                println(seat - 1)
                break
            }
            previousSeat = seat
        }
    }

    private fun generateAllSeats(): Set<Long> {
        val res = mutableSetOf<Long>()

        for(row in 0L until 128L) {
            for(col in 0L until 64L) {
                res.add(row * 8 + col)
            }
        }

        return res
    }

    private fun readInputFile(): Set<Long> {
        val file = object{}.javaClass.getResourceAsStream("/day5.txt")
        return file.bufferedReader().useLines {
            it.map { line ->
                val row = line.substring(0, 7)
                    .replace('B', '1')
                    .replace('F', '0')
                    .toLong(2)

                val col = line.substring(7)
                    .replace('R', '1')
                    .replace('L', '0')
                    .toLong(2)

                row * 8 + col
            }.toSet()
        }
    }
}