package com.jvmlab.platon.wheel

import com.jvmlab.platon.cli.BooleanOption
import com.jvmlab.platon.cli.Parser

fun main(args: Array<String>) {

    val parser = try {
        Parser(
            args, listOf(
                BooleanOption('h',"help", "print this help"),
                BooleanOption('s',"silent", "silent mode: don't print prime numbers",
                    true),
            )
        )
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    }

    if (parser.getBooleanOption('h')) {
        parser.printOptions()
        return
    }
}