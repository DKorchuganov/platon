package com.jvmlab.platon.eratosthenes

class FactorialPrime : PrimeType, AnyPrime() {
    override val name = "factorial prime"

    override fun process(currentPrime: Int): Boolean {
        if (isFactorial(currentPrime - 1) || isFactorial(currentPrime + 1)) {
            count++
            return true
        }
        return false
    }

    private fun isFactorial(number: Int): Boolean {
        var divider = 1
        var divisible = number

        while (divisible != divider) {
            if (divisible % divider != 0) return false
            divisible /= divider
            divider++
        }
        return true
    }

}