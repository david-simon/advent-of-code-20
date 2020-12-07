package xyz.davidsimon.aoc2020

class Day4 : Solution {

    override val inputFileName: String = "day4.txt"

    override fun printSolution() {
        val passports = readInputFile()

        println(validatePassports(passports, false))
        println(validatePassports(passports, true))
    }

    private fun validatePassports(passports: List<Map<String, String>>, strict: Boolean): Int {
        var count = 0
        for(passport in passports) {
            if(passport.keys.size == 8 || passport.keys.size == 7 && !passport.containsKey("cid")) {
                if(strict) {
                    val byr = passport["byr"]!!.toInt()
                    if(byr < 1920 || byr > 2002) continue

                    val iyr = passport["iyr"]!!.toInt()
                    if(iyr < 2010 || iyr > 2020) continue

                    val eyr = passport["eyr"]!!.toInt()
                    if(eyr < 2020 || eyr > 2030) continue

                    val hgt = passport["hgt"]!!
                    val unit = hgt.substring(hgt.length - 2)
                    if(unit != "cm" && unit != "in") continue
                    val height = hgt.substring(0, hgt.length - 2).toInt()
                    if(unit == "cm" && (height < 150 || height > 193)) continue
                    if(unit == "in" && (height < 59 || height > 76)) continue

                    if(!Regex("#[0-9a-f]{6}").matches(passport["hcl"]!!)) continue

                    if(!listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(passport["ecl"]!!)) continue

                    if(!Regex("[0-9]{9}").matches(passport["pid"]!!)) continue
                }

                ++count
            }
        }

        return count
    }

    fun readInputFile(): MutableList<MutableMap<String, String>> {
        val passports = mutableListOf<MutableMap<String, String>>()

        for (line in readInputLines()) {
            if(line.isBlank() || passports.isEmpty()) {
                passports.add(mutableMapOf())
            }
            line.isBlank() && continue

            val currentPassport = passports.last()
            for(field in line.split(' ')) {
                val (key, value) = field.split(':')
                currentPassport[key] = value
            }

        }

        return passports
    }
}