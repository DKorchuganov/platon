package com.jvmlab.platon.wheel

import com.jvmlab.platon.cli.BooleanOption
import com.jvmlab.platon.cli.Parser

fun main(args: Array<String>) {

    val parser = try {
        Parser(
            args, listOf(
                BooleanOption('h',"help", "print this help"),
                BooleanOption('s',"silent", "silent mode: don't print prime numbers"),
                BooleanOption('P', "parallel", "perform calculations in parallel")
            )
        )
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    }

    if (parser.getBooleanOption('h')) {
        parser.printOptions()
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
    println("Start searching prime numbers above 5 and below ${size.toLong() * 30}")

    val silent = parser.getBooleanOption('s')
    val sieve: Sieve =
        if (parser.getBooleanOption('P'))
            ParallelSieve(size)
        else
            BasicSieve(size)

    var nextPrime = true

    while (nextPrime) {
        if (!silent) println(sieve.currentPrime)
        sieve.removeComposite()
        nextPrime = sieve.nextPrime()
    }

    println("\n${sieve.count} prime numbers are found\n")
    println("${sieve.removeCompositeDuration.inWholeMilliseconds} ms to remove composite numbers")
}