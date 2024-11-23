package com.jvmlab.platon.eratosthenes


fun booleanFlag(name: Char, args: Array<String>): Boolean {
    val flags = args.filter {
        it[0] == '-'
    }.joinToString("") {
        it.drop(1)
    }

    return flags.contains(name)
}