fun safeParseAndClassify(input: String?): String {
    if (input.isNullOrBlank()) return "BRAK_DANYCH" //null lub puste

    return input.toIntOrNull()?.let { number -> //gdy jest int, to idzie dalej, gdy nie, null i później brak danych
        if (number % 2 == 0) "PARZYSTA" else "NIEPARZYSTA"
    } ?: "BRAK_DANYCH"
}

fun main() {
println(safeParseAndClassify(null)) // BRAK_DANYCH
println(safeParseAndClassify("")) // BRAK_DANYCH
println(safeParseAndClassify("10")) // PARZYSTA
println(safeParseAndClassify("7")) // NIEPARZYSTA
}
