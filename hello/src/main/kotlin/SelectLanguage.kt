package com.jvmlab.platon

fun selectLanguage(): String {
    var lang = ""

    val select = listOf(
        "Select your language",
        "Выберете ваш язык"
    )

    val languages = listOf(
        "English",
        "русский"
    )

    while ((lang != "1") and (lang != "2")) {
        select.forEach {
            println(it)
        }
        languages.forEachIndexed { index, language ->
            val count = index + 1
            println("$count - $language")
        }

        print("Your choice / Ваш выбор:")
        lang = readln()
        println()
        if ( ((lang != "1") and (lang != "2")) ) {
            println("$lang: unknown language / неизвестный язык!")
        }
    }

    return lang
}