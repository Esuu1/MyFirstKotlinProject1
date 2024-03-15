fun main() {
    println("Enter number of students: ")
    var noOfStuds = readLine()?.toInt()
    val subjects = listOf("Math", "English", "Filipino", "Science")
    for (i in 1..noOfStuds!!) {
        println("Write your name: ")
        val name = readLine()
        val grades = mutableMapOf<String, Double>()

        subjects.forEach { subject ->
            grades[subject] = getGrade(subject)
        }
        val totalGrade = grades.values.sum()
        val averageGrade = totalGrade / subjects.size
        println("Grades for $name:")
        grades.forEach { (subject, grade)->
            println("$subject: $grade")
        }
        println("$name, your average grade is : $averageGrade")
        if (averageGrade > 75) {
            println("$name, you passed!")
        } else if (averageGrade < 75) {
            println("$name, you failed!")
        } else {
            println("Invalid!")
        }
    }
}

fun getGrade(subject: String): Double {
    println("Enter your grade in $subject: ")
    val input = readLine()
    return if(input != null && input.toDoubleOrNull()!= null){
        input.toDouble()
    }else {
        println("Invalid input. Enter a valid grade.")
        getGrade(subject)
    }
}