package com.jvmlab.platon

fun selectLanguage(): String {
    var lang = ""

    val select = listOf(
        "Select your language",
        "Выберете ваш язык"
    )
    while ((lang != "1") and (lang != "2")) {
        select.forEach {
            println(it)
        }
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