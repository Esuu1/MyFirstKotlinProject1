import java.text.DecimalFormat

data class CompProducts(val name: String, val quantity: Int, val price: Double, val condition: String, val remarks: String)

fun main() {
    println("*********************************************************************************************")
    println("***                  Welcome to the Computer Parts Inventory System!                   ***")
    println("*********************************************************************************************")

    var continueAdd = true

    var product1: CompProducts? = null
    var product2: CompProducts? = null
    var product3: CompProducts? = null
    var product4: CompProducts? = null
    var product5: CompProducts? = null
    var productCount = 0

    while (continueAdd) {
        println("\nMenu:")
        println("1. Add a product to inventory:")
        println("2. View inventory:")
        println("3. Exit")
        print("\nSelect an option: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                if (productCount < 5) {
                    println("\nEnter details:")
                    print(" product name: ")
                    val name = readLine() ?: ""
                    print("Quantity: ")
                    val quantity = readLine()?.toIntOrNull() ?: 0
                    print("Price (in PHP): ")
                    val priceString = readLine()
                    val price = if (priceString != null) priceString.toDoubleOrNull() ?: 0.0 else 0.0
                    val formatter = DecimalFormat("PHP#no,###.00")
                    val formattedPrice = formatter.format(price)
                    print("Condition(good/bad): ")
                    val condition = readLine()?.takeIf { it.isNotEmpty() } ?: "bad"
                    val remarks = if (condition.equals("good", ignoreCase = true)) "product is buyable" else "not buyable"

                    when (productCount) {
                        0 -> product1 = CompProducts(name, quantity, price, condition, remarks)
                        1 -> product2 = CompProducts(name, quantity, price, condition, remarks)
                        2 -> product3 = CompProducts(name, quantity, price, condition, remarks)
                        3 -> product4 = CompProducts(name, quantity, price, condition, remarks)
                        4 -> product5 = CompProducts(name, quantity, price, condition, remarks)
                    }

                    productCount++

                    println("\nProduct added to inventory!")
                    println("Product: Name: $name, Quantity: $quantity, Price: $formattedPrice, Condition: $condition, Remarks: $remarks")
                } else {
                    println("\nMaximum number of products reached.")
                }
            }
            2 -> {
                if (productCount > 0) {
                    println("\nCurrent inventory:")
                    if (product1 != null) {
                        println("Product 1: Name: ${product1!!.name}, Quantity: ${product1!!.quantity}, Price: PHP${"%.2f".format(product1!!.price)}, Condition: ${product1!!.condition}, Remarks: ${product1!!.remarks}")
                    }
                    if (product2 != null) {
                        println("Product 2: Name: ${product2!!.name}, Quantity: ${product2!!.quantity}, Price: PHP${"%.2f".format(product2!!.price)}, Condition: ${product2!!.condition}, Remarks: ${product2!!.remarks}")
                    }
                    if (product3 != null) {
                        println("Product 3: Name: ${product3!!.name}, Quantity: ${product3!!.quantity}, Price: PHP${"%.2f".format(product3!!.price)}, Condition: ${product3!!.condition}, Remarks: ${product3!!.remarks}")
                    }
                    if (product4 != null) {
                        println("Product 4: Name: ${product3!!.name}, Quantity: ${product3!!.quantity}, Price: PHP${"%.2f".format(product3!!.price)}, Condition: ${product3!!.condition}, Remarks: ${product3!!.remarks}")
                    }
                    if (product5 != null) {
                        println("Product 5: Name: ${product3!!.name}, Quantity: ${product3!!.quantity}, Price: PHP${"%.2f".format(product3!!.price)}, Condition: ${product3!!.condition}, Remarks: ${product3!!.remarks}")
                    }
                } else {
                    println("\nNo product present in the inventory yet.")
                }
            }
            3 -> {
                continueAdd = false
            }

            else -> {
                println("\nInvalid option. Please select a valid option.")
            }
        }

        if (continueAdd) {
            println("\nDo you want to add another product to the inventory? (yes/no)")
            val addAnother = readLine()?.toLowerCase()
            if (addAnother == "no" || addAnother == "n") {
                continueAdd = false
            }
        }
    }

    println("\nExiting the Inventory System. Goodbye!")
}