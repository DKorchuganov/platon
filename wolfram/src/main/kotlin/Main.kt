package com.jvmlab.platon.wolfram

import com.jvmlab.platon.cli.BooleanOption
import com.jvmlab.platon.cli.Parser
import com.jvmlab.platon.cli.StringOption


fun main(args: Array<String>) {

    val parser = try {
        Parser(
            args,
            listOf(
                BooleanOption('h',"help", "print this help"),
                BooleanOption('l', "loop", "treat row as a loop"),
                BooleanOption('s', "stop", "stop at the end of the row")
            ),
            listOf(
                StringOption('i', "initial-string", "set initial string")
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

    var initialString = parser.getStringOption('i') ?: ""

    while (initialString.isEmpty()) {
        println("Enter initial string:")
        initialString = readln().trim()
    }

    val initialList = initialString.map {
        if (it == '*') Cell.ALIVE else Cell.DEAD
    }

    val loop = parser.getBooleanOption('l')
    val row = Row(rule, initialList, loop)

    println(initialString)
    val maxSteps = 100
    var count = 0
    val stop = parser.getBooleanOption('s')
    var list = initialList
    while (
        (count < maxSteps) &&
        (!stop || (list[0] == Cell.DEAD && list[list.lastIndex] == Cell.DEAD))
    ) {
        list = row.nextList()
        println(list.joinToString(""))
        count++
    }
}
