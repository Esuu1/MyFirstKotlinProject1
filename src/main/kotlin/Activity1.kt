fun main() {



    for(noOfGroup in 1..4) {
        print("Enter your first name: ")
        var first = readLine()
        print("Enter your middle E: ")
        var E = readLine()
        print("Enter your last name: ")
        var last = readLine()
        print("How old are you: ")
        var age = readLine()?.toInt() ?: 0

        println("your name is $first $E. $last\nYour Age is $age")
    }

}