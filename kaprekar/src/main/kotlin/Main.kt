package com.jvmlab.platon.kaprekar


fun main() {

    var input = ""

    while (!((input.isNotEmpty())&&(input.all { it.isDigit() }))) {
        print("Enter number (max 18 digits): ")
        input = readln()
        if ((input.length > 1)&&input.all { it == input[0] }) {
            println()
            println("All characters should not be the same!")
            input = ""
        }
        if (input.length > 18) {
            println()
            println("Too many characters: ${input.length}")
            input = ""
        }
    }

    var result = input.toLong()
    val oldResult: MutableList<Long> = mutableListOf()

    while (!oldResult.contains(result)) {
        oldResult.add(result)
        val list = result.toString().padStart(input.length, '0').toList()
        val small = list.sorted().joinToString("").toLong()
        val big = list.sortedDescending().joinToString("").toLong()
        result = big - small
        println("$big - $small = $result")
    }

    val resultIndex = oldResult.indexOf(result)
    if (resultIndex == oldResult.lastIndex) {
        println("Constant point: $result")
    } else {
        println("Cycle: ")
        val cycle = oldResult.subList(resultIndex, oldResult.size).joinToString(" -> ")
        println("$cycle -> $result")
    }

}