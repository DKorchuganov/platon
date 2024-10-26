package com.jvmlab.platon.eratosthenes

fun printFound(number: Int, name: String) {
    if (number == 1) {
        println("1 $name was found")
    } else {
        println("$number ${name}s were found")
    }
}