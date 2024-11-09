package com.jvmlab.platon.eratosthenes

class HappyPrime : PrimeType, AnyPrime() {

    override val name = "happy prime"

    override fun process(currentPrime: Int): Boolean {
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
            count++
            return true
        }
        return false
    }
}