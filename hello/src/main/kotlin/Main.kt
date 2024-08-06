package com.jvmlab.platon


fun main() {
    println("This is my first program")

    var lang = ""
    while ((lang != "1") and (lang != "2")) {
        println("Select your language")
        println("Выберете ваш язык")
        println("1 - English")
        println("2 - русский")
        print("Your choice / Ваш выбор:")
        lang = readln()
        println()
        if ( ((lang != "1") and (lang != "2")) ) {
            println("$lang: unknown language / неизвестный язык!")
        }
    }

    val nameQuestions = listOf(
        "What is your name? ",
        "Как тебя зовут? "
    )
    val langIndex = lang.toInt() - 1
    print(nameQuestions[langIndex])
    val name = readln()

    val greetings = listOf(
        "Hello $name!",
        "Привет $name!"
    )
    println(greetings[langIndex])

}