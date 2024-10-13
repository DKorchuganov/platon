package com.jvmlab.platon.eratosthenes

fun isHappy(number: Int): Boolean {
    var currentNumber = 0
    toDigits(number).forEach {
        currentNumber += it * it
    }
    val pastNumbers = mutableListOf(number)
    while ((currentNumber != 1) && (! pastNumbers.contains(currentNumber))) {
        pastNumbers.add(currentNumber)
        var sum = 0
        toDigits(currentNumber).forEach {
           sum += it * it
        }
        println(currentNumber)
        currentNumber = sum
    }
    println(currentNumber)

    return currentNumber == 1
}