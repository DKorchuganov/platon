package com.jvmlab.platon

fun selectLanguage(): String {
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

    return lang
}