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

    val rule = Rule(ruleCode.toUByte())
    rule.show()

    var initialString = if (parser.params.lastIndex > 0) parser.params[1] else ""

    while (initialString.isEmpty()) {
        println("Enter initial string:")
        initialString = readln().trim()
    }

    val initialList = initialString.map {
        if (it == '*') Cell.ALIVE else Cell.DEAD
    }
    val row = Row(rule, initialList)

    println(initialString)
    repeat(100) {
        val list = row.nextList()
        println(list.joinToString(""))
    }
}
