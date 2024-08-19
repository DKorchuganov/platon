package com.jvmlab.platon.kaprekar


fun main() {

    var input = ""
    while (!((input.length == 4)&&(input.all { it.isDigit() }))) {
        print("Enter 4-digit number: ")
        input = readln()
    }

}