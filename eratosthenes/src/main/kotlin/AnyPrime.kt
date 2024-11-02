package com.jvmlab.platon.eratosthenes

abstract class AnyPrime {
    protected var count = 0
    protected abstract val name: String

    fun printCount() {
        if (count == 1) {
            println("1 $name was found")
        } else {
            println("$count ${name}s were found")
        }
    }
}