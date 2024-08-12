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

    val unknowns = listOf(
        "unknown language",
        "неизвестный язык"
    )

    while ((lang != "1") and (lang != "2")) {
        printColumn(select)

        printColumn(
            languages.mapIndexed { index, s ->
                "${index + 1} - $s"
            }
        )

        printSeparated(choices, " / ", ": ")

        lang = readln()
        println()

        if (((lang != "1") and (lang != "2"))) {
            print("$lang: ")
            printSeparated(unknowns, " / ", "!\n")
        }
    }

    return lang
}