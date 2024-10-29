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

    val balancedPrime = BalancedPrime()
    val happyPrime = HappyPrime()
    val twinPrime = TwinPrime(sieve)
    val printPrime = PrintPrime(printPrimes, sieve, sqrt(size.toDouble()))

    while (currentPrime != null) {

        if (findBalanced) balancedPrime.process(currentPrime)

        if (findTwins) twinPrime.process(currentPrime)

        printPrime.process(currentPrime)

        if (findHappy) happyPrime.process(currentPrime)

        currentPrime = nextPrime(currentPrime, sieve)
    }
    println()

    printPrime.printCount()
    if (findBalanced) balancedPrime.printCount()
    if (findTwins) twinPrime.printCount()
    if (findHappy) happyPrime.printCount()

    println()
}