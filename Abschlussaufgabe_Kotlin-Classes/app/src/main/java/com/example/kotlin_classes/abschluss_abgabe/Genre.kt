package com.example.kotlin_classes.abschluss_abgabe

enum class Genre(private val description: String) {
    FICTION("Fictional stories and novels"),
    NON_FICTION("Non-fictional books based on facts"),
    SCIENCE("Books about science and scientific discoveries"),
    HISTORY("Books about historical events and figures"),
    CHILDREN("Books intended for children");

    fun printDescription() {
        println(description)
    }
}
