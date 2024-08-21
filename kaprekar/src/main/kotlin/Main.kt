package com.jvmlab.platon.kaprekar


fun main() {

    var input = ""

    while (!((input.length == 4)&&(input.all { it.isDigit() }))) {
        print("Enter 4-digit number: ")
        input = readln()
        if (input.all { it == input[0] }) {
            println()
            println("All characters should not be the same!")
            input = ""
        }
    }

    var result = input.toInt()
    var oldResult = 0

    while (result != oldResult) {
        oldResult = result
        val list = result.toString().padStart(4, '0').toList()
        val small = list.sorted().joinToString("").toInt()
        val big = list.sortedDescending().joinToString("").toInt()
        result = big - small
        println("$big - $small = $result")
    }

}