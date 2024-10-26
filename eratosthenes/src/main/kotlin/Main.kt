package com.jvmlab.platon.eratosthenes

import kotlin.math.sqrt


fun main(args: Array<String>) {
    val printPrimes = !booleanFlag('s', args)
    val findTwins = booleanFlag('t', args)
    val findBalanced = booleanFlag('b', args)
    val findHappy = booleanFlag('H', args)

    var input = ""

    while (!((input.isNotEmpty()) && (input.all { it.isDigit() }))) {
        print("Enter number (max 9 digits): ")
        input = readln()

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

    var currentPrime: Int? = 2
    var lastPrime = 0
    var lastLastPrime = 0

    val maxFind = sqrt(size.toDouble())
    var countTwins = 0
    var countPrimes = 0

    val balancedPrime = BalancedPrime()
    val happyPrime = HappyPrime()

    while (currentPrime != null) {
        countPrimes++

        if (findBalanced) balancedPrime.find(lastLastPrime, lastPrime, currentPrime)

        if (findTwins && sieve[currentPrime - 2]) {
            println("${currentPrime - 2} and $currentPrime are twin primes")
            countTwins++
        }

        val divisibles = if (currentPrime < maxFind) {
            findDivisibles(currentPrime, sieve)
        } else 0
        if (printPrimes) {
            println("$currentPrime: $divisibles")
        }

        if (findHappy) happyPrime.find(currentPrime)

        lastLastPrime = lastPrime
        lastPrime = currentPrime
        currentPrime = nextPrime(currentPrime, sieve)
    }
    println()

    printFound(countPrimes, "prime number")
    if (findBalanced) balancedPrime.printCount()
    if (findTwins) printFound(countTwins, "twin prime")
    if (findHappy) happyPrime.printCount()

    println()
}