package com.jvmlab.platon


fun printSeparated(list: List<String>, separator: String, suffix: String) {
    list.dropLast(1).forEach {
        print(it + separator)
    }
    print(list.last() + suffix)
}