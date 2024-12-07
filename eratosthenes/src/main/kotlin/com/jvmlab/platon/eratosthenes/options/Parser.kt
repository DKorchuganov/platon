package com.jvmlab.platon.eratosthenes.options

import java.lang.IllegalArgumentException

class Parser(args: Array<String>, val booleanOptions: List<BooleanOption>) {
    private val optionString = args.filter {
        it[0] == '-'
    }.joinToString("") {
        it.drop(1)
    }

    private val booleanOptionsByShortName = mutableMapOf<Char, BooleanOption>()
    private val booleanByShortName = mutableMapOf<Char, Boolean>()

    init {
        booleanOptions.forEach {
            if (it.shortName != null) {
                booleanOptionsByShortName[it.shortName] = it
            }
        }

        optionString.forEach {
            if (booleanOptionsByShortName[it] != null) {
                booleanByShortName[it] = ! booleanOptionsByShortName[it]!!.valueIfAbsent
            } else {
                throw IllegalArgumentException("Unknown option: $it")
            }
        }
    }

    fun getBooleanOption(shortName: Char): Boolean {
        if (booleanByShortName[shortName] != null) return booleanByShortName[shortName]!!
        return booleanOptionsByShortName[shortName]!!.valueIfAbsent
    }
}