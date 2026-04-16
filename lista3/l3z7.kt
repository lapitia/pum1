fun srt(list: List<String>): List<Pair<String, List<String>>> {
    return list
        .filter { it.length % 2 == 0 } // tylko o parzystej długości
        .groupBy { it.first().toString() } // grupy po pierwszej literze
        .toSortedMap() // sortujemy po literze
        .map { (key, value) -> Pair(key, value) } // konwertujemy na pary
}

fun main(){
println(srt(listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut"))) // [(b, [banana]), (c, [cherry, citrus])]
}