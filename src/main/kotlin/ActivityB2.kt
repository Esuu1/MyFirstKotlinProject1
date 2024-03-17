fun main() {
    val users = mapOf("cashier" to "admin123", "cashier" to "admin@1")

    Logger.log.info { "Login" }

    var username: String
    var password: String
    var isLoggedIn = false

    while (!isLoggedIn) {
        print("Username: ")
        username = readLine() ?: ""
        print("Password: ")
        password = readLine() ?: ""

        if (users.containsKey(username) && users[username] == password) {
            Logger.log.info { "User Logins successfully." }
            println("Login successful!")
            isLoggedIn = true
        } else {
            println("Incorrect username or password. Please try again.")
            Logger.log.error { "User not identified." }
        }
    }

    val store = GroceryStore()

    println("Login as cashier")
    println("[1] Process Transaction")
    println("[2] Display records")
    println("[3] Exit")

    var choice: String
    while (true) {
        print("Enter your choice: ")
        choice = readLine() ?: ""
        when (choice) {
            "1" -> processTransaction(store)
            "2" -> displayRecords(store)
            "3" -> {
                Logger.log.info { "System exits." }
                println("Exiting...")
                break
            }
            else -> println("Invalid choice. Please enter a valid option.")
        }
    }
}

fun processTransaction(store: GroceryStore) {
    Logger.log.info { "User selected option1." }
    println("\nProcess Transaction")
    var itemName: String
    var price: Double
    var quantity: Int

    while (true) {
        print("Enter item name (or 'done' to finish): ")
        itemName = readLine()?.trim() ?: ""
        if (itemName.equals("done", ignoreCase = true)) break

        print("Enter price per item: ")
        price = readLine()?.toDoubleOrNull()!!
        if(price == null){
            println("Invalid price. Please enter a valid number.")
            continue
        }
        if (price <= 0) {
            println("Price must be greater than zero.")
            continue
        }

        print("Enter quantity: ")
        quantity = readLine()?.toIntOrNull()!!
        if(quantity == null){
            println("Invalid quantity. Please enter a valid number.")
            continue
        }
        if (quantity <= 0) {
            println("Quantity must be greater than zero.")
            continue
        }

        store.addItem(itemName, price, quantity)
    }
}

fun displayRecords(store: GroceryStore) {
    Logger.log.info { "User selected option2." }
    println("\nDisplay Records")
    val (itemsBought, totalCost) = store.displayCart()

    println("\nItems Bought:")
    for (item in itemsBought) {
        println("- $item")
    }
    println("Total Cost: $totalCost")
}

data class Item(val name: String, val price: Double)

class GroceryStore {
    private val inventory = mutableMapOf<String, Item>()
    private val cart = mutableMapOf<Item, Int>()

    fun addItem(name: String, price: Double, quantity: Int) {
        val item = Item(name, price)
        inventory[item.name] = item
        cart[item] = cart.getOrDefault(item, 0) + quantity
    }

    fun displayCart(): Pair<List<String>, Double> {
        val itemsBought = mutableListOf<String>()
        var totalCost = 0.0
        for ((item, quantity) in cart) {
            val itemName = "${item.name} x $quantity"
            itemsBought.add(itemName)
            totalCost += item.price * quantity
        }
        return Pair(itemsBought, totalCost)
    }
}
