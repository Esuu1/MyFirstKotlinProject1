import java.time.LocalDate
import java.time.format.DateTimeFormatter

val books = arrayListOf("ENGLISH", "MATH", "FILIPINO", "SCIENCE")
val courses = arrayListOf("BSIT", "BSED", "BSBA", "BSHM", "BEED")

fun main() {
    val borrowers = mutableListOf<Borrower>()

    var continueProgram = true

    while (continueProgram) {
        println("\nBook Borrower System")
        println("[1] Cater Transactions")
        println("[2] Return Books")
        println("[3] Display Records")
        println("[4] Exit")
        print("Enter your choice: ")
        val choice = readLine()?.toIntOrNull()

        when (choice) {
            1 -> {
                val borrower = addBorrower()
                borrowers.add(borrower)
            }
            2 -> {
                returnBorrower(borrowers)
            }
            3 -> {
                displayBorrowers(borrowers)
            }
            4 -> {
                continueProgram = false
                println("Exiting the Book Borrower System. Goodbye!")
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}

data class Borrower(
    val studentId: String,
    val studentName: String,
    val yearLevel: String,
    val course: String,
    val borrowedBooks: MutableList<BorrowedBook>,
    val borrowedDate: String
)

data class BorrowedBook(
    val book: String,
    val returnDate: String
)

fun addBorrower(): Borrower {
    println("\nAdding Borrower:")
    print("Enter student's ID: ")
    val studentId = readLine()?.let { "${it.substring(0, 2)}-${it.substring(2)}" } ?: ""

    print("Enter student's first name: ")
    val studentFname = readLine()?.toUpperCase() ?: ""
    print("Enter student's Middle Name: ")
    val studentMI = readlnOrNull()?.toCharArray()?.getOrNull(0)?.toUpperCase()
    print("Enter student's last name: ")
    val studentLName = readLine()?.toUpperCase() ?: ""
    val studentName = "$studentFname ${studentMI ?: ""}. $studentLName"

    println("Enter student's course:")
    courses.forEachIndexed { index, course -> println("${index + 1}. $course") }
    print("Enter your choice: ")
    val courseChoice = readLine()?.toIntOrNull()
    val course = courses.getOrNull(courseChoice?.minus(1) ?: -1) ?: ""

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

    val borrowedBooks = mutableListOf<BorrowedBook>()
    var continueAddBook = true

    while (continueAddBook) {
        println("\nAvailable Books to Borrow:")
        books.forEachIndexed { index, book -> println("${index + 1}. $book") }
        print("Enter the book to borrow (or enter 'exit' to finish adding books): ")
        val borrowedBook = readLine()?.toUpperCase()

        if (borrowedBook == "EXIT") {
            continueAddBook = false
        } else {
            if (borrowedBook !in books) {
                println("Book '$borrowedBook' is not available.")
            } else if (borrowedBooks.any { it.book == borrowedBook }) {
                println("Book '$borrowedBook' already added.")
            } else {
                print("Enter return date for $borrowedBook: ")
                val returnDate = readLine() ?: ""
                val returnDateFormatted = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                val formattedReturnDate = returnDateFormatted.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))
                println("Book '$borrowedBook' added.")
                borrowedBooks.add(BorrowedBook(borrowedBook!!, formattedReturnDate))
            }
        }
    }

    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))
    return Borrower(studentId, studentName, yearLevel, course, borrowedBooks, formattedDate)
}


fun returnBorrower(borrowers: MutableList<Borrower>) {
    if (borrowers.isNotEmpty()) {
        println("\nReturning Borrower:")
        println("Enter student's ID to return: ")
        val studentId = readLine()?.let { "${it.substring(0, 2)}-${it.substring(2)}" } ?: ""
        val borrower = borrowers.find { it.studentId == studentId }

        if (borrower != null) {
            borrowers.remove(borrower)
            println("Borrower with ID '$studentId' returned successfully.")
        } else {
            println("Borrower with ID '$studentId' not found.")
        }
    } else {
        println("\nNo borrowers to return.")
    }
}

fun displayBorrowers(borrowers: List<Borrower>) {
    if (borrowers.isNotEmpty()) {
        println("\nList of Borrowers:")
        for ((index, borrower) in borrowers.withIndex()) {
            println("Borrower ${index + 1}:")
            println("Student ID: ${borrower.studentId}")
            println("Student Name: ${borrower.studentName}")
            println("Course: ${borrower.course}")
            println("Year Level: ${borrower.yearLevel}")
            println("Borrowed Date: ${borrower.borrowedDate}")
            if (borrower.borrowedBooks.isNotEmpty()) {
                println("Borrowed Books:")
                borrower.borrowedBooks.forEachIndexed { i, borrowedBook ->
                    println("${i + 1}. Book: ${borrowedBook.book}, Return Date: ${borrowedBook.returnDate}")
                }
            } else {
                println("No books borrowed.")
            }
            println()
        }
    } else {
        println("\nNo borrowers to display.")
    }
}

fun readlnOrNull() = readLine()
