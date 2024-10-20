package com.jvmlab.platon.eratosthenes

fun printCounter(counter: Int, name: String) {
    if (counter == 1) {
        println("1 $name was found")
    } else {
        println("$counter ${name}s were found")
    }
}