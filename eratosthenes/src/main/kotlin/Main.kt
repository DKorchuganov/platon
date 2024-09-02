package com.jvmlab.platon.eratosthenes


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

  val sieve = BooleanArray(input.toInt()) {
    true
  }
  sieve[0] = false
  sieve[1] = false
}