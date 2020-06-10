package machine

import java.util.*

class Machine {

    private var amountOf: MutableList<Int> = mutableListOf(400, 540, 120, 9, 550)

    enum class DRINK(val water: Int, val milk: Int, val beans: Int, val money: Int){
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6)
    }

    private fun whatIsStatus() {
        println("The coffee machine has:")
        println("${amountOf[0]} of water")
        println("${amountOf[1]} of milk")
        println("${amountOf[2]} of coffee beans")
        println("${amountOf[3]} of disposable cups")
        println("${amountOf[4]} of money")
    }

    private fun buyFromCoffeeMachine(drink: DRINK) {
        when {
            amountOf[3] <= 0 -> println("Sorry, not enough cups!")
            drink.water > amountOf[0] -> println("Sorry, not enough water!")
            drink.milk > amountOf[1] -> println("Sorry, not enough milk!")
            drink.beans > amountOf[2] -> println("Sorry, not enough coffee beans!")
            else -> {
                amountOf[0] -= drink.water
                amountOf[1] -= drink.milk
                amountOf[2] -= drink.beans
                amountOf[3]--
                amountOf[4] += drink.money
                println("I have enough resources, making you a coffee!")
            }
        }
    }

    private fun fillForCoffeeMachine(amountWater: Int, amountMilk: Int, amountCoffeeBeans: Int, amountDisposableCups: Int) {
        amountOf[0] += amountWater
        amountOf[1] += amountMilk
        amountOf[2] += amountCoffeeBeans
        amountOf[3] += amountDisposableCups
    }

    private fun takeFromCoffeeMachine() {
        println("I gave you $${amountOf[4]}")
        amountOf[4] = 0
    }

    fun action(){
        val scanner = Scanner(System.`in`)

        while (true){
            println("")
            print("Write action (buy, fill, take, remaining, exit): ")
            val task: String = scanner.nextLine()
            println("")

            when (task){
                "buy" -> {
                    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                    if (scanner.hasNextInt()){
                        when (scanner.nextInt()){
                            1 -> buyFromCoffeeMachine(DRINK.ESPRESSO)
                            2 -> buyFromCoffeeMachine(DRINK.LATTE)
                            3 -> buyFromCoffeeMachine(DRINK.CAPPUCCINO)
                        }
                    }
                }
                "fill" -> {
                    print("Write how many ml of water do you want to add: ")
                    val amountAddedWater: Int = scanner.nextInt()
                    print("Write how many ml of milk do you want to add: ")
                    val amountAddedMilk: Int = scanner.nextInt()
                    print("Write how many grams of coffee beans do you want to add: ")
                    val amountAddedCoffeeBeans: Int = scanner.nextInt()
                    print("Write how many disposable cups of coffee do you want to add: ")
                    val amountAddedDisposableCups: Int = scanner.nextInt()
                    fillForCoffeeMachine(amountAddedWater,amountAddedMilk,amountAddedCoffeeBeans,amountAddedDisposableCups)
                }
                "take" -> {
                    takeFromCoffeeMachine()
                }
                "remaining" -> {
                    whatIsStatus()
                }
                "exit" -> {
                    scanner.close()
                    return
                }
            }
        }
    }
}

fun main() {
    val coffee = Machine()
    coffee.action()
}