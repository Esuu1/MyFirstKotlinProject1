fun main() {
    for(noOfStud in 1..4) {
        val birthYear = getBirthYear()
        val age = calculateAge(birthYear)
        val ageGroup = determineAgeGroup(age)
        println("What is your name: ")
        var name = readLine()
        println("Hi $name. Your age is $age. You are $ageGroup")
    }
}
fun getBirthYear():Int{
    println("Enter your birth year: ")
    val input = readLine()?.toIntOrNull()
    return if(input == null||input <0|| input< 1970 || input >2024){
        println("Invalid input. Please enter a valid year.")
        getBirthYear()
    }else{
        input
    }
}
fun calculateAge(birthYear: Int):Int
{
    val currentYear = 2024
    return currentYear - birthYear
}
fun determineAgeGroup(age:Int):String{
    return when{
        age in 0..1->"Baby"
        age in 1..17 ->"Under Age"
        age in 18..30->"Young Adult"
        age in 31..59->"Adult"
        else->"Old"
    }
}