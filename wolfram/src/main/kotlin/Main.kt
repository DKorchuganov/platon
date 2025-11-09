package com.jvmlab.platon.wolfram

import com.jvmlab.platon.cli.BooleanOption
import com.jvmlab.platon.cli.Parser


fun main(args: Array<String>) {

    val parser = try {
        Parser(
            args, listOf(
                BooleanOption('h',"help", "print this help")
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

    var input = if (parser.params.isNotEmpty()) parser.params[0] else ""

    while (!((input.isNotEmpty()) && (input.all { it.isDigit() }))) {
        print("Enter number from 0 to 255: ")
        input = readln().trim()

        if (input.length > 3) {
            println()
            println("Too many characters: ${input.length}")
            input = ""
        }
    }

    val ruleCode = input.toInt()
    if (ruleCode > 255) {
        println()
        println("The number should be less than 256!!!")
        return
    }

}
