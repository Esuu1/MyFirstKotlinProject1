package activity

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    var studentId: String? = null
    var studentFname: String? = null
    var studentMI: String? = null
    var studentLName: String? = null
    var studentName: String? = null
    var continueAddStudent = true

    val subjects = arrayListOf("ENGLISH", "MATH", "FILIPINO", "SCIENCE")

    while (continueAddStudent) {
        println("Welcome To Library System")

        print("Enter student's ID: ")
        studentId = readLine()?.let { "${it.substring(0, 2)}-${it.substring(2)}" }

        print("Enter student's first name: ")
        studentFname = readLine()?.toUpperCase()

        print("Enter student's Middle Name: ")
        studentMI = readLine()?.toCharArray()?.getOrNull(0)?.toUpperCase()?.toString()

        print("Enter student's last name: ")
        studentLName = readLine()?.toUpperCase()
        studentName = "$studentFname $studentMI. $studentLName"

        print("Enter Department: ")
        val studentDept = readLine()?.toUpperCase()
        println("Enter student's year level:")
        println("1. First year")
        println("2. Second year")
        println("3. Third year")
        println("4. Fourth year")
        print("Enter your choice: ")
        val yearLevelChoice = readLine()?.toIntOrNull()

        val yearLevel = when (yearLevelChoice) {
            1 -> "First year"
            2 -> "Second year"
            3 -> "Third year"
            4 -> "Fourth year"
            else -> "Unknown"
        }

        var continueAddBook = true
        var borrowedBooks = ""

        while (continueAddBook) {
            println("\nMenu:")
            println("1. Add book to borrow")
            println("2. Return book")
            print("Enter your choice: ")
            val bookChoice = readLine()?.toIntOrNull()

            when (bookChoice) {
                1 -> {
                    if (subjects.isEmpty()) {
                        Logger.log.warn { "No Books are present in the Library" }
                    } else {
                        print("Enter book name to borrow: ")
                        var borrowBook = readLine()?.toUpperCase() ?: ""

                        while (!subjects.contains(borrowBook)) {
                            println("Book is not present. Enter another book name.")
                            borrowBook = readLine()?.toUpperCase() ?: ""
                        }

                        borrowedBooks += if (borrowedBooks.isEmpty()) borrowBook else ", $borrowBook"

                        println("Is book '$borrowBook' available: (yes/no)")
                        val isAvailable = readLine()?.toLowerCase() == "yes"

                        if (isAvailable) {
                            println("Book '$borrowBook' is available. Borrowed!")
                        } else {
                            println("Book '$borrowBook' is unavailable.")
                        }
                    }
                }
                2 -> {
                    if (borrowedBooks.isNotEmpty()) {
                        print("Enter book name to return: ")
                        val returnBook = readLine()?.toUpperCase() ?: ""

                        if (borrowedBooks.contains(returnBook)) {
                            borrowedBooks = borrowedBooks.replace(returnBook, "").replace(", ,", ",")
                            println("Book '$returnBook' returned successfully.")
                        } else {
                            println("Book '$returnBook' was not borrowed.")
                        }
                    } else {
                        Logger.log.warn { "No Books Borrowed" }
                    }
                }
                else -> {
                    Logger.log.warn { "Invalid Choice. Enter a vaalid number" }
                }
            }

            println("\nDo you want to add/return another book? (yes/no)")
            continueAddBook = readLine()?.toLowerCase() == "yes"
        }

        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))

        Logger.log.info { "Data has been successfully saved." }
        println("\nStudent Details:")
        println("Student ID: $studentId, Student Name: $studentName, Department: $studentDept, Year Level: $yearLevel, Date: $formattedDate")



        if (borrowedBooks.isNotEmpty()) {
            println("Borrowed Books: $borrowedBooks")
        } else {
            println("No books borrowed.")
        }

        println("\nDo you want to add another student to borrow books? (yes/no)")
        continueAddStudent = readLine()?.toLowerCase() == "yes"
    }

    Logger.log.info { "Exiting the Library System. Thank You!" }
}
