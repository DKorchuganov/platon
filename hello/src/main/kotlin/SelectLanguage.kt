@file:Suppress("KotlinConstantConditions")

package com.jvmlab.platon

fun selectLanguage(): String {
    var lang = ""

    val select = listOf(
        "Select your language",
        "Выберите ваш язык",
        "Wählen Sie Ihre Sprache"
    )

    val languages = listOf(
        "English",
        "русский",
        "Deutsch"
    )

    val choices = listOf(
        "Your choice",
        "Ваш выбор",
        "Ihre Wahl"
    )

    val unknowns = listOf(
        "unknown language",
        "неизвестный язык",
        "unbekannte Sprache"
    )

    while (!checkLang(select, lang)) {
        printColumn(select)

        printColumn(
            languages.mapIndexed { index, s ->
                "${index + 1} - $s"
            }
        )

        printSeparated(choices, " / ", ": ")

        lang = readln()
        println()

        if (!checkLang(select, lang)) {
            print("$lang: ")
            printSeparated(unknowns, " / ", "!\n")
        }
    }

    return lang
}