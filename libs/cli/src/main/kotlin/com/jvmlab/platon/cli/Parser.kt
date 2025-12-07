package com.jvmlab.platon.cli

import java.lang.IllegalArgumentException

private enum class State {
    START,
    OPTION,
    LONG_NAME,
    SHORT_NAME,
    PARAMETER
}

class Parser(
    args: Array<String>,
    val booleanOptions: List<BooleanOption>,
    val stringOptions: List<StringOption> = listOf()
) {

    private val booleanOptionsByShortName = mutableMapOf<Char, BooleanOption>()
    private val booleanOptionsByLongName = mutableMapOf<String, BooleanOption>()
    private val booleanByOption = mutableMapOf<BooleanOption, Boolean>()

    private val stringOptionsByShortName = mutableMapOf<Char, StringOption>()
    private val stringOptionsByLongName = mutableMapOf<String, StringOption>()
    private val stringByOption = mutableMapOf<StringOption, String>()
    private val mutableParams = mutableListOf<String>()
    val params
        get() = mutableParams.toList()

    init {
        booleanOptions.forEach {
            if (it.shortName != null) {
                booleanOptionsByShortName[it.shortName] = it
            }
            if (it.longName.isNotEmpty()) {
                booleanOptionsByLongName[it.longName] = it
            }
        }
        stringOptions.forEach {
            if (it.shortName != null) {
                stringOptionsByShortName[it.shortName] = it
            }
            if (it.longName.isNotEmpty()) {
                stringOptionsByLongName[it.longName] = it
            }
        }
        args.forEach {
            println(it)
        }

        args.forEach(::parseArg)
    }

    private var initialState = State.START
    private var lastStringOption: StringOption? = null

    private fun parseArg(arg: String) {

        var state = initialState
        var index = 0

        while (index < arg.length) {
            when(state) {
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
                    val booleanOption = booleanOptionsByLongName[longName]
                    val stringOption = stringOptionsByLongName[longName]
                    if (booleanOption != null) {
                        booleanByOption[booleanOption] = ! booleanOption.valueIfAbsent
                    } else if (stringOption != null) {
                        initialState = State.PARAMETER
                        lastStringOption = stringOption
                    } else throw IllegalArgumentException("Unknown option: $longName")
                    return
                }

                State.SHORT_NAME -> {
                    val shortName = arg[index]
                    val booleanOption = booleanOptionsByShortName[shortName]
                    val stringOption = stringOptionsByShortName[shortName]
                    if (booleanOption != null) {
                        booleanByOption[booleanOption] = ! booleanOption.valueIfAbsent
                    } else if (stringOption != null) {
                        initialState = State.PARAMETER
                        lastStringOption = stringOption
                    } else throw IllegalArgumentException("Unknown option: $shortName")
                    index++
                }

                State.PARAMETER -> {
                    lastStringOption?.let {
                        stringByOption[it] = arg
                    } ?: mutableParams.add(arg)
                    lastStringOption = null
                    initialState = State.START
                    return
                }

            }
        }
    }

    fun getBooleanOption(shortName: Char): Boolean {
        val booleanOption = booleanOptionsByShortName[shortName]
        if (booleanOption != null) return booleanByOption[booleanOption] ?: booleanOption.valueIfAbsent
        throw IllegalArgumentException("Undefined option: $shortName")
    }

    fun getBooleanOption(longName: String): Boolean {
        val booleanOption = booleanOptionsByLongName[longName]
        if (booleanOption != null) return booleanByOption[booleanOption] ?: booleanOption.valueIfAbsent
        throw IllegalArgumentException("Undefined option: $longName")
    }

    fun printOptions() = booleanOptions.forEach {
        if (it.shortName != null) print("-${it.shortName}")
        if (it.shortName != null && it.longName != "") print(", ")
        if (it.longName != "") print("--${it.longName}")
        println(": ${it.description}")
    }
}