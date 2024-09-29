package com.jvmlab.platon.eratosthenes

import kotlin.math.sqrt


fun main(args: Array<String>) {
    val printPrimes = !booleanFlag('s', args)
    val findTwins = booleanFlag('t', args)
    val findBalanced = booleanFlag('b', args)

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
    println(toDigits(size))
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
    var countBalanced = 0

    while (currentPrime != null) {
        countPrimes++

        if (findBalanced && (lastPrime * 2 == lastLastPrime + currentPrime)) {
            println("$lastPrime is a balanced prime (between $lastLastPrime and $currentPrime)")
            countBalanced++
        }

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
        lastLastPrime = lastPrime
        lastPrime = currentPrime
        currentPrime = nextPrime(currentPrime, sieve)
    }
    println()

    if (countPrimes == 1) {
        println("1 prime number was found")
    } else {
        println("$countPrimes prime numbers were found")
    }
    if (findBalanced) {
        if (countBalanced == 1) {
            println("1 balanced prime was found")
        } else {
            println("$countBalanced balanced primes were found")
        }
    }
    if (findTwins) {
        if (countTwins == 1) {
            println("1 twin prime was found")
        } else {
            println("$countTwins twin primes were found")
        }
    }
    println()
}