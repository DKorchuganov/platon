package com.jvmlab.platon.eratosthenes

import com.jvmlab.platon.eratosthenes.options.BooleanOption
import com.jvmlab.platon.eratosthenes.options.Parser
import com.jvmlab.platon.eratosthenes.primetypes.*
import kotlin.math.sqrt


fun main(args: Array<String>) {

    val parser = try {
        Parser(
            args, listOf(
                BooleanOption('h',"help", "print this help"),
                BooleanOption('b',"balanced", "find balanced primes"),
                BooleanOption('t',"twin", "find twin primes"),
                BooleanOption('s',"silent", "silent mode: don't print prime numbers",
                    true),
                BooleanOption('H',"happy", "find happy primes"),
                BooleanOption('p', "palindromic", "find palindromic primes"),
                BooleanOption('e', "emirp", "find emirp primes"),
                BooleanOption('f', "factorial", "find factorial primes"),
            )
        )
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    }

    if (parser.getBooleanOption('h')) {
        parser.booleanOptions.forEach {
            if (it.shortName != null) print("-${it.shortName}")
            if (it.shortName != null && it.longName != "") print(", ")
            if (it.longName != "") print("--${it.longName}")
            println(": ${it.description}")
        }
        return
    }

    var input = if (parser.params.isNotEmpty()) parser.params[0] else ""

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

    if (parser.getBooleanOption('b')) primeTypes.add(BalancedPrime())
    if (parser.getBooleanOption('t')) primeTypes.add(TwinPrime(sieve))
    primeTypes.add(PrintPrime(parser.getBooleanOption('s'), sieve, sqrt(size.toDouble())))
    if (parser.getBooleanOption('H')) primeTypes.add(HappyPrime())
    if (parser.getBooleanOption('p')) primeTypes.add(PalindromicPrime())
    if (parser.getBooleanOption('e')) primeTypes.add(EmirpPrime(sieve))
    if (parser.getBooleanOption('f')) primeTypes.add(FactorialPrime())


    var currentPrime: Int? = 2

    while (currentPrime != null) {
        primeTypes.forEach {
            it.processAndPrint(currentPrime!!)
        }
        currentPrime = nextPrime(currentPrime, sieve)
    }
    println()

    primeTypes.forEach {
        it.printCount()
    }
    println()
}