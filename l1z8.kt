fun main() {
    fun countVowels(word: String): Int {
    val vowels = setOf('a','e','i','o','u','y')
    var count = 0
    for (ch in word.lowercase()) {
        if (ch in vowels) count++
    }
    return count
}
    println(countVowels("meow"))
}