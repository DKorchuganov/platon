package com.jvmlab.platon.argozavr

class Parser {
    private val optionsByShortName = mutableMapOf<Char, AnyOption<*>>()
    private val optionsByLongName = mutableMapOf<String, AnyOption<*>>()


    fun stringOption(shortName: Char, longName: String, description: String): AnyOption<*> {
        val option = StringOption(shortName, longName, description)
        optionsByShortName[shortName] = option
        optionsByLongName[longName] = option
        return option
    }


    fun stringOption(longName: String, description: String): AnyOption<*> {
        val option = StringOption(null, longName, description)
        optionsByLongName[longName] = option
        return option
    }
}