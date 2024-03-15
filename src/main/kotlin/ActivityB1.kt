import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    var studentId: String? = null
    var studentFname: String? = null
    var studentMI: String? = null
    var studentLName: String? = null
    var studentName: String? = null
    var continueAddStudent = true
    var studentDept: String? = null
    var yearLevelChoice: Int? = null
    var yearLevel: String? = null

    val subjects = arrayListOf("ENGLISH", "MATH", "FILIPINO", "SCIENCE")

    while (continueAddStudent) {

        var continueAddBook = true
        var borrowedBooks = ""

        while (continueAddBook) {
            println("Welcome To Library System\n")
            println("[1] Add Books")
            println("[2] Remove Books")
            println("[3] Borrow Books")
            println("[4] Return Books")
            println("[5] See Booklist")

            print("\nEnter your choice: ")
            val bookChoice = readLine()?.toIntOrNull()

            when (bookChoice) {
                1 -> {
                    print("Enter book name: ")
                    val bookName = readLine()?.toUpperCase() ?: ""
                    if (subjects.contains(bookName)) {
                        Logger.log.error { "Book entered is already in the list." }
                        println("Book '$bookName' is already in the list.")
                    } else {
                        subjects.add(bookName)
                        Logger.log.info { "Book entered is added to the list." }
                        println("Book '$bookName' added to the list.")
                    }
                }

                2 -> {
                    print("Enter book name to remove: ")
                    val bookToRemove = readLine()?.toUpperCase() ?: ""
                    if (subjects.contains(bookToRemove)) {
                        subjects.remove(bookToRemove)
                        Logger.log.info{ "Book is removed from the list." }
                        println("Book '$bookToRemove' removed from the list.")
                    } else {
                        Logger.log.error{"Book entered is not in the list."}
                        println("Book '$bookToRemove' is not in the list.")
                    }
                }

                3 -> {
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
                    studentDept = readLine()?.toUpperCase()
                    println("Enter student's year level:")
                    println("1. First year")
                    println("2. Second year")
                    println("3. Third year")
                    println("4. Fourth year")
                    print("Enter your choice: ")
                    yearLevelChoice = readLine()?.toIntOrNull()

                    yearLevel = when (yearLevelChoice) {
                        1 -> "First year"
                        2 -> "Second year"
                        3 -> "Third year"
                        4 -> "Fourth year"
                        else -> "Unknown"
                    }

                    // Initialize continueAddBook to true to enter the borrowing loop
                    continueAddBook = true

                    // Start the borrowing loop
                    while (continueAddBook) {
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
                            // Display student details after borrowing a book
                            Logger.log.info { "Data Saved Successfully!" }
                            displayStudentDetails(studentId, studentName, studentDept, yearLevel, borrowedBooks)
                        } else {
                            Logger.log.warn { "User entered book is unavailable." }
                            println("Book '$borrowBook' is unavailable.")
                        }

                        println("\nDo you want to add another book to borrow? (yes/no)")
                        continueAddBook = readLine()?.toLowerCase() == "yes"
                    }
                }

                4 -> {
                    if (borrowedBooks.isNotEmpty()) {
                        print("Enter book name to return: ")
                        val returnBook = readLine()?.toUpperCase() ?: ""

                        if (borrowedBooks.contains(returnBook)) {
                            borrowedBooks = borrowedBooks.replace(returnBook, "").replace(", ,", ",")
                            Logger.log.info { "User's borrowed book returned successfully." }
                            println("Book '$returnBook' returned successfully.")
                        } else {
                            Logger.log.warn { "Book user entered was not borrowed." }
                            println("Book '$returnBook' was not borrowed.")
                        }
                    } else {
                        Logger.log.error{"No books borrowed."}
                        println("No books borrowed.")
                    }
                }

                5 -> {
                    if (subjects.isEmpty()) {
                        Logger.log.warn{"books are not available."}
                        println("No books available.")
                    } else {
                        println("Available Books:")
                        subjects.forEachIndexed { index, book ->
                            println("${index + 1}. $book")
                        }
                    }
                }

                else -> {
                    Logger.log.error{"User choose invalid number."}
                    println("Invalid Choice. Enter a valid number.")
                }
            }
        }

        println("\nDo you want to add another student to borrow books? (yes/no)")
        continueAddStudent = readLine()?.toLowerCase() == "yes"
    }
    Logger.log.info{"System Exiting."}
    println("Exiting the Library System. Thank You!")
}

fun displayStudentDetails(studentId: String?, studentName: String?, studentDept: String?, yearLevel: String?, borrowedBooks: String) {
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"))

    println("\nStudent Details:")
    println("Student ID: $studentId, Student Name: $studentName, Department: $studentDept, Year Level: $yearLevel, Date: $formattedDate, Books: $borrowedBooks")
}
