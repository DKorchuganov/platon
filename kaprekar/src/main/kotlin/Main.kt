package com.jvmlab.platon.kaprekar


fun main() {

    var input = ""

    while (!((input.length == 4)&&(input.all { it.isDigit() }))) {
        print("Enter 4-digit number: ")
        input = readln()
    }

    val small = input.toList().sorted().joinToString("").toInt()
    val big = input.toList().sortedDescending().joinToString("").toInt()
    val result = big - small

    println("$big - $small = $result")

}