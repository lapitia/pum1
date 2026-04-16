fun main() {
    val number=4
    var sum=0
    fun sumEven(num:Int){
        for (n in 1..num){
            if (n%2==0){
                sum+=n
            }
        }
        println(sum)
	}
    sumEven(number)
}
