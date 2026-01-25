package com.jvmlab.platon.argozavr

import java.lang.IllegalArgumentException


private enum class State {
    START,
    OPTION,
    LONG_NAME,
    SHORT_NAME,
    PARAMETER
}


class Parser {
    private val optionsByShortName = mutableMapOf<Char, AnyOption<*>>()
    private val optionsByLongName = mutableMapOf<String, AnyOption<*>>()
    private var lastOption: AnyOption<*>? = null

    private val mutableParams = mutableListOf<String>()
    val params
        get() = mutableParams.toList()

    fun stringOption(shortName: Char, longName: String, description: String): StringOption {
        val option = StringOption(shortName, longName, description)
        optionsByShortName[shortName] = option
        optionsByLongName[longName] = option
        return option
    }


    fun stringOption(longName: String, description: String): StringOption {
        val option = StringOption(null, longName, description)
        optionsByLongName[longName] = option
        return option
    }


    fun stringOption(shortName: Char, description: String): StringOption {
        val option = StringOption(shortName, "", description)
        optionsByShortName[shortName] = option
        return option
    }


    fun booleanOption(
        shortName: Char,
        longName: String,
        description: String,
        valueIfAbsent: Boolean = false
    ): BooleanOption {
        val option = BooleanOption(shortName, longName, description, valueIfAbsent)
        optionsByShortName[shortName] = option
        optionsByLongName[longName] = option
        return option
    }

    fun intOption(
        shortName: Char,
        longName: String,
        description: String,
        valueIfAbsent: Int = 0
    ): IntOption {
        val option = IntOption(shortName, longName, description, valueIfAbsent)
        optionsByShortName[shortName] = option
        optionsByLongName[longName] = option
        return option
    }

    private var initialState = State.START

    fun parse(args: Array<String>) = args.forEach(::parseArg)


    private fun parseArg(arg: String) {

        var state = initialState
        var index = 0

        while (index < arg.length) {
            when (state) {
                State.START -> {
                    if (arg[index] == '-') {
                        index++
                        state = State.OPTION
                    } else {
                        state = State.PARAMETER
                    }
                }

                State.OPTION -> {
                    if (arg[index] == '-') {
                        index++
                        state = State.LONG_NAME
                    } else state = State.SHORT_NAME
                }

                State.LONG_NAME -> {
                    val longName = arg.substring(index)
                    val option = optionsByLongName[longName] ?:
                                 throw IllegalArgumentException("Unknown option: $longName")
                    when (option) {
                        is BooleanOption -> option.value = !option.valueIfAbsent
                        is StringOption, is IntOption -> {
                            initialState = State.PARAMETER
                            lastOption = option
                        }
                    }
                    return
                }

                State.SHORT_NAME -> {
                    val shortName = arg[index]
                    val option = optionsByShortName[shortName] ?:
                                 throw IllegalArgumentException("Unknown option: $shortName")
                    when (option) {
                        is BooleanOption -> option.value = !option.valueIfAbsent
                        is StringOption, is IntOption -> {
                            initialState = State.PARAMETER
                            lastOption = option
                        }
                    }
                    index++
                }

                State.PARAMETER -> {
                    lastOption?.let {
                       when (it) {
                           is StringOption -> it.value = arg
                           is IntOption -> it.value = arg.toInt()
                           is BooleanOption -> throw IllegalStateException("Boolean options are not allowed here")
                       }
                    } ?: mutableParams.add(arg)
                    lastOption = null
                    initialState = State.START
                    return
                }
            }
        }
    }
}