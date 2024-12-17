package com.example.kotlin_classes.abschluss_abgabe


class Library {
    private val books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun searchBook(query: String): List<Book> {
        return books.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.author.contains(query, ignoreCase = true)
        }
    }

    fun displayBookStatuses() {
        books.forEach { book ->
            println("${book.title} by ${book.author}: ${book.status.displayStatus()}")
        }
    }

    class LibraryAddress(private val street: String, private val city: String, private val zipCode: String) {
        // Methode, um die Adresse auszugeben
        fun printAddress() {
            println("Library Address: $street, $city, $zipCode")
        }
    }

    inner class LibraryMember(private val name: String, private val memberID: Int) {
        // Methode: Ein Buch ausleihen
        fun checkoutBook(book: Book, dueDate: String) {
            val index = books.indexOf(book)
            if (index != -1 && books[index].status is BookStatus.Available) {
                books[index] = book.copy(status = BookStatus.CheckedOut(dueDate))
                println("$name checked out ${book.title}. Due date: $dueDate.")
            } else {
                println("${book.title} is not available for checkout.")
            }
        }

        fun reserveBook(book: Book) {
            val index = books.indexOf(book)
            if (index != -1 && books[index].status is BookStatus.Available) {
                books[index] = book.copy(status = BookStatus.Reserved(name))
                println("$name reserved ${book.title}.")
            } else {
                println("${book.title} is not available for reservation.")
            }
        }
    }
}

// Die main()-Funktion sollte außerhalb der Library-Klasse stehen
fun main() {
    // Instanz der Bibliothek erstellen
    val library = Library()

    // Adresse der Bibliothek erstellen und ausgeben
    val address = Library.LibraryAddress("Buchstraße 42", "Lesestadt", "54321")
    println("Bibliothek Adresse:")
    address.printAddress()

    // Bücher zur Bibliothek hinzufügen
    library.addBook(Book("1984", "George Orwell", Genre.FICTION, BookStatus.Available))
    library.addBook(Book("A Brief History of Time", "Stephen Hawking", Genre.SCIENCE, BookStatus.Available))
    library.addBook(Book("The Hobbit", "J.R.R. Tolkien", Genre.FICTION, BookStatus.Available))
    library.addBook(Book("Sapiens", "Yuval Noah Harari", Genre.HISTORY, BookStatus.Available))

    // Bücherstatus vor Änderungen anzeigen
    println("\nBücherstatus vor Änderungen:")
    library.displayBookStatuses()

    // Mitglied erstellen
    val member = library.LibraryMember("Max Mustermann", 101)

    // Mitglied leiht ein Buch aus und reserviert ein anderes
    println("\nMax Mustermann leiht '1984' aus und reserviert 'The Hobbit':")
    val bookToCheckout = library.searchBook("1984").first()
    member.checkoutBook(bookToCheckout, "2024-12-31")

    val bookToReserve = library.searchBook("The Hobbit").first()
    member.reserveBook(bookToReserve)

    // Bücherstatus nach Änderungen anzeigen
    println("\nBücherstatus nach Änderungen:")
    library.displayBookStatuses()
}
