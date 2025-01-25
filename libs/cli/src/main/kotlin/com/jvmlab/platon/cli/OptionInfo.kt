package com.jvmlab.platon.cli

interface OptionInfo {
    val shortName: Char?
    val longName: String
    val description: String
}