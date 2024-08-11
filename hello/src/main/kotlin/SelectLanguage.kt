@file:Suppress("KotlinConstantConditions")

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

    val choices = listOf(
        "Your choice",
        "Ваш выбор"
    )

    while ((lang != "1") and (lang != "2")) {
        select.forEach {
            println(it)
        }

        languages.forEachIndexed { index, language ->
            val count = index + 1
            println("$count - $language")
        }

        for (index in 0..(choices.size - 2)) {
            val choice = choices[index]
            print("$choice / ")
        }
        print("${choices[choices.size - 1]}: ")

        lang = readln()
        println()

        if ( ((lang != "1") and (lang != "2")) ) {
            println("$lang: unknown language / неизвестный язык!")
        }
    }

    return lang
}