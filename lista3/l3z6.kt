fun perm(list: List<Int>): List<List<Int>> {
// 0 lub 1 element -> jedna permutacja
    if (list.size <= 1) return listOf(list)

    return list.flatMap { element ->
// przyjmujemy każdy element kolejno jako pierwszy i permutujemy resztę
        val remaining = list - element // lista bez bieżącego elementu
        perm(remaining).map { listOf(element) + it } // doklejamy pozostałe elementy na przód żeby permutować
    }
}

fun main(){
println(perm(listOf(1, 2, 3))) // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
}