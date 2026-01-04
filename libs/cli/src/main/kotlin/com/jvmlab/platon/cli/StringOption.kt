package com.jvmlab.platon.cli


class StringOption(shortName: Char?, longName: String, description: String) :
    AnyOption(shortName, longName, description) {

    constructor(longName: String, description: String) : this(null, longName, description)

    constructor(shortName: Char?, description: String) : this(shortName, "", description)
}