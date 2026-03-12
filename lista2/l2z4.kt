fun safeParseAndClassify(input: String?): String{
	if (input.isNullOrBlank()) "brak_danych"
	return input.toIntOrNull()?.let{
        number ->
        if(number%2==0) "parzysta" 
        else "nieparzysta" ? "brak_danych"
    }
}
fun main() {
	
}
