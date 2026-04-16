import kotlin.math.pow
fun main() {
    
fun checkArmstrong(number: Int): Boolean {
    val s = number.toString()
    val k = s.length
    var sum = 0
    for (ch in s) {
        val digit = ch.digitToInt()
        sum += digit.toDouble().pow(k).toInt()
    }
    return sum == number
}
    println(checkArmstrong(153))
}