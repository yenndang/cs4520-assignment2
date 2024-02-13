package com.cs4520.assignment1

// modify the Dataset.kt file using the sealed class definition for Product
val productsDataset: List<Product> = listOf(
    Product.Equipment("Treadmill", 32),
    Product.Food("Banana", "2024-02-29", 29),
    Product.Equipment("Dumbbells", 45),
    Product.Food("Apple", "2024-03-10", 20),
    Product.Equipment("Stationary Bike", 50),
    Product.Food("Orange", "2024-03-05", 25),
    Product.Equipment("Yoga Mat", 15),
    Product.Food("Grapes", "2024-02-02", 18),
    Product.Equipment("Resistance Bands", 22),
    Product.Food("Kiwi", "2024-01-29", 30),
    Product.Equipment("Elliptical Machine", 55),
    Product.Food("Strawberries", "2024-03-08", 28),
    Product.Equipment("Weight Bench", 40),
    Product.Food("Watermelon", "2024-03-12", 15),
    Product.Equipment("Jump Rope", 10),
    Product.Food("Blueberries",  "2024-04-05", 22),
    Product.Equipment("Kettlebell",  35),
    Product.Food("Mango",  "2024-04-10", 33),
    Product.Equipment("Rowing Machine", 48),
    Product.Food("Pineapple", "2024-03-20", 26),
    Product.Equipment("Pull-Up Bar",  30),
    Product.Food("Peach",  "2024-04-15", 23),
    Product.Equipment("Medicine Ball", 18),
    Product.Food("Cherry", "2024-04-08", 35),
    Product.Equipment("Foam Roller",  20),
    Product.Food("Papaya",  "2024-04-18", 32),
    Product.Equipment("Balance Ball",  25),
    Product.Food("Pear", "2024-04-20", 27),
    Product.Equipment("Step Platform", 15),
    Product.Food("Plum", "2024-04-28", 19),
    Product.Equipment("Battle Ropes",  42),
    Product.Food("Apricot", "2024-04-25", 21),
    Product.Equipment("Trampoline",   38),
    Product.Food("Raspberry",  "2024-05-02", 24),
    Product.Equipment("Gymnastic Rings",  50),
    Product.Food("Blackberry", "2024-05-08", 29)
)

//
//val productsDataset_OLD = listOf(
//    listOf("Treadmill", "Equipment", null, 32),
//    listOf("Banana", "Food", "2024-02-29", 29),
//    listOf("Dumbbells", "Equipment", null, 45),
//    listOf("Apple", "Food", "2024-03-10", 20),
//    listOf("Stationary Bike", "Equipment", null, 50),
//    listOf("Orange", "Food", "2024-03-05", 25),
//    listOf("Yoga Mat", "Equipment", null, 15),
//    listOf("Grapes", "Food", "2024-02-02", 18),
//    listOf("Resistance Bands", "Equipment", null, 22),
//    listOf("Kiwi", "Food", "2024-01-29", 30),
//    listOf("Elliptical Machine", "Equipment", null, 55),
//    listOf("Strawberries", "Food", "2024-03-08", 28),
//    listOf("Weight Bench", "Equipment", null, 40),
//    listOf("Watermelon", "Food", "2024-03-12", 15),
//    listOf("Jump Rope", "Equipment", null, 10),
//    listOf("Blueberries", "Food", "2024-04-05", 22),
//    listOf("Kettlebell", "Equipment", null, 35),
//    listOf("Mango", "Food", "2024-04-10", 33),
//    listOf("Rowing Machine", "Equipment", null, 48),
//    listOf("Pineapple", "Food", "2024-03-20", 26),
//    listOf("Pull-Up Bar", "Equipment", null, 30),
//    listOf("Peach", "Food", "2024-04-15", 23),
//    listOf("Medicine Ball", "Equipment", null, 18),
//    listOf("Cherry", "Food", "2024-04-08", 35),
//    listOf("Foam Roller", "Equipment", null, 20),
//    listOf("Papaya", "Food", "2024-04-18", 32),
//    listOf("Balance Ball", "Equipment", null, 25),
//    listOf("Pear", "Food", "2024-04-20", 27),
//    listOf("Step Platform", "Equipment", null, 15),
//    listOf("Plum", "Food", "2024-04-28", 19),
//    listOf("Battle Ropes", "Equipment", null, 42),
//    listOf("Apricot", "Food", "2024-04-25", 21),
//    listOf("Trampoline", "Equipment", null, 38),
//    listOf("Raspberry", "Food", "2024-05-02", 24),
//    listOf("Gymnastic Rings", "Equipment", null, 50),
//    listOf("Blackberry", "Food", "2024-05-08", 29),
//)