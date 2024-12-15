package com.jvmlab.platon.eratosthenes.options

import java.lang.IllegalArgumentException

private enum class State {
    START,
    OPTION,
    LONG_NAME,
    SHORT_NAME
}

class Parser(args: Array<String>, val booleanOptions: List<BooleanOption>) {

    private val booleanOptionsByShortName = mutableMapOf<Char, BooleanOption>()
    private val booleanOptionsByLongName = mutableMapOf<String, BooleanOption>()
    private val booleanByOption = mutableMapOf<BooleanOption, Boolean>()

    init {
        booleanOptions.forEach {
            if (it.shortName != null) {
                booleanOptionsByShortName[it.shortName] = it
            }
            if (it.longName.isNotEmpty()) {
                booleanOptionsByLongName[it.longName] = it
            }
        }

        args.forEach(::parseArg)
    }

    private fun parseArg(arg: String) {
        var state = State.START
        var index = 0

        while (index < arg.length) {
            when(state) {
                State.START -> {
                    if (arg[index] == '-') {
                        index++
                        state = State.OPTION
                    } else return
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
                    if (booleanOption != null) {
                        booleanByOption[booleanOption] = ! booleanOption.valueIfAbsent
                    } else throw IllegalArgumentException("Unknown option: $longName")
                    return
                }
                State.SHORT_NAME -> {
                    val shortName = arg[index]
                    val booleanOption = booleanOptionsByShortName[shortName]
                    if (booleanOption != null) {
                        booleanByOption[booleanOption] = ! booleanOption.valueIfAbsent
                    } else throw IllegalArgumentException("Unknown option: $shortName")
                    index++
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

}