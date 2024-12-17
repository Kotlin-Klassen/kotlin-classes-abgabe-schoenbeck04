package com.example.kotlin_classes.abschluss_abgabe

sealed class BookStatus {
    // Das Buch ist verfügbar
    object Available : BookStatus()

    // Das Buch ist ausgeliehen mit einem Rückgabedatum
    data class CheckedOut(val dueDate: String) : BookStatus()

    // Das Buch ist reserviert und zeigt an, von wem
    data class Reserved(val reservedBy: String) : BookStatus()

    // Methode, die den aktuellen Status des Buches in lesbarem Format zurückgibt
    fun displayStatus(): String {
        return when (this) {
            is Available -> "The book is available."
            is CheckedOut -> "The book is checked out. Due date: $dueDate."
            is Reserved -> "The book is reserved by $reservedBy."
        }
    }
}

