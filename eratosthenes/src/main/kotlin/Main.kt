package com.jvmlab.platon.eratosthenes

import kotlin.math.sqrt


fun main(args: Array<String>) {

    var input = ""

    while (!((input.isNotEmpty()) && (input.all { it.isDigit() }))) {
        print("Enter number (max 9 digits): ")
        input = readln().trim()

        if (input.length > 9) {
            println()
            println("Too many characters: ${input.length}")
            input = ""
        }
    }

    val size = input.toInt()
    if (size < 3) {
        println()
        println("The number should be greater than 2!!!")
        return
    }

    val sieve = BooleanArray(size) {
        true
    }
    sieve[0] = false
    sieve[1] = false

    val primeTypes = mutableListOf<PrimeType>()

    if (booleanFlag('b', args)) primeTypes.add(BalancedPrime())
    if (booleanFlag('t', args)) primeTypes.add(TwinPrime(sieve))
    primeTypes.add(PrintPrime(!booleanFlag('s', args), sieve, sqrt(size.toDouble())))
    if (booleanFlag('H', args)) primeTypes.add(HappyPrime())

    var currentPrime: Int? = 2

    while (currentPrime != null) {
        primeTypes.forEach {
            it.process(currentPrime!!)
        }
        currentPrime = nextPrime(currentPrime, sieve)
    }
    println()

    primeTypes.forEach {
        it.printCount()
    }
    println()
}