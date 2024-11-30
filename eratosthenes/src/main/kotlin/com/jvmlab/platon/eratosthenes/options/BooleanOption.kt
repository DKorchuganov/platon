package com.jvmlab.platon.eratosthenes.options

class BooleanOption(shortName: Char?, longName: String, description: String, val valueIfAbsent: Boolean = false) :
    AnyOption(shortName, longName, description) {

    constructor(longName: String, description: String, valueIfAbsent: Boolean = false) :
            this(null, longName, description, valueIfAbsent)

    constructor(shortName: Char?, description: String, valueIfAbsent: Boolean = false) :
            this(shortName, "", description, valueIfAbsent)
}