fun evenPositiveSquare(list: List<Int>): List<Int> {
    return list
        .filterIndexed { index, _ -> index % 2 != 0 } // tylko nieparzyste indeksy bo mamy resztę od dzielenia przez 2
        .filter { it > 0 }
        .map { it * it } // do kwadratu
}

fun main(){
println(evenPositiveSquare(listOf(1, 2, 3, 5, -6, -1, -1, 2, 3))) // [4, 25, 4]
}