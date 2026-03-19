fun check(n: Int, list: List<Int>): Int {
    if (n <= 0 || list.size <= n) return -1

    for (i in n until list.size) {
        val target = list[i] //sprawdzana liczba
        val preamble = list.subList(i - n, i) //n elementów przed target (od indeksu początkowego włącznie do końcowego wyłącznie)

        var ok = false

        for (j in preamble.indices) {
            for (k in j + 1 until preamble.size) {
                if (preamble[j] != preamble[k] && preamble[j] + preamble[k] == target) {
                    ok = true
                    break
                }
            }
            if (ok) break
        }

        if (!ok) return target
    }

    return -1
}

fun main() {
    println(check(2, listOf(1, 2, 3, 4, 5, 6))) // 4
    println(
        check(
            5,
            listOf(35, 25, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)
        )
    ) // 127
}
