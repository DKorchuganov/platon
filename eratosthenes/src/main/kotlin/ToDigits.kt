package com.jvmlab.platon.eratosthenes

fun toDigits(number: Int): List<Int> {
    val list = mutableListOf<Int>()
    var currentNumber = number
    while (currentNumber != 0) {
        list.add(currentNumber % 10)
        currentNumber /= 10
    }

    return list.toList()
}