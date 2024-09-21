package com.jvmlab.platon.eratosthenes

import kotlin.math.sqrt


fun main(args: Array<String>) {
  val printPrimes: Boolean = if (args.isNotEmpty()) {
    args[0] != "-s"
  } else {
    true
  }

  var input = ""

  while (!((input.isNotEmpty())&&(input.all { it.isDigit() }))) {
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

  val maxFind = sqrt(size.toDouble())
  var twins = 0
  var count = 0
  while (currentPrime != null) {
    count++
    if (sieve[currentPrime - 2]) {
      println("${currentPrime - 2} and $currentPrime are twin primes")
      twins++
    }
    val divisibles = if (currentPrime < maxFind) {
      findDivisibles(currentPrime, sieve)
    } else 0
    if (printPrimes) {
      println("$currentPrime: $divisibles")
    }
    currentPrime = nextPrime(currentPrime, sieve)
  }
  if (count == 1) {
    println("1 prime number was found")
  } else {
    println("$count prime numbers were found")
  }
  if (twins == 1) {
    println("1 twin prime was found")
  } else {
    println("$twins twin primes were found")
  }
  println()
}