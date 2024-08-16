package com.jvmlab.platon


fun main() {
    println("This is my first program")
    println()

    val nameQuestions = listOf(
        "What is your name? ",
        "Как тебя зовут? ",
        "Wie heißt du? "
    )
    val langIndex = selectLanguage().toInt() - 1
    print(nameQuestions[langIndex])
    val name = readln()

    val greetings = listOf(
        "Hello $name!",
        "Привет $name!",
        "Hallo $name!"
    )
    println(greetings[langIndex])

}