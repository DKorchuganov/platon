package com.jvmlab.platon.eratosthenes

class PalindromicPrime : PrimeType, AnyPrime() {
    override val name = "palindromic prime"

    override fun process(currentPrime: Int) {
        val digits = toDigits(currentPrime)
        for (index in 0 .. digits.lastIndex / 2) {
            if (digits[index] != digits[digits.lastIndex - index]) return
        }
        println("$currentPrime is a palindromic prime")
        count++
    }

}