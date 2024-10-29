package com.jvmlab.platon.eratosthenes

class HappyPrime : PrimeType {
    private var count = 0

    override fun process(currentPrime: Int) {
        var currentNumber = 0
        toDigits(currentPrime).forEach {
            currentNumber += it * it
        }
        val pastNumbers = mutableListOf(currentPrime)
        while ((currentNumber != 1) && (! pastNumbers.contains(currentNumber))) {
            pastNumbers.add(currentNumber)
            var sum = 0
            toDigits(currentNumber).forEach {
                sum += it * it
            }
            currentNumber = sum
        }

        if (currentNumber == 1) {
            println("$currentPrime is a happy prime")
            count++
        }
    }

    override fun printCount() = printFound(count, "happy prime")

}