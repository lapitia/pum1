fun countElements(lists: List<List<String>>): Map<String, Int> {
    return lists
        .flatten() // uproszczamy List<List<String>> do List<String>
        .groupingBy { it } // grupujemy po wartości
        .eachCount() //liczymy każdą grupę 
}

fun main(){
println(countElements(listOf(
    listOf("a", "b", "c"),
    listOf("c", "d", "f"),
    listOf("d", "f", "g")))) // {a=1, b=1, c=2, d=2, f=2, g=1}
}