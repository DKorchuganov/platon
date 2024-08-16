package com.jvmlab.platon


fun printSeparated(list: List<String>, separator: String, suffix: String) {
    list.dropLast(1).forEach {
        print(it + separator)
    }
    print(list.last() + suffix)
}


fun printColumn(list: List<String>) {
    list.forEach {
        println(it)
    }
}


fun checkLang(list: List<String>, lang: String): Boolean {
    return list.indices.toList().map {
        (it + 1).toString()
    }.contains(lang)
}