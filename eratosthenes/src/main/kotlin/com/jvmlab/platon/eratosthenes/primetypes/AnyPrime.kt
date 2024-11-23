package com.jvmlab.platon.eratosthenes.primetypes

abstract class AnyPrime : PrimeType {
    protected var count = 0
    protected abstract val name: String

    override fun processAndPrint(currentPrime: Int): Boolean {
        if (process(currentPrime)) {
            println("$currentPrime is a $name")
            return true
        }
        return false
    }

    override fun printCount() {
        if (count == 1) {
            println("1 $name was found")
        } else {
            println("$count ${name}s were found")
        }
    }
}