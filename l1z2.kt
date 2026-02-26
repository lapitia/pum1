fun main() {
    val word="awawa"
    fun isPalindrome(word:String){
        val word_lower=word.lowercase()
        if (word_lower==word.reversed())
        {println("tak")}
        else{println("nie")}
	}
    isPalindrome(word)
}
