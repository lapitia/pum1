fun main() {
    val height: Int = 4
    fun printPascal(height: Int) {
    val triangle = MutableList(height) { MutableList(it + 1) { 1 } }

    for (i in 2 until height) {
        for (j in 1 until i) {
            triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j]
        }
    }

    for (i in 0 until height) {
        val spaces = " ".repeat(height - i)
        print(spaces)
        println(triangle[i].joinToString(" "))
    }
}
printPascal(height)
}
