fun findDuplicates(list: List<Int>): List<Int> {
    val seen = mutableSetOf<Int>() // elementy, po którym już przeszliśmy
    val duplicates = mutableSetOf<Int>() // duplikaty niepowtarzalne

    for (n in list) {
        if (!seen.add(n)) {  // add() zwraca false jeśli element już był
            duplicates.add(n)
        }
    }

    return duplicates.sorted() // posortowana lista
}
fun main() {
val lst = listOf(0, 1, 1, 1, 4, 4, 4, 9, 3, 3, 3, 3)
println(findDuplicates(lst)) // [1, 3, 4]
}