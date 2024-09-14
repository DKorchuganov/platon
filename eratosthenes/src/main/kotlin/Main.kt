package com.jvmlab.platon.eratosthenes

import kotlin.math.sqrt


fun main() {

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
  var count = 0
  while (currentPrime != null) {
    count++
    val divisibles = if (currentPrime < maxFind) {
      findDivisibles(currentPrime, sieve)
    } else 0
    println("$currentPrime: $divisibles")
    currentPrime = nextPrime(currentPrime, sieve)
  }
  println("$count prime numbers were found")
  println()
}